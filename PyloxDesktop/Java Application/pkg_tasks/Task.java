package pkg_tasks;


/**
 * Décrivez votre classe abstraite Task ici.
 *
 * @author Charles A
 * @version 05/06/2024
 */
public abstract class Task
{
    private String aTaskType;
    
    public Task(final String pTaskType){
        this.aTaskType = pTaskType;
    }
    
    /**
     * Obtenir le type de la tâche
     */
    public String getStringType(){
        return this.aTaskType.toUpperCase();
    }
    
    /**
     * Représentation graphique de la tâche
     */
    @Override public abstract String toString();
}
