package view.components.panels.customOptionPanel;

import lombok.Getter;
import util.connection.PathsPropertiesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;


@Getter
public class CustomOptionMessage extends JPanel {

    private final int min = GroupLayout.DEFAULT_SIZE;
    private final int pref;
    private final int max  = Short.MAX_VALUE;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel iconTag;
    private JLabel textTag;

    public CustomOptionMessage(String errorText, int pref) {
        this.pref = pref;
        initComponents();
        textTag.setText(errorText);
    }
    private void initComponents() {

        cancelButton = new JButton();
        iconTag = new JLabel();
        textTag = new JLabel();
        confirmButton = new JButton();

        cancelButton.setText("Cancel");
        cancelButton.setFocusPainted(false);

        iconTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "question.png"));

        textTag.setHorizontalAlignment(SwingConstants.LEFT);

        confirmButton.setText("Ok");
        confirmButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(iconTag)
                .addGap(18, 18, 18)
                .addComponent(textTag, min, pref, max)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(57, 57, 57)
                .addComponent(confirmButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(textTag, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconTag, GroupLayout.Alignment.TRAILING))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(confirmButton))
                .addGap(23, 23, 23))
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Area rectangle = new Area(new Rectangle.Double(0,0,getWidth(), getHeight()));
        Area round = new Area(new RoundRectangle2D.Double(0,0,getWidth(), getHeight(), 25,25));
        rectangle.subtract(round);
        g2d.setPaint(new Color(0,0,0,0));
        g2d.fill(rectangle);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(round);
    }
}