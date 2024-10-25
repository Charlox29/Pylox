package pkg_tasks;


/**
 * Tâche consistant à attendre un certain temps
 *
 * @author Charles A
 * @version 22/06/2024
 */
public class Delay extends Task
{
    private static final String TYPE = "Delay";
    
    private int aTime;
    
    /**
     * Constructeur d'objets de classe Text
     */
    public Delay()
    {
        super(TYPE);
        
        this.aTime = 0;
    }
    
    /**
     * Accesseur du temps
     */
    public int getTime(){
        return this.aTime;
    }
    
    /**
     * Modifieur du temps
     */
    public void setTime(final int pTime){
        this.aTime = pTime;
    }
    
    /**
     * Représentation graphique de la tâche
     */
    @Override public String toString(){
        return TYPE + ": " + this.aTime + "ms";
    }
}
