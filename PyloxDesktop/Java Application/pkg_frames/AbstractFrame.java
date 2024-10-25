package pkg_frames;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Décrivez votre classe abstraite AbstractFrame ici.
 *
 * @author Charles A
 * @version 09/06/2024
 */
public abstract class AbstractFrame extends JFrame implements ActionListener, WindowListener
{
    private final boolean PRINT = false;
    
    /**
     * Constructeur de Frames
     */
    public AbstractFrame(final String pName){
        super(pName);

        //this.pack();
        //this.setVisible(true);
        
        this.addWindowListener(this);
    }

    /**
     * Méthode de l'interface ActionListener
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowActivated( final WindowEvent pE ) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowDeactivated( final WindowEvent pE ) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowIconified( final WindowEvent pE ) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowDeiconified(final WindowEvent pE) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowOpened(final WindowEvent pE) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowClosing(final WindowEvent pE) 
    {
        this.printEvent(pE);
    }

    /**
     * Méthode de l'interface WindowListener
     */
    @Override public void windowClosed(final WindowEvent pE) 
    {
        this.printEvent(pE);
    }

    private void printEvent(final WindowEvent pE){
        if (PRINT) {
            System.out.println(pE.paramString());
        }
    }
    
    private void printEvent(final ActionEvent pE){
        if (PRINT) {
            System.out.println(pE.paramString());
        }
    }
}
