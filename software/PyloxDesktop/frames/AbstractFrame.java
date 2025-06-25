package frames;

import javax.swing.*;

import java.awt.*;
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
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Définir police et couleurs globales
            UIManager.put("Panel.background", new Color(20, 20, 20));
            UIManager.put("Button.background", new Color(50, 50, 50));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("List.background", new Color(50, 50, 50));
            UIManager.put("List.foreground", Color.WHITE);
            UIManager.put("TextField.background", new Color(40, 40, 40));
            UIManager.put("TextField.foreground", Color.WHITE);
            UIManager.put("TextArea.background", new Color(40, 40, 40));
            UIManager.put("TextArea.foreground", Color.WHITE);
            UIManager.put("ComboBox.background", new Color(50, 50, 50));
            UIManager.put("ComboBox.foreground", Color.WHITE);
            UIManager.put("ScrollPane.background", new Color(20, 20, 20));
            UIManager.put("Menu.foreground", Color.WHITE);
            UIManager.put("Menu.background", new Color(50, 50, 50));
            UIManager.put("MenuItem.foreground", Color.WHITE);
            UIManager.put("MenuItem.background", new Color(50, 50, 50));
            UIManager.put("OptionPane.background", new Color(20, 20, 20));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("PopupMenu.background", new Color(50, 50, 50));
            UIManager.put("PopupMenu.foreground", Color.WHITE);
            UIManager.put("TextField.caretForeground", Color.WHITE);
            UIManager.put("TextArea.caretForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(50, 50, 50));
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Button.select", new Color(70, 70, 70));
            UIManager.put("Menu.foreground", Color.BLACK);
            UIManager.put("MenuItem.foreground", Color.BLACK);
            UIManager.put("Menu.background", Color.WHITE);
            UIManager.put("MenuItem.background", Color.WHITE);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("ComboBox.foreground", Color.BLACK);

            // Police globale
            Font font = new Font("Segoe UI", Font.PLAIN, 12);
            UIManager.put("Label.font", font);
            UIManager.put("Button.font", font);
            UIManager.put("Menu.font", font);
            UIManager.put("MenuItem.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("TextArea.font", font);
            UIManager.put("ComboBox.font", font);
            UIManager.put("List.font", font);
            UIManager.put("OptionPane.font", font);
        } catch (Exception ignored) {}
    }

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
