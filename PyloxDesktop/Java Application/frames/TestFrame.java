package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame extends AbstractFrame {
    private JButton button1;
    private JButton button2;

    public TestFrame(String pName) {
        super(pName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        button1 = new JButton("Bouton 1");
        button2 = new JButton("Bouton 2");
        button1.addActionListener(this);
        button2.addActionListener(this);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(button1, 100, 200, 500)
                        .addComponent(button2, 200, 200, 500)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(button1, 100, 200, 500)
                        .addComponent(button2, 100, 200, 500)
        );

        add(panel);

        // Récupérer le facteur de mise à l'échelle DPI
        /*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();*/
        double scaleFactor = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0;

        // Calculer la taille minimale ajustée
        Dimension minimumSize = panel.getMinimumSize();

        pack();

        Insets insets = getInsets();




        Dimension minWindowSize = new Dimension(
                (int) ((minimumSize.width + insets.left + insets.right) * scaleFactor),
                (int) ((minimumSize.height + insets.top + insets.bottom) * scaleFactor)
        );

        System.out.println(minimumSize);
        System.out.println(minWindowSize);

        //System.out.println("Taille minimale ajustée avec le facteur de mise à l'échelle : " + minWindowSize);

        // Appliquer la taille minimale ajustée
        pack();
        setMinimumSize(minWindowSize);
        setPreferredSize(getSize());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent pE) {
        System.out.println(getSize());
        System.out.println(button1.getSize());
    }

    public static void main(String[] args) {
        new TestFrame("Test");
    }
}
