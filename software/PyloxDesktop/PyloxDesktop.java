import frames.MainFrame;

import tasks.*;

import utils.Cookies;
import utils.Profile;
import utils.Serial;

import javax.swing.JFrame;

import java.time.LocalTime;

import static utils.Data.load;
import static utils.Data.save;


/**
 * @author Charles A
 * @version 05/18/2025
 */
public class PyloxDesktop
{
    private static final String COOKIES_FILE = "cookies.pxd";

    private Cookies aCookies;

    private Profile aProfile;



    private static final int ROWS = 3;
    private static final int COLUMNS = 5;

    private static final String PORT = "COM7";

    private TaskList[][] aTaskLists;
    private String aLangage;

    /**
     * Constructs PyloxDesktop objects.
     */
    public PyloxDesktop()
    {
        aCookies = (Cookies) load(COOKIES_FILE);

        if (aCookies == null){
            aCookies = new Cookies();
        }

        aProfile = aCookies.getProfile();

        /*TaskList[][] loaded = load();

        if (loaded != null) {
            aTaskLists = loaded;

            //System.out.println("Loaded task lists.");
        } else {
            aTaskLists = new TaskList[ROWS][COLUMNS];
            for (int r = 0; r < ROWS; r++) for (int c = 0; c < COLUMNS; c++) aTaskLists[r][c] = (new TaskList());

            {// TEST
                aTaskLists[1][1].add(new Text());
                aTaskLists[1][1].add(new Shortcut());
                aTaskLists[1][1].add(new Mouse());
                aTaskLists[1][1].add(new Delay());
            }

            //System.out.println("Tasklists created");
        }*/

        aTaskLists = aProfile.getTaskLists();

        aLangage = "en";

        MainFrame vMainFrame;

        switch(aLangage){
            case "en":
            default:
                vMainFrame = new MainFrame(aCookies);
                break;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                save(aCookies, COOKIES_FILE)
        ));

        vMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SerialExecuter(aTaskLists).start();
    }

    private Thread SerialExecuter(final TaskList[][] pTaskLists) {
        Thread watcher = new Thread(() -> {
            while (true) {
                if (!Serial.isOpened()) {
                    if (!Serial.open(PORT)) {
                        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                        continue;
                    }
                    Serial.clearInput();
                    System.out.println("Port " + PORT + " ouvert avec succès");
                }
                
                try {
                    while (Serial.isOpened()) {
                        if (Serial.available()) {
                            String message = Serial.read();
                            if (message != null) {
                                System.out.println(LocalTime.now() + " - " + PORT + " : " + message);

                                String[] vTokens = message.split("\\.");
                                //System.out.printf("%s %s %s\n", vTokens[0], vTokens[1], vTokens[2]);

                                switch (vTokens[0]) {
                                    case "enc":
                                        break;
                                    case "but":
                                        try {
                                            int nb =  Integer.parseInt(vTokens[1]);
                                            int row = nb / COLUMNS;
                                            int column = nb % COLUMNS;

                                            aTaskLists[row][column].executeAll();
                                        }
                                        catch (NumberFormatException e) {
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Serial.close();
                }
            }
        }, "SerialExecuterThread");

        watcher.setDaemon(true);
        return watcher;
    }

    /**
     * Start the application.
     */
    public static void main(String[] args) {
        new PyloxDesktop();
    }


}