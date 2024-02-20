package view.components.panels.customErrorMessage;

import java.awt.*;
import java.awt.geom.Area;
import lombok.Getter;
import util.connection.PathsPropertiesManager;
import javax.swing.*;


@Getter
public class CustomErrorMessage extends JPanel {

    private final int min = GroupLayout.DEFAULT_SIZE;
    private final int pref;
    private final int max  = Short.MAX_VALUE;
    private final boolean isException;
    private JButton confirmButton;
    private JLabel iconTag;
    private JLabel textTag;

    public CustomErrorMessage(boolean isException, int pref) {
        this.pref = pref;
        this.isException = isException;
        initComponents();
        iconTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + (isException ? "error" : "stop") + ".png"));

    }
    private void initComponents() {

        confirmButton = new JButton();
        iconTag = new JLabel();
        textTag = new JLabel();

        confirmButton.setText("Got it");
        confirmButton.setFocusPainted(false);

        textTag.setText("You didn't mean to do that did you? ");
        textTag.setMaximumSize(new Dimension(999, 999));
        textTag.setMinimumSize(new Dimension(10, 10));
        textTag.setRequestFocusEnabled(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(iconTag)
                .addGap(18, 18, 18)
                .addComponent(textTag, min, pref, max)
                .addGap(32, 32, 32))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(textTag, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconTag, GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addComponent(confirmButton)
                .addGap(25, 25, 25))
        );
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Area rectangle = new Area(new Rectangle.Double(0,0,getWidth(), getHeight()));
        g2d.setPaint(new GradientPaint(0, 0, Color.decode(isException ? "#ffffff" : "#f1e2f1"),0, getHeight(), Color.decode(isException ? "#ad4949" : "#f7eef7")));
        g2d.fill(rectangle);
    }
}