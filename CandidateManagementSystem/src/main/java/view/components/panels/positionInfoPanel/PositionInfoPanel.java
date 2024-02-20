package view.components.panels.positionInfoPanel;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
public class PositionInfoPanel extends JPanel {

    private JLabel jLabel1;
    private JButton saveButton;
    private view.RoundedTextArea textArea;

    public PositionInfoPanel() {
        setOpaque(false);
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
    private void initComponents() {

        jLabel1 = new JLabel();
        saveButton = new JButton();
        textArea = new view.RoundedTextArea();

        jLabel1.setText("Here's an important note:");

        saveButton.setText("Save");
        saveButton.setFocusPainted(false);

        textArea.setMinimumSize(new Dimension(200, 100));
        textArea.setPreferredSize(new Dimension(400, 200));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addGap(13, 13, 13))
        );
    }
}