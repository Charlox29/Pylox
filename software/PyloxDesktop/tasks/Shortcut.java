package tasks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Task of executing a shortcut.
 * The shortcut is represented by a list of integers (ArrayList of Integer), each element of which represents a key.
 * By default, the key list is empty.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 *
 * @see Task
 * @see ArrayList
 * @see java.awt.event.KeyEvent
 */
public class Shortcut extends Task
{
    private static final long serialVersionUID = 1L;

    private static final String TYPE = "Shortcut";
    
    private ArrayList<Integer> aKeys;
    
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
    public ArrayList<Integer> getKeys(){
        return aKeys;
    }

    /**
     * List modifier
     *
     * @param pList the list of keys
     */
    public void setKeys(final ArrayList<Integer> pList){
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
     * @param pInt the code of the key
     */
    public void add(final int pInt){
        aKeys.add(pInt);
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

    @Override
    public void execute() {
        if (aKeys.isEmpty()) return;
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(20);

            // Appuyer sur toutes les touches dans l'ordre
            for (int key : aKeys) {
                robot.keyPress(key);
            }
            // Relâcher toutes les touches dans l'ordre inverse
            for (int i = aKeys.size() - 1; i >= 0; i--) {
                robot.keyRelease(aKeys.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public String getDescription(){
        if (isEmpty()) return "Shortcut empty";

        StringBuilder vS = new StringBuilder();

        for(int vInt : aKeys){
            vS.append("<").append(vInt).append("> ");
        }

        return vS.toString().trim();
    }
}