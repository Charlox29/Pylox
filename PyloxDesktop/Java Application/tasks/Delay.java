package tasks;


/**
 * Task of waiting for a certain time in milliseconds.
 * The time to wait is represented in an integer (long) type variable.
 * By default, time is zero.
 *
 * @author Charles A
 * @version 29/10/2024
 */
public class Delay extends Task
{
    private static final String TYPE = "Delay";
    
    private long aTime;
    
    /**
     * Constructor of Delay class objects
     */
    public Delay()
    {
        super(TYPE);
        
        aTime = 0;
    }
    
    /**
     * Time accessor
     *
     * @return Time in milliseconds
     */
    public long getTime(){
        return aTime;
    }

    /**
     * Time modifier
     *
     * @param pTime Time in milliseconds
     */
    public void setTime(final int pTime){
        aTime = pTime;
    }

    @Override public String getDescription() {
        return aTime + "ms";
    }
}