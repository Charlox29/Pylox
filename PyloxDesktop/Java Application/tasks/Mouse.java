package tasks;

//import java.util.ArrayList;


/**
 * Task of simulating mouse behaviors.
 * /!\ This class is currently deprecated because optimal mouse movement handling has not been worked out. /!\
 *
 * @author Charles A
 * @version 29/10/2024
 */
public class Mouse extends Task
{
    private static final String TYPE = "Mouse";
    
    //private ArrayList<String> aList;
    
    /**
     * Constructor of Mouse class objects
     */
    public Mouse()
    {
        super(TYPE);
        
        //aList = new ArrayList<String>();
    }

    @Override public String getDescription(){
        return "Not defined yet.";
    }
}