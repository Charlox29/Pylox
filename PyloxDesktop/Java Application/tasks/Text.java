package tasks;


/**
 * Task of writing a text.
 * The text is represented by a String.
 * By default, the text is empty.
 *
 * @author Charles A
 * @version 29/10/2024
 */
public class Text extends Task
{
    private static final String TYPE = "Text";
    
    private String aText;
    
    /**
     * Constructor of Text class objects
     */
    public Text()
    {
        super(TYPE);

        aText = "";
    }

    /**
     * Text accessor
     *
     * @return Text
     */
    public String getText(){
        return aText;
    }

    /**
     * Text modifier
     *
     * @param pText Text
     */
    public void setText(final String pText){
        aText = pText;
    }

    @Override public String getDescription(){
        return "<" + aText + ">";
    }
}