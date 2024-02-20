package view.components.panels.additionalBenefits;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import lombok.Getter;
import lombok.Setter;
import view.RoundedTextField;
import javax.swing.*;

@Getter
@Setter
public class AdditionalBenefitsPanel extends JPanel {

    private RoundedTextField existingField;
    private JLabel existingLabel;
    private RoundedTextField newField;
    private JLabel newLabel;
    private PickingBox pickedBox;
    private JButton saveButton;
    private PickingBox toPickBox;

    public AdditionalBenefitsPanel() {
        initComponents();
        initCustomComponents();
    }

    private void initComponents() {

        saveButton = new JButton();
        existingLabel = new JLabel();
        newLabel = new JLabel();
        newField = new RoundedTextField();
        existingField = new RoundedTextField();
        pickedBox = new PickingBox();
        toPickBox = new PickingBox();
        saveButton.setText("Save");
        saveButton.setFocusPainted(false);
        existingLabel.setText("Find existing label");
        newLabel.setText("Create new label");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(toPickBox, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                    .addComponent(pickedBox, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(existingLabel)
                            .addComponent(newLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(newField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(existingField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(31, 31, 31))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(existingLabel)
                    .addComponent(existingField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(newLabel)
                    .addComponent(newField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(toPickBox, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
                    .addComponent(pickedBox, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(16, 16, 16))
        );
    }

    private void initCustomComponents() {
        setOpaque(false);
        toPickBox.getHeader().setText("Benefits to pick from");
        pickedBox.getHeader().setText("Picked Benefits");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}