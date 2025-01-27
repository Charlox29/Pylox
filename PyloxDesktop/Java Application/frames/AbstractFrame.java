package frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Une AbstractFrame est le modèle d'une fenêtre graphique de l'application.
 * Par défaut, elle possède un Panel principal dans lequel tous les composants doivent être ajoutés.
 *
 * @author Charles A
 * @version 30/10/2024
 */
public abstract class AbstractFrame extends JFrame implements ActionListener, WindowListener
{
    // FOR DEVS
    private final static boolean PRINT = false;

    private final JPanel aMainPanel;

    /**
     * Constructor of AbstractFrame class objects
     *
     * @param pName Frame's title
     */
    public AbstractFrame(final String pName){
        super(pName);

        aMainPanel = new JPanel();

        add(aMainPanel);

        ImageIcon vIcone = new ImageIcon(new File("xD.png").getAbsolutePath());

        setIconImage(vIcone.getImage());
        
        addWindowListener(this);
    }

    /**
     *
     */
    public JPanel getMainPanel(){
        return aMainPanel;
    }

    @Override public void actionPerformed(final ActionEvent pE)
    {
        printEvent(pE);
    }

    @Override public void windowActivated(final WindowEvent pE)
    {
        printEvent(pE);
    }

    @Override public void windowDeactivated(final WindowEvent pE)
    {
        printEvent(pE);
    }

    @Override public void windowIconified(final WindowEvent pE)
    {
        printEvent(pE);
    }

    @Override public void windowDeiconified(final WindowEvent pE) 
    {
        printEvent(pE);
    }

    @Override public void windowOpened(final WindowEvent pE) 
    {
        printEvent(pE);
    }

    @Override public void windowClosing(final WindowEvent pE) 
    {
        printEvent(pE);
    }

    @Override public void windowClosed(final WindowEvent pE) 
    {
        printEvent(pE);
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
