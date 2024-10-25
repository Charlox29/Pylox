package pkg_frames;

import javax.swing.*;
import javax.swing.GroupLayout.Group;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import pkg_tasks.Task;
import pkg_tasks.TaskList;
import pkg_tasks.Macro;
import pkg_tasks.Text;
import pkg_tasks.Mouse;
import pkg_tasks.Delay;

/**
 * Décrivez votre classe TasksManageFrame ici.
 *
 * @author Charles A
 * @version 19/08/2024
 */
public class TasksManageFrame extends AbstractFrame{
    private JFrame aParent;
    private TaskList aTaskList;

    // Swing components
    private JPanel aCenterPanel;

    private JList<String> aList;

    /**
     * Constructeur d'objets de classe TasksManageFrame
     * 
     * @param pParent
     * @param pTaskList
     */
    public TasksManageFrame(final JFrame pParent, final TaskList pTaskList)
    {
        super("Button");

        this.aParent = pParent;
        this.aTaskList = pTaskList;


        // West Panel
        JPanel vWestPanel = new JPanel();
        vWestPanel.setBackground(new Color(22, 27, 147));

        DefaultListModel<String> vModel = new DefaultListModel<>();
        this.aList = new JList<String>(vModel);
        this.aList.setDragEnabled(true);
        this.aList.setDropMode(DropMode.USE_SELECTION);

        for (int i = 0; i < this.aTaskList.size(); i++) {
            vModel.addElement(this.aTaskList.get(i).getStringType());
        }
        
        JScrollPane vScrollPane = new JScrollPane(this.aList);

        JButton vButton = new JButton("Add Task");


        GroupLayout vWestPanelLayout = new GroupLayout(vWestPanel);
        vWestPanel.setLayout(vWestPanelLayout);

        vWestPanelLayout.setHorizontalGroup(
                vWestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vWestPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(vWestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(vButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vScrollPane))
                                .addContainerGap())
        );

        vWestPanelLayout.setVerticalGroup(
                vWestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vWestPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(vScrollPane, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        //vWestPanel.add(this.aList);




        // Center Panel
        this.aCenterPanel = new TaskEditorPanel(this.aTaskList.get(0));
        this.aCenterPanel.setBackground(new Color(161, 6, 6));


        // this
        GroupLayout vLayout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(vLayout);

        vLayout.setHorizontalGroup(
                vLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vLayout.createSequentialGroup()
                                 //.addComponent(vWestPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vWestPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 //.addComponent(this.aCenterPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(this.aCenterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
        );

        vLayout.setVerticalGroup(
                vLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vWestPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //addComponent(vWestPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.aCenterPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //.addComponent(this.aCenterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );


        //this.getContentPane().add(vWestPanel, BorderLayout.WEST);
        //this.getContentPane().add(this.aCenterPanel, BorderLayout.CENTER);



        this.pack();
        this.setVisible(true);

        this.setLocationRelativeTo(this.aParent);

        this.aParent.setEnabled(false);

        this.setSize(500,500); // TEST
    }

    @Override public void actionPerformed(final ActionEvent pE) 
    {
        System.out.println(pE.getActionCommand());
    }

    @Override public void windowClosing(final WindowEvent pE)
    {
        this.aParent.setEnabled(true);

        this.aParent.toFront();
    }
}

class TaskEditorPanel extends JPanel{
    private Task aTask;

    public TaskEditorPanel(final Task pTask){
        this.aTask = pTask;

        // Layout
        GroupLayout vTaskEditorLayout = new GroupLayout(this);
        this.setLayout(vTaskEditorLayout);

        Group vHorizontalSequentialGroup  = vTaskEditorLayout.createSequentialGroup();
        Group vVerticalSequentialGroup  = vTaskEditorLayout.createSequentialGroup();

        switch(this.aTask.getStringType()){
            case "MACRO":
            case "DELAY":
            case "MOUSE":
                JLabel vLabel = new JLabel(this.aTask.getStringType());
                vLabel.setSize(60,30);
                vLabel.setBackground(new java.awt.Color(255, 154, 154));

                JButton vButton = new JButton(this.aTask.getStringType());
                vButton.setSize(60,30);
                vButton.setBackground(new java.awt.Color(255, 154, 154));

                break;

            case "TEXT":
            default:

                JTextArea vTextArea = new JTextArea();

                vHorizontalSequentialGroup
                        .addGap(2)
                        .addComponent(vTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGap(2);

                vVerticalSequentialGroup
                        .addGap(2)
                        .addComponent(vTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGap(2);

                break;
        }


        vTaskEditorLayout.setHorizontalGroup(
                vTaskEditorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vHorizontalSequentialGroup)
        );

        vTaskEditorLayout.setVerticalGroup(
                vTaskEditorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(vVerticalSequentialGroup)
        );
    }
}