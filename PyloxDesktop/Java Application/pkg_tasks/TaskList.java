package pkg_tasks;

import java.util.ArrayList;

/**
 * TaskList permet de gérer facilement une liste de tâches
 *
 * @author Charles A
 * @version 05/06/2024
 */
public class TaskList
{
    private ArrayList<Task> aTasks;

    /**
     * Constructeur d'objets de classe TaskList
     */
    public TaskList(){
        this.aTasks = new ArrayList<Task>();
    }
    
    /**
     * Obtenir la taille de la liste
     */
    public int size(){
        return this.aTasks.size();
    }

    /**
     * Ajouter une tâche à la liste
     */
    public void add(final Task pTask){
        this.aTasks.add(pTask);
    }
    
    /**
     * Ajouter une tâche à la liste à un certain index
     */
    public void add(final int pIndex, final Task pTask){
        this.aTasks.add(pIndex, pTask);
    }
    
    /**
     * Remplacer une tâche d'un certain indice par une autre tâche
     */
    public void set(final int pIndex, final Task pTask){
        this.aTasks.set(pIndex, pTask);
    }
    
    /**
     * Obtenir une tâche de la liste depuis son indice
     */
    public Task get(final int pIndex){
        return this.aTasks.get(pIndex);
    }

    /**
     * Retirer une tâche de la liste
     */
    public void remove(final Task pTask){
        this.aTasks.remove(pTask);
    }
    
    /**
     * Effacer tous les élements de la liste
     */
    public void clear(){
        this.aTasks.clear();
    }
}
