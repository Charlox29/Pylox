import frames.MainFrame;

import tasks.Delay;
import tasks.Macro;
import tasks.Mouse;
import tasks.Task;
import tasks.TaskList;
import tasks.Text;

import java.util.ArrayList;
import java.util.RandomAccess;

import javax.swing.JFrame;


/**
 * @author Charles A
 * @version 29/10/2024
 */
public class PyloxDesktop
{
    private static final int ROWS = 3;
    private static final int COLUMNS = 5;

    private TaskList[][] aTaskLists;
    private String aLangage;

    /**
     * Constructor of PyloxDesktop class objects
     */
    public PyloxDesktop()
    {
        aTaskLists = new TaskList[ROWS][COLUMNS];
        for (int r = 0; r < ROWS; r++) for (int c = 0; c < COLUMNS; c++) aTaskLists[r][c] = (new TaskList());

        {// TEST
            aTaskLists[1][1].add(new Text());
            aTaskLists[1][1].add(new Macro());
            aTaskLists[1][1].add(new Mouse());
            aTaskLists[1][1].add(new Delay());
        }

        aLangage = "en";

        MainFrame vMainFrame;

        switch(aLangage){
            case "en":
            default:
                vMainFrame = new MainFrame(aTaskLists);
                break;
        }

        vMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Start the application
     */
    public static void main(String[] args) {
        new PyloxDesktop();
    }
}