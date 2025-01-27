package tasks;

import java.util.ArrayList;


/**
 * This class allows the best management of a task list.
 * By default, the task list is empty.
 *
 * @author Charles A
 * @version 29/10/2024
 *
 *
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/DefaultListModel.html
 */
public class TaskList
{
    private ArrayList<Task> aTasks;

    /**
     * Constructor of TaskList class objects
     */
    public TaskList(){
        aTasks = new ArrayList<Task>();
    }

    /**
     * Get the length of the list
     *
     * @return The number of tasks the list has
     */
    public int size(){
        return aTasks.size();
    }

    /**
     * Know if the list contains no tasks
     *
     * @return True if the list is empty
     */
    public boolean isEmpty(){
        return aTasks.isEmpty();
    }

    /**
     * Add a task to the list
     *
     * @param pTask The task to add
     */
    public void add(final Task pTask){
        aTasks.add(pTask);
    }
    
    /**
     * Ajouter une tâche à la liste à un certain index
     */
    public void add(final int pIndex, final Task pTask){
        aTasks.add(pIndex, pTask);
    }
    
    /**
     * Remplacer une tâche d'un certain indice par une autre tâche
     */
    public void set(final int pIndex, final Task pTask){
        aTasks.set(pIndex, pTask);
    }
    
    /**
     * Obtenir une tâche de la liste depuis son indice
     */
    public Task get(final int pIndex){
        return aTasks.get(pIndex);
    }

    /**
     * Retirer une tâche de la liste
     */
    public void remove(final Task pTask){
        aTasks.remove(pTask);
    }

    /**
     * Retirer une tâche de la liste
     */
    public void remove(final int pIndex){
        aTasks.remove(pIndex);
    }
    
    /**
     * Effacer tous les élements de la liste
     */
    public void clear(){
        aTasks.clear();
    }

    @Override public String toString(){
        if (aTasks.isEmpty()) return "Empty TaskList";

        StringBuilder vS = new StringBuilder();

        for(Task vTask : aTasks){
            vS.append("<").append(vTask.toString()).append("> ");
        }

        return vS.toString().trim();
    }
}