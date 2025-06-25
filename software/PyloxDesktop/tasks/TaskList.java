package tasks;

import java.util.ArrayList;

import java.io.Serializable;

/**
 * Allows the best management of a task list.
 * By default, the task list is empty.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 *
 * @see Task
 * @see ArrayList
 */
public class TaskList implements Serializable
{
    private static final long serialVersionUID = 1L;

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
     * Replaces a task of a certain index with another task.
     */
    public void set(final int pIndex, final Task pTask){
        aTasks.set(pIndex, pTask);
    }
    
    /**
     * Gets a task from the list by its index.
     */
    public Task get(final int pIndex){
        return aTasks.get(pIndex);
    }

    /**
     * Removes a task from the list.
     */
    public void remove(final int pIndex){
        aTasks.remove(pIndex);
    }
    
    /**
     * Clears all tasks from the list.
     */
    public void clear(){
        aTasks.clear();
    }

    /**
     * Performs all tasks.
     */
    public void executeAll() {
        for(Task pTask : aTasks){
            pTask.execute();
        }
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