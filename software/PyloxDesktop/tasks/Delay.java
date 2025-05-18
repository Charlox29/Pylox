package tasks;


/**
 * Task of waiting for a certain time in milliseconds.
 * The time to wait is represented in an integer (long) type variable.
 * By default, time is zero.
 *
 * @author Charles A
 *
 * @version 05/18/2025
 *
 * @see Task
 */
public class Delay extends Task
{
    private static final long serialVersionUID = 1L;

    private static final String TYPE = "Delay";
    
    private long aTime;
    
    /**
     * Constructs Delay objects.
     */
    public Delay()
    {
        super(TYPE);
        
        aTime = 0;
    }
    
    /**
     * Time accessor
     *
     * @return the time in milliseconds
     */
    public long getTime(){
        return aTime;
    }

    /**
     * Time modifier
     *
     * @param pTime the time in milliseconds
     */
    public void setTime(final long pTime){
        aTime = pTime;
    }

    @Override public Delay clone(){
        Delay vDelay = new Delay();

        vDelay.setTime(aTime);

        return vDelay;
    }

    @Override public String getDescription() {
        return aTime + "ms";
    }
}