import pkg_frames.MainFrame;
import pkg_tasks.TaskList;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author Charles A
 * @version 10/06/2024
 */
public class PyloxDesktop
{
    private static int ROWS = 3;
    private static int COLUMNS = 5;

    private ArrayList<TaskList> aTaskLists;
    private String aLangage;

    /**
     *
     */
    public PyloxDesktop()
    {
        this.aTaskLists = new ArrayList<TaskList>(ROWS*COLUMNS);
        this.aLangage = "en";

        MainFrame vMainFrame;

        switch(this.aLangage){
            case "en":
            default:
                vMainFrame = new MainFrame(ROWS, COLUMNS);
                break;
        }

        vMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *
     */
    public static void main (String[] args) {
        PyloxDesktop vPD = new PyloxDesktop();
    }
}
