package pkg_tasks;


/**
 * Tâche consistant à écrire un texte
 *
 * @author Charles A
 * @version 05/06/2024
 */
public class Text extends Task
{
    private static final String TYPE = "Text";
    
    private String aText;
    
    /**
     * Constructeur d'objets de classe Text
     */
    public Text()
    {
        super(TYPE);
        this.aText = "";
    }
    
    /**
     * Accesseur du texte
     */
    public String getText(){
        return this.aText;
    }
    
    /**
     * Modifieur du texte
     */
    public void setText(final String pText){
        this.aText = pText;
    }
    
    /**
     * Représentation graphique de la tâche
     */
    @Override public String toString(){
        return TYPE + ": <<" + this.aText + ">>";
    }
}
