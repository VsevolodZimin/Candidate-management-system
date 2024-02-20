
package view.components.panels.welcomePanel;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WelcomePanel extends JPanel {

    private JLabel jLabel1;

    public WelcomePanel() {
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Segoe UI", 2, 24));
        jLabel1.setForeground(new Color(153, 153, 153));
        jLabel1.setText("Let's get started!");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(134, Short.MAX_VALUE))
        );
    }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(new GradientPaint(0, 0, Color.decode("#faf2f9"),0, getHeight(), Color.decode("#ffffff")));
                g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
                super.paintComponent(g);
            }
}
