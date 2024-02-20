package view.components.panels.additionalBenefits;

import lombok.Getter;
import lombok.Setter;
import javax.swing.*;

@Getter
@Setter
public class FacePanel extends JPanel {

    private javax.swing.JLabel deleteLabel;
    private javax.swing.JLabel editLabel;
    private javax.swing.JLabel nameLabel;

    public FacePanel() {
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {

        deleteLabel = new JLabel();
        editLabel = new JLabel();
        nameLabel = new JLabel();

        setPreferredSize(new java.awt.Dimension(322, 50));
        setVerifyInputWhenFocusTarget(false);

        deleteLabel.setMaximumSize(new java.awt.Dimension(25, 30));
        deleteLabel.setMinimumSize(new java.awt.Dimension(25, 30));
        deleteLabel.setPreferredSize(new java.awt.Dimension(25, 30));

        editLabel.setAutoscrolls(true);

        nameLabel.setText("Name");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(editLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }
}