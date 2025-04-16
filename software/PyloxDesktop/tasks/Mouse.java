package tasks;

//import java.util.ArrayList;


/**
 * Task of simulating mouse behaviors.
 * /!\ This class is currently deprecated because optimal mouse movement handling has not been worked out. /!\
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see Task
 */
public class Mouse extends Task
{
    private static final String TYPE = "Mouse";
    
    //private ArrayList<String> aList;
    
    /**
     * Constructs Mouse objects.
     */
    public Mouse()
    {
        super(TYPE);
        
        //aList = new ArrayList<String>();
    }

    @Override public Mouse clone() {
        return new Mouse();
    }

    @Override public String getDescription(){
        return "Not defined yet.";
    }
}