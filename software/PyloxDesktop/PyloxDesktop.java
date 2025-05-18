import frames.MainFrame;

import tasks.Delay;
import tasks.Shortcut;
import tasks.Mouse;
import tasks.TaskList;
import tasks.Text;

import javax.swing.JFrame;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


/**
 * @author Charles A
 * @version 05/18/2025
 */
public class PyloxDesktop
{
    private static final String SAVE_FILE = "tasks.pxd";

    private static final int ROWS = 3;
    private static final int COLUMNS = 5;

    private TaskList[][] aTaskLists;
    private String aLangage;

    /**
     * Constructs PyloxDesktop objects.
     */
    public PyloxDesktop()
    {
        TaskList[][] loaded = load();

        if (loaded != null) {
            aTaskLists = loaded;

            System.out.println("Loaded task lists.");
        } else {
            aTaskLists = new TaskList[ROWS][COLUMNS];
            for (int r = 0; r < ROWS; r++) for (int c = 0; c < COLUMNS; c++) aTaskLists[r][c] = (new TaskList());

            {// TEST
                aTaskLists[1][1].add(new Text());
                aTaskLists[1][1].add(new Shortcut());
                aTaskLists[1][1].add(new Mouse());
                aTaskLists[1][1].add(new Delay());
            }

            System.out.println("Tasklists created");
        }



        aLangage = "en";

        MainFrame vMainFrame;

        switch(aLangage){
            case "en":
            default:
                vMainFrame = new MainFrame(aTaskLists);
                break;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                save(aTaskLists)
        ));

        vMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Start the application.
     */
    public static void main(String[] args) {
        new PyloxDesktop();
    }

    /**
     * Save the TaskLists to a file.
     */
    public static void save(TaskList[][] pTaskLists) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(pTaskLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import the TaskLists from a file.
     */
    private TaskList[][] load() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SAVE_FILE))) {
            return (TaskList[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}