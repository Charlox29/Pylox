package pkg_tasks;

import java.util.ArrayList;


/**
 * Tâche consistant à l'éxécution d'un raccourci
 *
 * @author Charles A
 * @version 23/06/2024
 */
public class Macro extends Task
{
    private static final String TYPE = "Macro";
    
    private ArrayList<String> aList;
    
    /**
     * Constructeur d'objets de classe Macro
     */
    public Macro()
    {
        super(TYPE);
        
        this.aList = new ArrayList<String>();
    }
    
    /**
     * Obtenir la taille de la liste
     */
    public int size(){
        return this.aList.size();
    }
    
    /**
     * Ajouter une touche à la macro
     */
    public void add(final String pString){
        this.aList.add(pString);
    }
    
    /**
     * Ajouter une touche à la macro à un certain index
     */
    public void add(final int pIndex, final String pString){
        this.aList.add(pIndex, pString);
    }
    
    /**
     * Remplacer une touche d'un certain indice par une autre touche
     */
    public void set(final int pIndex, final String pString){
        this.aList.set(pIndex, pString);
    }
    
    /**
     * Obtenir une touche de la macro depuis son indice
     */
    public String get(final int pIndex){
        return this.aList.get(pIndex);
    }

    /**
     * Retirer une touche de la macro
     */
    public void remove(final String pString){
        this.aList.remove(pString);
    }
    
    /**
     * Effacer toutes les macro de la liste
     */
    public void clear(){
        this.aList.clear();
    }
    
    /**
     * Représentation graphique de la macro
     */
    @Override public String toString(){
        return TYPE + ": ";
    }
}
