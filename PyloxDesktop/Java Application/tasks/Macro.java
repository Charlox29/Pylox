package tasks;

import java.util.ArrayList;


/**
 * Task of executing a macro.
 * The macro is represented by a list of character strings (ArrayList of Strings), each element of which represents the label of a key.
 * By default, the key list is empty.
 *
 * @author Charles A
 * @version 29/10/2024
 */
public class Macro extends Task
{
    private static final String TYPE = "Macro";
    
    private ArrayList<String> aList;
    
    /**
     * Constructor of Macro class objects
     */
    public Macro()
    {
        super(TYPE);
        
        aList = new ArrayList<String>();
    }

    /**
     * List accessor
     *
     * @return List of keys
     */
    public ArrayList<String> getMacro(){
        return aList;
    }

    /**
     * List modifier
     *
     * @param pList List of keys
     */
    public void setMacro(final ArrayList<String> pList){
        aList = pList;
    }
    
    /**
     * Get the length of the macro
     *
     * @return The number of keys the macro has
     */
    public int size(){
        return aList.size();
    }

    /**
     * Know if the macro contains no keys
     *
     * @return True if the list is empty
     */
    public boolean isEmpty(){
        return aList.isEmpty();
    }
    
    /**
     * Add a key to the macro
     *
     * @param pString The title of the key
     */
    public void add(final String pString){
        aList.add(pString);
    }

    /**
     * Clear all macro keys
     */
    public void clear(){
        aList.clear();
    }

    @Override public String getDescription(){
        if (isEmpty()) return "...";

        StringBuilder vS = new StringBuilder();

        for(String vString : aList){
            vS.append("<").append(vString).append("> ");
        }

        return vS.toString().trim();
    }
}