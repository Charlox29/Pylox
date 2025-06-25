package tasks;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * Task of writing a text.
 * The text is represented by a String.
 * By default, the text is empty.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 *
 * @see Task
 */
public class Text extends Task
{
    private static final long serialVersionUID = 1L;

    private static final String TYPE = "Text";
    
    private String aText;
    
    /**
     * Construct Text objects.
     */
    public Text()
    {
        super(TYPE);

        aText = "";
    }

    /**
     * Text accessor
     *
     * @return the text
     */
    public String getText(){
        return aText;
    }

    /**
     * Text modifier
     *
     * @param pText the text
     */
    public void setText(final String pText){
        aText = pText;
    }

    @Override public Text clone(){
        Text vText = new Text();

        vText.setText(aText);

        return vText;
    }

    @Override public void execute(){
        try {
            String os = System.getProperty("os.name").toLowerCase();
            boolean isMac = os.contains("mac");
            int modifierKey = isMac ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable clipboardBackup = clipboard.getContents(null);

            StringSelection newContent = new StringSelection(aText);
            clipboard.setContents(newContent, null);

            Robot robot = new Robot();
            robot.setAutoDelay(10);

            robot.keyPress(modifierKey);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(modifierKey);

            // USEFULL ????
            Thread.sleep(100);

            if (clipboardBackup != null) {
                clipboard.setContents(clipboardBackup, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public String getDescription(){
        return "<" + aText + ">";
    }
}