package tasks;

import java.io.Serializable;

/**
 * Allows you to describe how any task should be scheduled and behave.
 * A task is an action to be performed.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 */
public abstract class Task implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final String aTaskType;

    /**
     * Constructs Task objects.
     *
     * @param pTaskType the Task's type
     */
    public Task(final String pTaskType){
        aTaskType = pTaskType;
    }
    
    /**
     * Gets Task's type.
     *
     * @return the type in upper case
     */
    public String getStringType(){
        return aTaskType.toUpperCase();
    }

    /**
     * Represents the task description graphically.
     *
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Clones a task.
     *
     * @return a task with the same properties as the one passed as a parameter
     */
    public abstract Task clone();

    @Override public String toString(){
        return getStringType() + ": " + getDescription();
    }
}