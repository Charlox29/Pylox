package frames;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import tasks.TaskList;

/**
 * The MainFrame class represents the primary application window.
 * It creates a graphical representation of the PyloxDeck.
 *
 * @author Charles A
 *
 * @version 02/22/2025
 *
 * @see AbstractFrame
 */
public class MainFrame extends AbstractFrame
{
    /**
     * Constructs MainFrame objects.
     *
     * @param pTaskLists a 2D array of TaskList objects to display
     */
    public MainFrame(final TaskList[][] pTaskLists)
    {
        super("Pylox Desktop");

        final int aRows = pTaskLists.length;
        final int aColumns = pTaskLists[0].length;

        setBackground(new Color(20, 20, 20));


        // Encoder Panel
        JPanel vEncoderPanel = new JPanel();
        vEncoderPanel.setLayout(new GridLayout(1,1));
        vEncoderPanel.add(new JButton("Encoder"));
        vEncoderPanel.setBackground(new Color(110, 22, 22));


        // Buttons Panel
        JPanel vButtonsPanel = new JPanel();
        vButtonsPanel.setBackground(new Color(20, 20, 20));
        vButtonsPanel.setLayout(new GridLayout(aRows, aColumns, 5, 5));

        for (int r = 0; r < aRows; r++) for (int c = 0; c < aColumns; c++){
            vButtonsPanel.add(new Button(this, pTaskLists[r][c]));
        }


        // Main Panel
        JPanel vMainPanel = getMainPanel();
        vMainPanel.setBackground(new Color(20, 20, 20));

        GroupLayout vMainLayout = new GroupLayout(vMainPanel);
        vMainPanel.setLayout(vMainLayout);


        vMainLayout.setHorizontalGroup(vMainLayout.createSequentialGroup()
                .addContainerGap(5, Short.MAX_VALUE)

                .addComponent(vEncoderPanel, 50, 75, 100)

                .addGap(5)

                .addComponent(vButtonsPanel,
                        vButtonsPanel.getMinimumSize().width,
                        vButtonsPanel.getPreferredSize().width,
                        vButtonsPanel.getMaximumSize().width)

                .addContainerGap(5, Short.MAX_VALUE)
        );

        vMainLayout.setVerticalGroup(vMainLayout.createSequentialGroup()
                .addContainerGap(5, Short.MAX_VALUE)

                .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vEncoderPanel,50, 75, 100)

                        .addGap(5)

                        .addComponent(vButtonsPanel,
                                vButtonsPanel.getMinimumSize().height,
                                vButtonsPanel.getPreferredSize().height,
                                vButtonsPanel.getMaximumSize().height)
                )

                .addContainerGap(5, Short.MAX_VALUE)
        );


        MainMenuBar vMenuBar = new MainMenuBar(this);
        setJMenuBar(vMenuBar);

        setLocationRelativeTo(null);

        pack();

        setVisible(true);


        // Dimensions
        double vScaleFactor = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0;

        Dimension vMainPanelMinSize = vMainPanel.getMinimumSize();

        Insets vInsets = getInsets();

        Dimension vMinSize = new Dimension(
                (int) ((vMainPanelMinSize.width + vInsets.left + vInsets.right) * vScaleFactor),
                (int) ((vMainPanelMinSize.height + vMenuBar.getHeight() + vInsets.top + vInsets.bottom) * vScaleFactor)
        );

        setMinimumSize(vMinSize);
    }

    /**
     * @see JMenuBar
     */
    private static class MainMenuBar extends JMenuBar
    {
        private final JFrame aParent;

        /**
         * Constructs MainMenuBar objects.
         *
         * @param pParent the JFrame parent
         */
        public MainMenuBar(final JFrame pParent){
            aParent = pParent;

            JMenuItem aNew = new JMenuItem("New");
            JMenuItem aImport = new JMenuItem("Import");
            JMenuItem aSave = new JMenuItem("Save");
            JMenuItem aSaveAs = new JMenuItem("Save as");
            JMenuItem aPreferences = new JMenuItem("Preferences");
            JMenuItem aQuit = new JMenuItem("Quit");
            JMenuItem aAbout = new JMenuItem("About");

            aNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
            aImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
            aSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
            aSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));

            aNew.setMnemonic('N');
            aImport.setMnemonic('I');
            aSave.setMnemonic('S');
            aPreferences.setMnemonic('P');

            JMenu vMenu = new JMenu("Files");

            vMenu.add(aNew);
            vMenu.add(aImport);
            vMenu.add(aSave);
            vMenu.add(aSaveAs);
            vMenu.addSeparator();
            vMenu.add(aPreferences);
            vMenu.add(aQuit);
            vMenu.add(aAbout);

            add(vMenu);

            aNew.addActionListener(e -> System.out.println("New"));

            aImport.addActionListener(e -> System.out.println("Import"));

            aSave.addActionListener(e -> System.out.println("Save"));

            aPreferences.addActionListener(e -> System.out.println("Preferences"));

            aQuit.addActionListener(e -> aParent.dispose());

            aAbout.addActionListener(e -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/Charlox29/Pylox"));
                    } catch (IOException | URISyntaxException ignored) {}
                }
            });
        }
    }

    /**
     * @see JButton
     */
    private static class Button extends JButton implements ActionListener
    {
        private final JFrame aParent;
        private final TaskList aTaskList;

        /**
         * Constructs Button objects.
         *
         * @param pParent the JFrame parent
         * @param pTaskList the TaskList
         */
        public Button(final JFrame pParent, final TaskList pTaskList){
            super();

            aParent = pParent;
            aTaskList = pTaskList;

            setBackground(new Color(50, 50, 50));
            setForeground(new Color(255, 255, 255));

            setBorder(new LineBorder(new Color(90, 90, 90), 2));

            setMinimumSize(new Dimension(75, 75));
            setPreferredSize(new Dimension(100, 100));
            setMaximumSize(new Dimension(100, 100));

            setFocusPainted(false);

            addActionListener(this);

            updateText();
        }

        /**
         * 
         */
        public void updateText(){
            if(aTaskList.isEmpty()) setText("");

            else setText("·");
        }

        @Override
        public void actionPerformed(ActionEvent pE) {
            TaskListManagerFrame vButtonFrame = new TaskListManagerFrame(aTaskList){
                @Override public void windowClosing(final WindowEvent pE){
                    super.windowClosing(pE);

                    aParent.setEnabled(true);

                    aParent.toFront();

                    Button.this.updateText();
                }
            };

            vButtonFrame.setLocationRelativeTo(aParent);

            aParent.setEnabled(false);
        }
    }
}

