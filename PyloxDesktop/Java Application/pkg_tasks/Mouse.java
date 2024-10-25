package pkg_tasks;

import java.util.ArrayList;

/**
 * Tâche consistant à l'éxécution de comportements de souris
 *
 * @author Charles A
 * @version 05/06/2024
 */
public class Mouse extends Task
{
    private static final String TYPE = "Mouse";
    
    private ArrayList<String> aList;
    
    /**
     * Constructeur d'objets de classe Mouse
     */
    public Mouse()
    {
        super(TYPE);
        
        this.aList = new ArrayList<String>();
    }
    
    /**
     * Représentation graphique de la tâche
     */
    @Override public String toString(){
        return "";
    }
}
