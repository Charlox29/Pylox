package frames;

import java.awt.*;
import javax.swing.*;

import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import java.awt.geom.RoundRectangle2D;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import tasks.TaskList;

import utils.Cookies;
import utils.Data;
import utils.Profile;
import utils.Serial;

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
    private final Cookies aCookies;

    private JPanel aButtonsPanel;

    /**
     * Constructs MainFrame objects.
     */
    public MainFrame(Cookies aCookies)
    {
        super("Pylox Desktop");
        this.aCookies = aCookies;

        TaskList[][] vTaskLists = aCookies.getProfile().getTaskLists();

        final int aRows = vTaskLists.length;
        final int aColumns = vTaskLists[0].length;

        setBackground(new Color(20, 20, 20));


        // Encoder Panel
        JPanel vEncoderPanel = new JPanel();
        vEncoderPanel.setLayout(new GridLayout(1,1));
        vEncoderPanel.add(new JButton("Encoder"));
        vEncoderPanel.setBackground(new Color(110, 22, 22));


        // Buttons Panel
        aButtonsPanel = new JPanel();
        aButtonsPanel.setBackground(new Color(20, 20, 20));
        aButtonsPanel.setLayout(new GridLayout(aRows, aColumns, 5, 5));

        for (int r = 0; r < aRows; r++) for (int c = 0; c < aColumns; c++){
            aButtonsPanel.add(new Button(this, vTaskLists[r][c]));
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

                .addComponent(aButtonsPanel,
                        aButtonsPanel.getMinimumSize().width,
                        aButtonsPanel.getPreferredSize().width,
                        aButtonsPanel.getMaximumSize().width)

                .addContainerGap(5, Short.MAX_VALUE)
        );

        vMainLayout.setVerticalGroup(vMainLayout.createSequentialGroup()
                .addContainerGap(5, Short.MAX_VALUE)

                .addGroup(vMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(vEncoderPanel,50, 75, 100)

                        .addGap(5)

                        .addComponent(aButtonsPanel,
                                aButtonsPanel.getMinimumSize().height,
                                aButtonsPanel.getPreferredSize().height,
                                aButtonsPanel.getMaximumSize().height)
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


    public void reloadButtons() {
        TaskList[][] newTaskLists = aCookies.getProfile().getTaskLists();
        aButtonsPanel.removeAll();
        int aRows = newTaskLists.length;
        int aColumns = newTaskLists[0].length;
        aButtonsPanel.setLayout(new GridLayout(aRows, aColumns, 5, 5));
        for (int r = 0; r < aRows; r++)
            for (int c = 0; c < aColumns; c++)
                aButtonsPanel.add(new Button(this, newTaskLists[r][c]));
        aButtonsPanel.revalidate();
        aButtonsPanel.repaint();
    }


    /**
     * @see JMenuBar
     */
    private static class MainMenuBar extends JMenuBar
    {
        private final JFrame aParent;
        private final ConnectionStatusIndicator aStatusIndicator;
        private Timer connectionStatusTimer;

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
            JMenuItem aConnect = new JMenuItem("Connect");
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
            vMenu.add(aConnect);
            vMenu.addSeparator();
            vMenu.add(aPreferences);
            vMenu.add(aQuit);
            vMenu.add(aAbout);

            int iconSize = 16;
            aNew.setIcon(loadIcon("new.png"));
            aImport.setIcon(loadIcon("import.png"));
            aSave.setIcon(loadIcon("save.png"));
            aSaveAs.setIcon(loadIcon("save_as.png"));
            aPreferences.setIcon(loadIcon("preferences.png"));
            aQuit.setIcon(loadIcon("quit.png"));
            aAbout.setIcon(loadIcon("about.png"));
            aConnect.setIcon(loadIcon("connect.png"));

            aNew.addActionListener(e -> {
                if (aParent instanceof MainFrame mf) {
                    Profile newProfile = new Profile();
                    
                    mf.aCookies.setProfile(newProfile);
                    mf.aCookies.setLastProfilePath(null);
                    mf.reloadButtons();
                    
                    Data.save(mf.aCookies, "cookies.pxd");
                }
            });

            aImport.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pylox Profile (*.ppxd)", "ppxd"));
                int result = chooser.showOpenDialog(aParent);

                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = chooser.getSelectedFile();

                    if (!file.getName().toLowerCase().endsWith(".ppxd")) {
                        JOptionPane.showMessageDialog(aParent, "Veuillez sélectionner un fichier .ppxd", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Profile importedProfile = (Profile) Data.load(file.getAbsolutePath());

                    if (importedProfile != null && aParent instanceof MainFrame mf) {
                        mf.aCookies.setProfile(importedProfile);
                        mf.aCookies.setLastProfilePath(file.getAbsolutePath());
                        mf.reloadButtons();

                        Data.save(mf.aCookies, "cookies.pxd");
                    } else {
                        JOptionPane.showMessageDialog(aParent, "Erreur lors de l'import du profil.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            aSave.addActionListener(e -> {
                if (aParent instanceof MainFrame mf) {
                    String lastProfilePath = mf.aCookies.getLastProfilePath();
                    
                    if (lastProfilePath != null && !lastProfilePath.isEmpty()) {
                        Data.save(mf.aCookies.getProfile(), lastProfilePath);
                    } else {
                        JOptionPane.showMessageDialog(aParent, "Aucun chemin de profil enregistré. Utilisez 'Save as' pour choisir un emplacement.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            aSaveAs.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pylox Profile (*.ppxd)", "ppxd"));
                int result = chooser.showSaveDialog(aParent);

                if (result == JFileChooser.APPROVE_OPTION && aParent instanceof MainFrame mf) {
                    java.io.File file = chooser.getSelectedFile();
                    String path = file.getAbsolutePath();

                    if (!path.toLowerCase().endsWith(".ppxd")) {
                        path += ".ppxd";
                        file = new java.io.File(path);
                    }
                    Data.save(mf.aCookies.getProfile(), path);

                    mf.aCookies.setLastProfilePath(path);
                    Data.save(mf.aCookies, "cookies.pxd");
                }
            });

            aPreferences.addActionListener(e -> System.out.println("Preferences"));

            aQuit.addActionListener(e -> aParent.dispose());

            aAbout.addActionListener(e -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/Charlox29/Pylox"));
                    } catch (IOException | URISyntaxException ignored) {}
                }
            });

            aConnect.addActionListener(e -> {
                SerialConnectDialog dialog = new SerialConnectDialog(aParent);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    String selectedPort = dialog.getSelectedPort();

                    if (selectedPort != null) {
                        if (Serial.isOpened()) {
                            Serial.close();
                        }

                        if (!Serial.open(selectedPort, 9600)) {
                            JOptionPane.showMessageDialog(aParent, "Impossible d'ouvrir le port " + selectedPort, "Erreur", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Serial.clearInput(); // Vide le buffer à la connexion

                            // Mise à jour des cookies et sauvegarde
                            if (aParent instanceof MainFrame mf && mf.aCookies != null) {
                                mf.aCookies.setLastPort(selectedPort);
                                Data.save(mf.aCookies, "cookies.pxd");
                            }
                        }
                    }
                }
            });

            add(vMenu);

            aStatusIndicator = new ConnectionStatusIndicator();
            add(aStatusIndicator);

            connectionStatusTimer = new Timer(500, e -> {
                this.setConnectionStatus(Serial.isOpened());
            });
            connectionStatusTimer.start();
        }

        private static Icon loadIcon(String name) {
            return new ImageIcon("icons/" + name);
        }

        public void setConnectionStatus(boolean connected) {
            aStatusIndicator.setConnected(connected);
        }

        /**
         * Composant graphique pour afficher l'état de connexion (rond vert ou rouge)
         */
        private static class ConnectionStatusIndicator extends JPanel {
            private boolean connected = false;

            public ConnectionStatusIndicator() {
                setPreferredSize(new Dimension(24, 24));
                setOpaque(false);
                setToolTipText("État de la connexion");
            }

            public void setConnected(boolean connected) {
                this.connected = connected;
                repaint();
            }

            @Override
            public boolean contains(int x, int y) {
                int cx = 6 + 6; // centre x du rond
                int cy = 6 + 6; // centre y du rond
                int radius = 6;
                int dx = x - cx;
                int dy = y - cy;
                return dx * dx + dy * dy <= radius * radius;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(connected ? Color.GREEN : Color.RED);
                g2.fillOval(6, 6, 12, 12);
                g2.setColor(Color.DARK_GRAY);
                g2.drawOval(6, 6, 12, 12);
                g2.dispose();
            }
        }
    }

    /**
     * @see JButton
     */
    private static class Button extends JButton implements ActionListener {
        private final JFrame aParent;
        private final TaskList aTaskList;

        private final Color aCenterColor = new Color(50, 50, 50);
        private final Color aBorderColor = new Color(90, 90, 90);
        private final Color aPressedColor = new Color(40, 40, 40);
        private final int aArcWidth = 35;
        private final int aArcHeight = 35;

        /**
         * Constructs Button objects.
         *
         * @param pParent the JFrame parent
         * @param pTaskList the TaskList
         */
        public Button(final JFrame pParent, final TaskList pTaskList) {
            super();

            aParent = pParent;
            aTaskList = pTaskList;

            setForeground(new Color(255, 255, 255));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setUI(new BasicButtonUI());

            setMinimumSize(new Dimension(75, 75));
            setPreferredSize(new Dimension(100, 100));
            setMaximumSize(new Dimension(100, 100));

            addActionListener(this);



            updateText();
        }

        /**
         * Updates the button text based on the task list.
         */
        public void updateText() {
            if (aTaskList.isEmpty()) {
                setText("");
            } else {
                setText("·");
            }
        }

        @Override
        protected void paintComponent(Graphics pG) {
            Graphics2D vG2 = (Graphics2D) pG.create();
            vG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, aArcWidth, aArcHeight);

            vG2.setColor(getModel().isPressed() ? aPressedColor : aCenterColor);
            vG2.fill(roundRect);

            vG2.setColor(aBorderColor);
            vG2.setStroke(new BasicStroke(2));
            vG2.draw(roundRect);

            vG2.dispose();

            super.paintComponent(pG);
        }

        @Override
        protected void paintBorder(Graphics pG) {
        }

        @Override
        public boolean contains(int pX, int pY) {
            RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), aArcWidth, aArcHeight);
            return roundRect.contains(pX, pY);
        }

        @Override
        public void actionPerformed(ActionEvent pE) {
            TaskListManagerFrame vButtonFrame = new TaskListManagerFrame(aTaskList) {
                @Override
                public void windowClosing(final WindowEvent pE) {
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

    public static class SerialConnectDialog extends JDialog {
        private JComboBox<String> portComboBox;
        private boolean confirmed = false;

        public SerialConnectDialog(JFrame parent) {
            super(parent, "Connect to Serial Port", true);

            setLayout(new BorderLayout(10, 10));

            String[] ports = Serial.getPorts();
            portComboBox = new JComboBox<>(ports);

            String currentPort = utils.Serial.getPortName();
            if (currentPort != null && !currentPort.equals("NOT CONNECTED")) {
                for (int i = 0; i < ports.length; i++) {
                    if (ports[i].equals(currentPort)) {
                        portComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("Select port:"));
            centerPanel.add(portComboBox);

            JPanel buttonPanel = new JPanel();
            JButton okButton = new JButton("Connect");
            JButton cancelButton = new JButton("Cancel");
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            add(centerPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            okButton.addActionListener(e -> {
                confirmed = true;
                setVisible(false);
            });
            cancelButton.addActionListener(e -> setVisible(false));

            setPreferredSize(new Dimension(350, 170));
            pack();
            setResizable(false);
            setLocationRelativeTo(parent);
        }

        public String getSelectedPort() {
            return (String) portComboBox.getSelectedItem();
        }

        public boolean isConfirmed() {
            return confirmed;
        }
    }

    static class PlusIcon implements Icon {
        private final int size;
        public PlusIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            int m = size / 2;
            g2.drawLine(x + m, y + 3, x + m, y + size - 3);
            g2.drawLine(x + 3, y + m, x + size - 3, y + m);
            g2.dispose();
        }
    }

    static class DiskIcon implements Icon {
        private final int size;
        public DiskIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(60, 60, 60));
            g2.fillRect(x + 2, y + 2, size - 4, size - 4);
            g2.setColor(Color.WHITE);
            g2.fillRect(x + 5, y + 5, size - 10, size - 10);
            g2.setColor(Color.BLACK);
            g2.drawRect(x + 2, y + 2, size - 4, size - 4);
            g2.drawRect(x + 5, y + 5, size - 10, size - 10);
            g2.fillRect(x + size/2 - 2, y + size - 8, 4, 4);
            g2.dispose();
        }
    }

    static class GearIcon implements Icon {
        private final int size;
        public GearIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            for (int i = 0; i < 8; i++) {
                double angle = i * Math.PI / 4;
                int cx = x + size/2 + (int)(Math.cos(angle) * (size/2 - 2));
                int cy = y + size/2 + (int)(Math.sin(angle) * (size/2 - 2));
                g2.fillRect(cx-1, cy-1, 3, 3);
            }
            g2.setColor(Color.GRAY);
            g2.fillOval(x + 4, y + 4, size - 8, size - 8);
            g2.setColor(Color.BLACK);
            g2.drawOval(x + 4, y + 4, size - 8, size - 8);
            g2.fillOval(x + size/2 - 2, y + size/2 - 2, 4, 4);
            g2.dispose();
        }
    }

    static class QuestionIcon implements Icon {
        private final int size;
        public QuestionIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            g2.drawArc(x + 4, y + 4, size - 8, size - 8, 0, 180);
            g2.drawLine(x + size/2, y + size/2, x + size/2, y + size - 6);
            g2.fillOval(x + size/2 - 2, y + size - 6, 4, 4);
            g2.dispose();
        }
    }

    static class CrossIcon implements Icon {
        private final int size;
        public CrossIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.RED.darker());
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x + 4, y + 4, x + size - 4, y + size - 4);
            g2.drawLine(x + size - 4, y + 4, x + 4, y + size - 4);
            g2.dispose();
        }
    }

    static class DoubleArrowIcon implements Icon {
        private final int size;
        public DoubleArrowIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLUE.darker());
            int[] x1 = {x + 3, x + size/2, x + 3};
            int[] y1 = {y + 4, y + size/2, y + size - 4};
            g2.fillPolygon(x1, y1, 3);
            int[] x2 = {x + size - 3, x + size/2, x + size - 3};
            int[] y2 = {y + 4, y + size/2, y + size - 4};
            g2.fillPolygon(x2, y2, 3);
            g2.dispose();
        }
    }

    static class ImportIcon implements Icon {
        private final int size;
        public ImportIcon(int size) { this.size = size; }
        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GREEN.darker());
            g2.setStroke(new BasicStroke(2));
            g2.drawArc(x + 3, y + 5, size - 8, size - 10, 30, 210);
            int[] xArr = {x + size - 7, x + size - 3, x + size - 7};
            int[] yArr = {y + size/2 - 2, y + size/2, y + size/2 + 2};
            g2.fillPolygon(xArr, yArr, 3);
            g2.dispose();
        }
    }
}

