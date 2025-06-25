package frames;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;
import tasks.Shortcut;
import tasks.Text;
import tasks.Mouse;
import tasks.Delay;

/**
 * Interface graphique pour éditer une liste de tâches.
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see AbstractFrame
 * @see JFrame
 */
public class TaskListManagerFrame extends AbstractFrame {
    private final TaskList aTaskList;

    private JPanel aCenterPanel;

    private final TaskListManagerList aList;
    private final JButton aAddButton;

    /**
     * Constructs TaskListManagerFrame objects.
     *
     * @param pTaskList the TaskList
     */
    public TaskListManagerFrame(final TaskList pTaskList)
    {
        super("Button");

        aTaskList = pTaskList;

        // Center Panel
        if (!aTaskList.isEmpty()) {
            aCenterPanel = getEditorJPanel(aTaskList.get(0));
        }
        else {
            aCenterPanel = new NewTaskPanel();
        }


        // West Panel
        JPanel vWestPanel = new JPanel();
        vWestPanel.setBackground(new Color(50, 50, 50));
        vWestPanel.setBorder(new LineBorder(new Color(90, 90, 90)));

        aList = new TaskListManagerList(aTaskList);

        JScrollPane vScrollPane = new JScrollPane(aList);

        aAddButton = new JButton("Add Task");

        aAddButton.addActionListener(e -> changeCenterPanel(new NewTaskPanel()));

        GroupLayout vWestPanelLayout = new GroupLayout(vWestPanel);
        vWestPanel.setLayout(vWestPanelLayout);

        vWestPanelLayout.setHorizontalGroup(vWestPanelLayout.createSequentialGroup()
                .addGap(5)

                .addGroup(vWestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vScrollPane)

                        .addComponent(aAddButton)
                )

                .addGap(5)
        );

        vWestPanelLayout.setVerticalGroup(vWestPanelLayout.createSequentialGroup()
                .addGap(5)

                .addComponent(vScrollPane,
                        GroupLayout.DEFAULT_SIZE,
                        100,
                        Short.MAX_VALUE)

                .addGap(5)

                .addComponent(aAddButton,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE)

                .addGap(5)
        );




        // Main Panel
        JPanel vMainPanel = getMainPanel();
        vMainPanel.setBackground(new Color(20, 20, 20));

        GroupLayout vMainLayout = new GroupLayout(vMainPanel);
        vMainPanel.setLayout(vMainLayout);

        vMainLayout.setHorizontalGroup(vMainLayout.createSequentialGroup()
                .addGap(5)

                .addComponent(vWestPanel,
                        vWestPanel.getMinimumSize().width,
                        vWestPanel.getPreferredSize().width,
                        vWestPanel.getMaximumSize().width
                        // 200
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

    /**
     * Saves the currently displayed task.
     */
    private void saveCurrentTask(){
        if(aCenterPanel instanceof TaskEditorPanel){
            ((TaskEditorPanel<?>) aCenterPanel).saveTask();
        }
    }

    @Override public void windowClosing(final WindowEvent pE)
    {
        saveCurrentTask();
    }

    /**
     * Changes the central panel.
     *
     * @param pPanel the new panel to display
     */
    private void changeCenterPanel(final JPanel pPanel){
        saveCurrentTask();

        ((GroupLayout) getMainPanel().getLayout()).replace(aCenterPanel, pPanel);

        aCenterPanel = pPanel;

        getMainPanel().revalidate();
        getMainPanel().repaint();
    }

    /**
     * Returns the appropriate editor panel for the given task.
     *
     * @param pTask the task for which to create an editor panel
     *
     * @return a JPanel corresponding to the task editor
     */
    private JPanel getEditorJPanel(final Task pTask){
        return switch (pTask.getStringType()) {
            case "DELAY" -> new DelayEditorPanel((Delay) pTask);
            case "SHORTCUT" -> new ShortcutEditorPanel((Shortcut) pTask);
            case "MOUSE" -> new MouseEditorPanel((Mouse) pTask);
            case "TEXT" -> new TextEditorPanel((Text) pTask);
            default -> new JPanel();
        };
    }

    /**
     * @see JList
     */
    private class TaskListManagerList extends JList<String> {
        private final TaskList aTaskList;

        private final TaskPopupMenu aPopupMenu;

        private final DefaultListModel<String> aModel;

        /**
         * Constructs TaskListManagerList objects.
         *
         * @param pTaskList the TaskList to manage
         */
        public TaskListManagerList(final TaskList pTaskList) {
            super();

            aTaskList = pTaskList;

            aPopupMenu = new TaskPopupMenu();

            aModel = new DefaultListModel<>();

            setModel(aModel);

            for (int i = 0; i < aTaskList.size(); i++) {
                aModel.addElement(aTaskList.get(i).getStringType());
            }

            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setDragEnabled(true);
            setDropMode(DropMode.INSERT);

            setTransferHandler(new TransferHandler(){
                private int dragSourceIndex = -1;

                @Override
                public boolean canImport(TransferSupport support) {
                    return support.isDrop() && support.isDataFlavorSupported(DataFlavor.stringFlavor);
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                    dragSourceIndex = TaskListManagerList.this.getSelectedIndex();
                    String value = TaskListManagerList.this.getSelectedValue();
                    return new StringSelection(value);
                }

                @Override
                public int getSourceActions(JComponent c) {
                    return MOVE;
                }

                @Override
                public boolean importData(TransferSupport support) {
                    if (!canImport(support)) {
                        return false;
                    }

                    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
                    int dropIndex = dl.getIndex();

                    if (dragSourceIndex < 0 || dropIndex < 0 || dragSourceIndex == dropIndex || dragSourceIndex > aModel.size() || dropIndex > aModel.size()) {
                        return false;
                    }

                    if (dropIndex > dragSourceIndex) {
                        dropIndex--;
                    }

                    moveTo(dragSourceIndex, dropIndex);

                    TaskListManagerList.this.setSelectedIndex(dropIndex);

                    return true;
                }
            });

            addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int vIndex = TaskListManagerList.this.getSelectedIndex();

                    if (vIndex != -1) {
                        changeCenterPanel(getEditorJPanel(aTaskList.get(vIndex)));
                    }
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e) && TaskListManagerList.this.getSelectedIndex() != -1) {
                        aPopupMenu.show(TaskListManagerList.this, e.getX(), e.getY());
                    }
                }
            });
        }

        /**
         * Adds a task at the specified index in the list.
         *
         * @param pIndex the index at which to add the task
         * @param pTask the task to add
         */
        public void add(final int pIndex, final Task pTask) {
            aModel.add(pIndex, pTask.getStringType());
            aTaskList.add(pIndex, pTask);

            changeCenterPanel(getEditorJPanel(pTask));
        }

        /**
         * Adds a task to the end of the list.
         * 
         * @param pTask the task to add
         */
        public void add(final Task pTask) {
            add(aTaskList.size(), pTask);
        }

        /**
         * Duplicates the task at the specified index.
         *
         * @param pIndex the index of the task to duplicate
         */
        public void duplicate(final int pIndex) {
            add(pIndex + 1, aTaskList.get(pIndex).clone());
        }

        /**
         * Removes the task at the specified index.
         *
         * @param pIndex the index of the task to remove
         */
        public void remove(final int pIndex) {
            aModel.remove(pIndex);
            aTaskList.remove(pIndex);
        }

        /**
         * Reorders the list by moving the task from an index to another one.
         *
         * @param pIndexFrom the source index
         * @param pIndexTo the destination index
         */
        public void moveTo(final int pIndexFrom, final int pIndexTo) {
            Task vTask = aTaskList.get(pIndexFrom);

            remove(pIndexFrom);

            add(pIndexTo, vTask);
        }

        /*@Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }*/

        /**
         * The ManageTaskPopupMenu class provides a context menu for managing tasks,
         * including duplicating and deleting tasks.
         *
         * @see JPopupMenu
         */
        private class TaskPopupMenu extends JPopupMenu{
            /**
             * Constructs TaskPopupMenu objects.
             */
            public TaskPopupMenu(){
                super();

                JMenuItem vDuplicateItem = new JMenuItem("Duplicate");
                JMenuItem vDeleteItem = new JMenuItem("Delete");

                add(vDuplicateItem);
                add(vDeleteItem);


                vDuplicateItem.addActionListener(e -> {
                    int vSelectedIndex = TaskListManagerList.this.getSelectedIndex();

                    if (vSelectedIndex != -1) {
                        TaskListManagerList.this.duplicate(vSelectedIndex);
                    }
                });

                vDeleteItem.addActionListener(e -> {
                    int vSelectedIndex = TaskListManagerList.this.getSelectedIndex();

                    if (vSelectedIndex != -1) {
                        TaskListManagerList.this.remove(vSelectedIndex);
                    }
                });
            }
        }
    }

    /**
     * The NewTaskPanel class displays a panel with buttons for creating new tasks.
     *
     * @see JPanel
     */
    private class NewTaskPanel extends JPanel
    {
        /**
         * Constructs NewTaskPanel objects.
         */
        public NewTaskPanel(){
            super();

            NewTaskButton aNewTextButton = new NewTaskButton("New Text");
            NewTaskButton aNewDelayButton = new NewTaskButton("New Delay");
            NewTaskButton aNewShortcutButton = new NewTaskButton("New Shortcut");
            NewTaskButton aNewMouseButton = new NewTaskButton("New Mouse");

            aNewTextButton.addActionListener(e -> aList.add(new Text()));

            aNewDelayButton.addActionListener(e -> aList.add(new Delay()));

            aNewShortcutButton.addActionListener(e -> aList.add(new Shortcut()));

            aNewMouseButton.addActionListener(e -> aList.add(new Mouse()));

            setBackground(new Color(20, 20, 20));
            ///setBorder(new LineBorder(new Color(90, 90, 90)));

            setMinimumSize(new Dimension(200, 200));
            setPreferredSize(new Dimension(500, 300));

            GroupLayout vLayout = new GroupLayout(this);
            setLayout(vLayout);


            vLayout.setHorizontalGroup(vLayout.createSequentialGroup()
                    .addContainerGap(5, Short.MAX_VALUE)

                    .addGroup(vLayout.createParallelGroup()
                            .addComponent(aNewTextButton,
                                    aNewTextButton.getMinimumSize().width,
                                    aNewTextButton.getPreferredSize().width,
                                    Short.MAX_VALUE)

                            .addComponent(aNewDelayButton,
                                    aNewDelayButton.getMinimumSize().width,
                                    aNewDelayButton.getPreferredSize().width,
                                    Short.MAX_VALUE)

                            .addComponent(aNewShortcutButton,
                                    aNewShortcutButton.getMinimumSize().width,
                                    aNewShortcutButton.getPreferredSize().width,
                                    Short.MAX_VALUE)

                            .addComponent(aNewMouseButton,
                                    aNewMouseButton.getMinimumSize().width,
                                    aNewMouseButton.getPreferredSize().width,
                                    Short.MAX_VALUE)
                    )

                    .addContainerGap(5, Short.MAX_VALUE)
            );

            vLayout.setVerticalGroup(vLayout.createSequentialGroup()
                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aNewTextButton,
                            aNewTextButton.getMinimumSize().height,
                            aNewTextButton.getPreferredSize().height,
                            aNewTextButton.getMaximumSize().height)

                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aNewDelayButton,
                            aNewDelayButton.getMinimumSize().height,
                            aNewDelayButton.getPreferredSize().height,
                            aNewDelayButton.getMaximumSize().height)

                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aNewShortcutButton,
                            aNewShortcutButton.getMinimumSize().height,
                            aNewShortcutButton.getPreferredSize().height,
                            aNewShortcutButton.getMaximumSize().height)

                    .addContainerGap(5, Short.MAX_VALUE)

                    .addComponent(aNewMouseButton,
                            aNewMouseButton.getMinimumSize().height,
                            aNewMouseButton.getPreferredSize().height,
                            aNewMouseButton.getMaximumSize().height)

                    .addContainerGap(5, Short.MAX_VALUE)
            );
        }

        /**
         * @see JButton
         */
        private static class NewTaskButton extends JButton
        {
            /**
             * Construct NewTaskButton objects.
             *
             * @param pText text on the button
             */
            public NewTaskButton(final String pText){
                super(pText);

                setBackground(new Color(50, 50, 50));
                setForeground(new Color(255, 255, 255));

                setBorder(new LineBorder(new Color(90, 90, 90)));

                setMinimumSize(new Dimension(25, 25));
                setPreferredSize(new Dimension(50, 50));
                setMaximumSize(new Dimension(50, 50));

                setFocusPainted(false);
            }
        }
    }

    /**
     * The TaskEditorPanel is an abstract class that serves as a base for
     * task editor panels, which provide graphical interface for editing tasks.
     *
     * @param <tType> a Task extended class
     *
     * @see JPanel
     */
    private abstract static class TaskEditorPanel<tType extends Task> extends JPanel
    {
        private final tType aTask;

        /**
         * Construct TaskEditorPanel objects.
         *
         * @param pTask the Task to edit
         */
        private TaskEditorPanel(tType pTask) {
            super();
            aTask = pTask;

            setBackground(new Color(50, 50, 50));
            setBorder(new LineBorder(new Color(90, 90, 90)));

            setMinimumSize(new Dimension(200, 200));
            setPreferredSize(new Dimension(500, 300));
        }

        /**
         * Returns the task associated with this panel.
         *
         * @return the edited task
         */
        public tType getTask() {
            return aTask;
        }

        /**
         * Saves the modifications made to the task.
         */
        public abstract void saveTask();
    }

    /**
     * The DelayEditorPanel provides a graphical interface for editing Delay tasks.
     *
     * @see TaskEditorPanel
     */
    private static class DelayEditorPanel extends TaskEditorPanel<Delay>
    {
        private final JSpinner aSpinner;

        /**
         * Construct DelayEditorPanel objects.
         *
         * @param pDelay the Delay task to edit
         */
        public DelayEditorPanel(final Delay pDelay)
        {
            super(pDelay);

            aSpinner = new JSpinner(new SpinnerNumberModel(pDelay.getTime(), 0, 999999, 1));

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
            getTask().setTime(((Number) aSpinner.getValue()).longValue());
        }
    }

    /**
     * The ShortcutEditorPanel provides a graphical interface for editing Shortcut tasks.
     *
     * @see TaskEditorPanel
     */
    private static class ShortcutEditorPanel extends TaskEditorPanel<Shortcut> implements ActionListener, KeyListener
    {
        private boolean aIsRecording;
        private ArrayList<Integer> aKeys;

        private final JButton aButton;


        /**
         * Constructs ShortcutEditorPanel objects.
         *
         * @param pShortcut the Shortcut task to edit
         */
        public ShortcutEditorPanel(final Shortcut pShortcut)
        {
            super(pShortcut);

            aIsRecording = false;
            aKeys = pShortcut.getKeys();

            aButton = new JButton(getShortcutText());
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

        private String getShortcutText() {
            if (aKeys.isEmpty()) return "Shortcut empty";
            StringBuilder sb = new StringBuilder();
            for (int key : aKeys) {
                sb.append(KeyEvent.getKeyText(key)).append(" + ");
            }
            // Retirer le dernier " + "
            if (sb.length() > 3) sb.setLength(sb.length() - 3);
            return sb.toString();
        }

        @Override public void saveTask()
        {
            //getTask().setKeys(aIsRecording ? aKeys : null);
            getTask().setKeys(aKeys);
        }

        @Override public void actionPerformed(ActionEvent pE)
        {
            if (!aIsRecording){
                aButton.setText("Press any button...");

                aKeys.clear();

                aButton.addKeyListener(this);

                aIsRecording = true;
            }
            else{
                aButton.setText(getShortcutText());

                aButton.removeKeyListener(this);

                aIsRecording = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent pE) {}

        @Override
        public void keyPressed(KeyEvent pE)
        {
            int vKey = pE.getKeyCode();

            if (!aKeys.contains(vKey)) {
                aKeys.add(vKey);

                aButton.setText(getShortcutText());
            }
        }

        @Override
        public void keyReleased(KeyEvent pE)
        {
            saveTask();

            aButton.setText("Saved: " + getShortcutText());

            aButton.removeKeyListener(this);

            aIsRecording = false;
        }
    }

    /**
     * The MouseEditorPanel provides a graphical interface for editing Mouse tasks.
     * Currently, no additional editing functionality is implemented.
     *
     * @see TaskEditorPanel
     */
    private static class MouseEditorPanel extends TaskEditorPanel<Mouse> implements ActionListener
    {
        /**
         * Constructs MouseEditorPanel objects.
         *
         * @param pMouse the Mouse task to edit
         */
        public MouseEditorPanel(final Mouse pMouse)
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

    /**
     * The TextEditorPanel provides a graphical interface for editing Text tasks.
     *
     * @see TaskEditorPanel
     */
    private static class TextEditorPanel extends TaskEditorPanel<Text>
    {
        private final JTextArea aTextArea;
        /**
         * Constructs TextEditorPanel objects.
         *
         * @param pText the Text task to edit
         */
        public TextEditorPanel(final Text pText)
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