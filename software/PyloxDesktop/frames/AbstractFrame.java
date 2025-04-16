package frames;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.io.File;

/**
 * AbstractFrame is a model for all frames in the application.
 * Une AbstractFrame est le modèle d'une fenêtre graphique de l'application.
 * Par défaut, elle possède un Panel principal dans lequel tous les composants doivent être ajoutés.
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see JFrame
 */
public abstract class AbstractFrame extends JFrame implements ActionListener, WindowListener
{
    private final JPanel aMainPanel;

    /**
     * Constructs AbstractFrame objects.
     *
     * @param pName frame's title
     */
    public AbstractFrame(final String pName)
    {
        super(pName);

        aMainPanel = new JPanel();

        add(aMainPanel);

        ImageIcon vIcone = new ImageIcon(new File("xD.png").getAbsolutePath());

        setIconImage(vIcone.getImage());
        
        addWindowListener(this);
    }

    /**
     * Gets the main panel that is already added in the frame.
     *
     * @return the main panel
     */
    public JPanel getMainPanel()
    {
        return aMainPanel;
    }

    @Override public void actionPerformed(final ActionEvent pE)
    {
    }

    @Override public void windowActivated(final WindowEvent pE)
    {
    }

    @Override public void windowDeactivated(final WindowEvent pE)
    {
    }

    @Override public void windowIconified(final WindowEvent pE)
    {
    }

    @Override public void windowDeiconified(final WindowEvent pE) 
    {
    }

    @Override public void windowOpened(final WindowEvent pE) 
    {
    }

    @Override public void windowClosing(final WindowEvent pE) 
    {
    }

    @Override public void windowClosed(final WindowEvent pE) 
    {
    }
}
