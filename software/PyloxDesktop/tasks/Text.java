package tasks;


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

    @Override public String getDescription(){
        return "<" + aText + ">";
    }
}