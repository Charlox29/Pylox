package tasks;

import java.util.ArrayList;


/**
 * Task of executing a macro.
 * The macro is represented by a list of character strings (ArrayList of Strings), each element of which represents the label of a key.
 * By default, the key list is empty.
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see Task
 * @see ArrayList
 */
public class Macro extends Task
{
    private static final String TYPE = "Macro";
    
    private ArrayList<String> aKeys;
    
    /**
     * Constructs Macro objects.
     */
    public Macro()
    {
        super(TYPE);

        aKeys = new ArrayList<>();
    }

    /**
     * List accessor
     *
     * @return the list of keys
     */
    public ArrayList<String> getMacro(){
        return aKeys;
    }

    /**
     * List modifier
     *
     * @param pList the list of keys
     */
    public void setMacro(final ArrayList<String> pList){
        aKeys = pList;
    }
    
    /**
     * Gets the length of the macro.
     *
     * @return the number of keys the macro has
     */
    public int size(){
        return aKeys.size();
    }

    /**
     * Knows if the macro contains any key.
     *
     * @return <code>true</code> if the macro is empty; <code>false</code> otherwise.
     */
    public boolean isEmpty(){
        return aKeys.isEmpty();
    }
    
    /**
     * Adds a key to the macro.
     *
     * @param pString the title of the key
     */
    public void add(final String pString){
        aKeys.add(pString);
    }

    /**
     * Clears all keys.
     */
    public void clear(){
        aKeys.clear();
    }

    @Override public Macro clone(){
        Macro vMacro = new Macro();

        vMacro.setMacro(aKeys);

        return vMacro;
    }

    @Override public String getDescription(){
        if (isEmpty()) return "Macro empty";

        StringBuilder vS = new StringBuilder();

        for(String vString : aKeys){
            vS.append("<").append(vString).append("> ");
        }

        return vS.toString().trim();
    }
}