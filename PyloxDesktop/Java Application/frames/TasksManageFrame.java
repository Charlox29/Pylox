package frames;

import javax.crypto.Mac;
import javax.swing.*;
import javax.swing.GroupLayout.Group;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;
import tasks.Macro;
import tasks.Text;
import tasks.Mouse;
import tasks.Delay;

/**
 * @author Charles A
 * @version 20/01/2025
 */
public class TasksManageFrame extends AbstractFrame {
    private final JFrame aParent;
    private final TaskList aTaskList;

    private JPanel aCenterPanel;

    private final JList<String> aList;
    private final JButton aAddButton;

    private Task aCurrentTask;

    /**
     * Constructor of TasksManageFrame class objects
     * 
     * @param pParent
     * @param pTaskList
     */
    public TasksManageFrame(final JFrame pParent, final TaskList pTaskList)
    {
        super("Button");

        aParent = pParent;
        aTaskList = pTaskList;

        // A REMPLACER
        aCenterPanel = new DelayEditorJPanel(new Delay());


        // West Panel
        JPanel vWestPanel = new JPanel();
        vWestPanel.setBackground(new Color(22, 27, 147));

        aList = new myJList(aTaskList);

        JScrollPane vScrollPane = new JScrollPane(aList);

        aAddButton = new JButton("Add Task");

        GroupLayout vWestPanelLayout = new GroupLayout(vWestPanel);
        vWestPanel.setLayout(vWestPanelLayout);

        vWestPanelLayout.setHorizontalGroup(vWestPanelLayout.createSequentialGroup()
                .addGroup(vWestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vScrollPane)

                        .addComponent(aAddButton)
                )
        );

        vWestPanelLayout.setVerticalGroup(vWestPanelLayout.createSequentialGroup()
                .addComponent(vScrollPane,
                        GroupLayout.DEFAULT_SIZE,
                        100,
                        Short.MAX_VALUE)

                .addGap(5)

                .addComponent(aAddButton,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE)
        );


        // Main Panel
        JPanel vMainPanel = getMainPanel();
        vMainPanel.setBackground(new Color(20, 20, 20));

        GroupLayout vMainLayout = new GroupLayout(vMainPanel);
        vMainPanel.setLayout(vMainLayout);

        vMainLayout.setHorizontalGroup(vMainLayout.createSequentialGroup()
                .addGap(5)

                /*.addComponent(vScrollPane,
                        GroupLayout.DEFAULT_SIZE,
                        100,
                        250)*/

                .addComponent(vWestPanel,
                        vWestPanel.getMinimumSize().width,
                        vWestPanel.getPreferredSize().width,
                        vWestPanel.getMaximumSize().width
                )

                .addGap(5)

                .addComponent(aCenterPanel,
                        aCenterPanel.getMinimumSize().width,
                        aCenterPanel.getPreferredSize().width,
                        Short.MAX_VALUE)

                .addGap(5)
        );

        vMainLayout.setVerticalGroup(vMainLayout.createSequentialGroup()
                .addGap(5)

                .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        /*.addComponent(vScrollPane,
                                GroupLayout.DEFAULT_SIZE,
                                100,
                                Short.MAX_VALUE)*/

                        .addComponent(vWestPanel,
                                vWestPanel.getMinimumSize().height,
                                vWestPanel.getPreferredSize().height,
                                vWestPanel.getMaximumSize().height
                        )

                        .addComponent(aCenterPanel,
                                aCenterPanel.getMinimumSize().height,
                                aCenterPanel.getPreferredSize().height,
                                Short.MAX_VALUE)
                )

                .addGap(5)
        );


        pack();

        setVisible(true);

        setLocationRelativeTo(aParent);
        aParent.setEnabled(false);

        // Dimensions
        double vScaleFactor = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0;

        Dimension vMainPanelMinSize = vMainPanel.getMinimumSize();

        Insets vInsets = getInsets();

        Dimension vMinSize = new Dimension(
                (int) ((vMainPanelMinSize.width + vInsets.left + vInsets.right) * vScaleFactor),
                (int) ((vMainPanelMinSize.height + vInsets.top + vInsets.bottom) * vScaleFactor)
        );

        setMinimumSize(vMinSize);
    }

    /*@Override public void actionPerformed(final ActionEvent pE)
    {
        System.out.println(pE.getActionCommand());
    }*/

    @Override public void windowClosing(final WindowEvent pE)
    {
        aParent.setEnabled(true);

        aParent.toFront();
    }


    /*private static class TaskListManagerPanel extends JPanel {
        private static TaskList aTaskList;

        private final JList<String> aList;

        public TaskListManagerPanel(final TaskList pTaskList){
            super();

            aTaskList = pTaskList;

            setBackground(new Color(185, 22, 223));

            setMinimumSize(new Dimension(200, 200));
            setPreferredSize(new Dimension(500, 300));
            setMaximumSize(new Dimension(500, 300));

            DefaultListModel<String> vModel = new DefaultListModel<>();
            aList = new JList<String>(vModel);
            aList.setDragEnabled(true);
            aList.setDropMode(DropMode.USE_SELECTION);

            for (int i = 0; i < aTaskList.size(); i++) {
                vModel.addElement(aTaskList.get(i).getStringType());
            }

            JScrollPane vScrollPane = new JScrollPane(aList);

            JButton vButton = new JButton("Add Task");


            GroupLayout vMainLayout = new GroupLayout(this);
            setLayout(vMainLayout);

            vMainLayout.setHorizontalGroup(vMainLayout.createSequentialGroup()
                    .addContainerGap()

                    .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(vButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                            .addComponent(vScrollPane))

                    .addContainerGap()
            );

            vMainLayout.setVerticalGroup(vMainLayout.createSequentialGroup()
                    .addContainerGap()

                    .addComponent(vScrollPane, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)

                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

                    .addComponent(vButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                    .addContainerGap()
            );
        }
    }*/


    private class myJList extends JList<String> {
        private TaskList aTaskList;

        private PopupMenu aPopupMenu;

        private DefaultListModel<String> aModel;

        public myJList(final TaskList pTaskList) {
            super();

            aTaskList = pTaskList;

            aPopupMenu = new PopupMenu();

            aModel = new DefaultListModel<>();

            setModel(aModel);

            for (int i = 0; i < aTaskList.size(); i++) {
                aModel.addElement(aTaskList.get(i).getStringType());
            }

            //aModel.addElement("+");


            setDragEnabled(true);
            setDropMode(DropMode.INSERT);

            setTransferHandler(new TransferHandler(){
                @Override
                public boolean canImport(TransferHandler.TransferSupport support) {
                    return support.isDataFlavorSupported(DataFlavor.stringFlavor);
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                    return new StringSelection(((JList<String>) c).getSelectedValue());
                }

                @Override
                public int getSourceActions(JComponent c) {
                    return TransferHandler.MOVE;
                }

                @Override
                public boolean importData(TransferHandler.TransferSupport support) {
                    if (!canImport(support)) {
                        return false;
                    }

                    int vIndexFrom = aList.getSelectedIndex();
                    int vIndexTo = ((JList.DropLocation) support.getDropLocation()).getIndex();

                    if (vIndexFrom < 0 || vIndexFrom >= vIndexTo) {
                        return false;
                    }

                    Task vTask = aTaskList.get(vIndexFrom);

                    aModel.remove(vIndexFrom);
                    aTaskList.remove(vIndexFrom);

                    int c = vIndexTo;

                    if (c > aModel.size()) {
                        c = aModel.size();
                    }

                    aModel.add(c, vTask.getStringType());
                    aTaskList.add(c, vTask);

                    aList.setSelectedIndex(c);


                    return true;
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e) && aList.getSelectedIndex() != -1) {
                        aPopupMenu.show(aList, e.getX(), e.getY());
                    }
                    else if (SwingUtilities.isLeftMouseButton(e) && aList.getSelectedIndex() != -1) {
                        int index = aList.getSelectedIndex();

                        // BLOCK LIST

                        Task selectedTask = aTaskList.get(index);

                        JPanel vNewPanel = getEditorJPanel(selectedTask);

                        ((GroupLayout) getMainPanel().getLayout()).replace(aCenterPanel, vNewPanel);

                        aCenterPanel = vNewPanel;

                        // RELEASE LIST

                        getMainPanel().revalidate();
                        getMainPanel().repaint();
                    }
                    else {
                        System.out.println(e);
                    }
                }
            });
        }

        private class PopupMenu extends JPopupMenu{
            public PopupMenu(){
                super();

                JMenuItem vDuplicateItem = new JMenuItem("Duplicate");
                JMenuItem vDeleteItem = new JMenuItem("Delete");

                add(vDuplicateItem);
                add(vDeleteItem);


                vDuplicateItem.addActionListener(e -> {
                    int vSelectedIndex = aList.getSelectedIndex();

                    if (vSelectedIndex != -1) {
                        String selectedTask = aList.getModel().getElementAt(vSelectedIndex);

                        aModel.add(vSelectedIndex + 1, selectedTask);
                    }
                });

                vDeleteItem.addActionListener(e -> {
                    int vSelectedIndex = aList.getSelectedIndex();

                    if (vSelectedIndex != -1) {
                        aModel.remove(vSelectedIndex);
                    }
                });
            }
        }
    }



    /**
     *
     * @param pTask
     */
    private void setTaskEditorPanel(final Task pTask){
        aCenterPanel = getEditorJPanel(pTask);

        JPanel mainPanel = getMainPanel();

    }

    /**
     *
     * @param pTask
     *
     * @return
     */
    private JPanel getEditorJPanel(final Task pTask){
        return switch (pTask.getStringType()) {
            case "DELAY" -> new DelayEditorJPanel((Delay) pTask);
            case "MACRO" -> new MacroEditorJPanel((Macro) pTask);
            case "MOUSE" -> new MouseEditorJPanel((Mouse) pTask);
            case "TEXT" -> new TextEditorJPanel((Text) pTask);
            default -> new JPanel();
        };
    }

    private abstract static class TaskEditorJPanel<tType extends Task> extends JPanel
    {
        private final tType aTask;

        /**
         * Constructor of TaskEditorPanel class objects
         *
         * @param pTask
         */
        private TaskEditorJPanel(tType pTask) {
            super();
            aTask = pTask;

            setBackground(new Color(120, 150, 20));

            setMinimumSize(new Dimension(200, 200));
            setPreferredSize(new Dimension(500, 300));
        }

        /**
         *
         */
        public tType getTask() {
            return aTask;
        }

        /**
         *
         */
        public abstract void saveTask();
    }

    private static class DelayEditorJPanel extends TaskEditorJPanel<Delay>
    {
        private final JSpinner aSpinner;

        /**
         * Constructor of DelayEditorPanel class objects
         *
         * @param pDelay
         */
        public DelayEditorJPanel(final Delay pDelay)
        {
            super(pDelay);

            aSpinner = new JSpinner();
            aSpinner.setValue(pDelay.getTime());

            JLabel vLabel = new JLabel("ms");

            //aSpinner.setMinimumSize(new Dimension(50, 30));
            //aSpinner.setPreferredSize(new Dimension(50, 30));
            aSpinner.setMaximumSize(new Dimension(100, 40));


            GroupLayout vLayout = new GroupLayout(this);
            setLayout(vLayout);


            vLayout.setHorizontalGroup(vLayout.createSequentialGroup()
                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aSpinner,
                            aSpinner.getMinimumSize().width,
                            aSpinner.getPreferredSize().width,
                            aSpinner.getMaximumSize().width)

                    .addContainerGap(5, Short.MAX_VALUE)
            );

            vLayout.setVerticalGroup(vLayout.createSequentialGroup()
                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aSpinner,
                            aSpinner.getMinimumSize().height,
                            aSpinner.getPreferredSize().height,
                            aSpinner.getMaximumSize().height)

                    .addContainerGap(5, Short.MAX_VALUE)
            );
        }

        @Override public void saveTask()
        {
            getTask().setTime((int) aSpinner.getValue());
        }
    }

    private static class MacroEditorJPanel extends TaskEditorJPanel<Macro> implements ActionListener, KeyListener
    {
        private boolean aIsRecording;
        private ArrayList<String> aKeys;

        private final JButton aButton;


        /**
         * Constructor of MacroEditorPanel class objects
         *
         * @param pMacro
         */
        public MacroEditorJPanel(final Macro pMacro)
        {
            super(pMacro);

            aIsRecording = false;
            aKeys = pMacro.getMacro();

            aButton = new JButton();
            aButton.addActionListener(this);
            aButton.setFocusPainted(false);

            GroupLayout vLayout = new GroupLayout(this);
            setLayout(vLayout);


            vLayout.setHorizontalGroup(vLayout.createSequentialGroup()
                    .addGap(5)

                    .addComponent(aButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addGap(5)
            );

            vLayout.setVerticalGroup(vLayout.createSequentialGroup()
                    .addGap(5)

                    .addComponent(aButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addGap(5)
            );
        }

        @Override public void saveTask()
        {
            //getTask().setMacro(aIsRecording ? aKeys : null);
            getTask().setMacro(aKeys);
        }

        @Override public void actionPerformed(ActionEvent pE)
        {
            if (!aIsRecording){
                aIsRecording = true;

                aButton.setText("Press any button...");

                //aButton.setEnabled(false);

                aButton.addKeyListener(this);
            }
        }

        @Override
        public void keyTyped(KeyEvent pE) {}

        @Override
        public void keyPressed(KeyEvent pE)
        {
            String key = KeyEvent.getKeyText(pE.getKeyCode());

            if (!aKeys.contains(key)) {
                System.out.println(key);

                aKeys.add(key);
            }
        }

        @Override
        public void keyReleased(KeyEvent pE)
        {
            aButton.removeKeyListener(this);

            aButton.setText(getTask().getDescription() + "\n\nPress to record...");

            //aButton.setEnabled(true);

            aIsRecording = true;
        }
    }

    private static class MouseEditorJPanel extends TaskEditorJPanel<Mouse> implements ActionListener
    {
        /**
         * Constructor of MouseEditorPanel class objects
         *
         * @param pMouse
         */
        public MouseEditorJPanel(final Mouse pMouse)
        {
            super(pMouse);
        }

        @Override public void saveTask()
        {

        }

        @Override public void actionPerformed(ActionEvent pE)
        {

        }
    }

    private static class TextEditorJPanel extends TaskEditorJPanel<Text>
    {
        private final JTextArea aTextArea;
        /**
         * Constructor of TextEditorPanel class objects
         *
         * @param pText
         */
        public TextEditorJPanel(final Text pText)
        {
            super(pText);

            aTextArea = new JTextArea();
            aTextArea.setText(pText.getText());

            JScrollPane vScrollPane = new JScrollPane();
            vScrollPane.setViewportView(aTextArea);

            GroupLayout vLayout = new GroupLayout(this);
            setLayout(vLayout);


            vLayout.setHorizontalGroup(vLayout.createSequentialGroup()
                    .addGap(5)

                    .addComponent(vScrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addGap(5)
            );

            vLayout.setVerticalGroup(vLayout.createSequentialGroup()
                    .addGap(5)

                    .addComponent(vScrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addGap(5)
            );
        }

        @Override public void saveTask()
        {
            getTask().setText(aTextArea.getText());
        }
    }
}