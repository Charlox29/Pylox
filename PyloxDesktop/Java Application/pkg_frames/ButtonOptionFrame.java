package pkg_frames;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import pkg_tasks.Task;

/**
 * Décrivez votre classe ButtonFrame ici.
 *
 * @author Charles A
 * @version 09/05/2024
 */
public class ButtonOptionFrame extends AbstractFrame{
    private JFrame aParent;

    private JList<String> aList;

    /**
     * Constructeur d'objets de classe ButtonFrame
     */
    public ButtonOptionFrame(final JFrame vParent)
    {
        super("Button");

        this.aParent = vParent;

        DefaultListModel<String> model = new DefaultListModel<>();
        this.aList = new JList<String>(model);

        // Add items to the model dynamically
        for (int i = 0; i < 100; i++) {
            model.addElement("Item " + i);
        }
        
        this.add(this.aList);

        this.pack();
        this.setVisible(true);

        this.setLocationRelativeTo(this.aParent);

        this.aParent.setEnabled(false);
    }

    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        System.out.println(pE.getActionCommand());
    }

    @Override public void windowClosing(final WindowEvent pE){
        this.aParent.setEnabled(true);

        this.aParent.toFront();
    }
}
