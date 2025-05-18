package tasks;

import java.util.ArrayList;


/**
 * Task of executing a shortcut.
 * The shortcut is represented by a list of character strings (ArrayList of Strings), each element of which represents the label of a key.
 * By default, the key list is empty.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 *
 * @see Task
 * @see ArrayList
 */
public class Shortcut extends Task
{
    private static final long serialVersionUID = 1L;

    private static final String TYPE = "Shortcut";
    
    private ArrayList<String> aKeys;
    
    /**
     * Constructs Shortcut objects.
     */
    public Shortcut()
    {
        super(TYPE);

        aKeys = new ArrayList<>();
    }

    /**
     * List accessor
     *
     * @return the list of keys
     */
    public ArrayList<String> getKeys(){
        return aKeys;
    }

    /**
     * List modifier
     *
     * @param pList the list of keys
     */
    public void setKeys(final ArrayList<String> pList){
        aKeys = pList;
    }
    
    /**
     * Gets the length of the shortcut.
     *
     * @return the number of keys the shortcut has
     */
    public int size(){
        return aKeys.size();
    }

    /**
     * Knows if the shorcut contains any key.
     *
     * @return <code>true</code> if the shortcut is empty; <code>false</code> otherwise.
     */
    public boolean isEmpty(){
        return aKeys.isEmpty();
    }
    
    /**
     * Adds a key to the shortcut.
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

    @Override public Shortcut clone(){
        Shortcut vShortcut = new Shortcut();

        vShortcut.setKeys(aKeys);

        return vShortcut;
    }

    @Override public String getDescription(){
        if (isEmpty()) return "Shortcut empty";

        StringBuilder vS = new StringBuilder();

        for(String vString : aKeys){
            vS.append("<").append(vString).append("> ");
        }

        return vS.toString().trim();
    }
}