package tasks;

import java.util.ArrayList;


/**
 * Allows the best management of a task list.
 * By default, the task list is empty.
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see Task
 * @see ArrayList
 */
public class TaskList
{
    private ArrayList<Task> aTasks;

    /**
     * Constructs TaskList objects.
     */
    public TaskList(){
        aTasks = new ArrayList<Task>();
    }

    /**
     * Gets the length of the list.
     *
     * @return the number of tasks the list has
     */
    public int size(){
        return aTasks.size();
    }

    /**
     * Knows if the list contains any task.
     *
     * @return <code>true</code> if the list is empty; <code>false</code> otherwise.
     */
    public boolean isEmpty(){
        return aTasks.isEmpty();
    }

    /**
     * Adds a task to the list.
     *
     * @param pTask the task to add
     */
    public void add(final Task pTask){
        aTasks.add(pTask);
    }
    
    /**
     * Adds a task to the list at a specified index.
     *
     * @param pIndex the index to put the task in
     * @param pTask the task to add
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