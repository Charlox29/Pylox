package tasks;


/**
 * A task is an action to be performed.
 * This abstract class allows you to describe how any task should be scheduled and behave.
 *
 * @author Charles A
 * @version 29/10/2024
 */
public abstract class Task
{
    private String aTaskType;

    /**
     * Constructor of Task class objects
     *
     * @param pTaskType Task type
     */
    public Task(final String pTaskType){
        aTaskType = pTaskType;
    }
    
    /**
     * Get task type
     *
     * @return Task type in upper case
     */
    public String getStringType(){
        return aTaskType.toUpperCase();
    }

    /**
     * Graphical representation of the task description
     *
     * @return Task description
     */
    public abstract String getDescription();

    @Override public String toString(){
        return getStringType() + ": " + getDescription();
    }
}