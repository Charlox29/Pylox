package pkg_frames;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.border.Border;

import pkg_tasks.TaskList;
import pkg_tasks.Task;
import pkg_tasks.Delay;
import pkg_tasks.Macro;
import pkg_tasks.Mouse;
import pkg_tasks.Text;

import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Interface graphique de l'application
 *
 * @author Charles A
 * @version 19/08/2024
 */
public class MainFrame extends AbstractFrame
{
    private JMenuItem aNew, aImport, aSave, aSaveAs, aPreferences, aQuit, aAbout;

    private ArrayList<JButton> aButtons;

    /**
     * Constructeur d'interfaces graphiques
     * 
     * @param pRows nombre de lignes de boutons
     * @param pColumns nombre de colonnes de boutons
     */
    public MainFrame(final int pRows, final int pColumns)
    {
        super("Pylox Desktop");
        this.setBackground(new Color(30, 33, 138));

        // MENU BAR
        JMenuBar vMenuBar = new JMenuBar();

        JMenu vMenu = new JMenu("Files");

        this.aNew = new JMenuItem("New");
        this.aImport = new JMenuItem("Import");
        this.aSave = new JMenuItem("Save");
        this.aSaveAs = new JMenuItem("Save as");
        this.aPreferences = new JMenuItem("Preferences");
        this.aQuit = new JMenuItem("Quit");
        this.aAbout = new JMenuItem("About");

        this.aNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        this.aImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        this.aSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        this.aSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK));

        this.aNew.setMnemonic('N');
        this.aImport.setMnemonic('I');
        this.aSave.setMnemonic('S');
        this.aPreferences.setMnemonic('P');

        vMenu.add(this.aNew);
        vMenu.add(this.aImport);
        vMenu.add(this.aSave);
        vMenu.add(this.aSaveAs);
        vMenu.addSeparator();
        vMenu.add(this.aPreferences);
        vMenu.add(this.aQuit);
        vMenu.add(this.aAbout);

        vMenuBar.add(vMenu);

        this.aNew.addActionListener(this);
        this.aImport.addActionListener(this);
        this.aSave.addActionListener(this);
        this.aSaveAs.addActionListener(this);
        this.aPreferences.addActionListener(this);
        this.aQuit.addActionListener(this);
        this.aAbout.addActionListener(this);

        this.setJMenuBar(vMenuBar);



        // West Panel
        JPanel vWestPanel = new JPanel();
        vWestPanel.setLayout(new GridLayout(1,1));
        vWestPanel.add(new JButton("Encoder"));
        vWestPanel.setBackground(new Color(110, 22, 22));


        // Center Panel
        JPanel vCenterPanel = new JPanel();
        vCenterPanel.setBackground(new Color(198, 77, 77));
        vCenterPanel.setLayout(new GridLayout(pRows,pColumns, 10,10));

        this.aButtons = new ArrayList<JButton>();

        for (int i = 0; i<pRows*pColumns; i++){
            JButton vButton = new JButton("Bouton " + (i + 1));

            this.aButtons.add(vButton);

            vCenterPanel.add(vButton);

            vButton.addActionListener(this);
        }


        GroupLayout vMainLayout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(vMainLayout);

        vMainLayout.setHorizontalGroup(
                vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vMainLayout.createSequentialGroup()
                                //.addGap(5)
                                .addComponent(vWestPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                //.addGap(5)
                                .addComponent(vCenterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                //.addGap(5)
                        )
        );

        vMainLayout.setVerticalGroup(
                vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vMainLayout.createSequentialGroup()
                                //.addGap(5)
                                .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(vWestPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        //.addGap(5)
                                        .addComponent(vCenterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                //.addGap(5)
                        )
        );

        /*vMainLayout.setHorizontalGroup(
                vMainLayout.createSequentialGroup()
                        .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        //.addGap(5)
                                        .addComponent(vWestPanel)
                                        //.addGap(5)
                                        .addComponent(vCenterPanel)
                                //.addGap(5)
                        )
        );

        vMainLayout.setVerticalGroup(
                vMainLayout.createSequentialGroup()
                        .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        //.addGap(5)
                                        .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(vWestPanel)
                                                //.addGap(5)
                                                .addComponent(vCenterPanel)
                                        )
                                //.addGap(5)
                        )
        );*/

        // https://www.javatpoint.com/java-grouplayout
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/groupExample.html

        //this.getContentPane().add(vWestPanel, BorderLayout.WEST);
        //this.getContentPane().add(vCenterPanel, BorderLayout.CENTER);


        //this.aWindowFrame.addWindowListener(this);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setSize(800, 400);
        this.setJMenuBar(vMenuBar);
    }

    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        if (this.aButtons.contains(pE.getSource())){
            TaskList vTaskList = new TaskList();
            Macro vMacro = new Macro();
            Text vText = new Text();
            Delay vDelay = new Delay();
            Mouse vMouse = new Mouse();

            vTaskList.add(vText);
            vTaskList.add(vMacro);
            vTaskList.add(vDelay);
            vTaskList.add(vMouse);

            TasksManageFrame vButtonFrame = new TasksManageFrame(this, vTaskList);

            vButtonFrame.setLocationRelativeTo(this);
        }
        else if (pE.getSource() == this.aQuit){
            this.dispose();
        }
        else{
            System.out.println(pE.getActionCommand());
        }
    }
}