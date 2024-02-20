package view.components.panels.newPositionPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import lombok.Getter;
import lombok.Setter;
import util.enums.BoxType;
import view.RoundedNumericTextField;
import view.RoundedTextArea;
import view.RoundedTextField;
import view.components.comboBox.CustomComboBox;
import view.components.comboBox.CustomComboBoxModel;

import javax.swing.*;

@Setter
@Getter
public class NewPositionPanel extends JPanel {

    private RoundedTextArea additionalInfo;
    private RoundedNumericTextField budgetField;
    private CustomComboBox countryField;
    private JLabel errorTag;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private RoundedTextField nameField;
    private CustomComboBox profileField;
    private JButton saveButton;
    public NewPositionPanel() {
        setOpaque(false);
        initComponents();
        initCustomComponents();
    }
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        saveButton = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        nameField = new view.RoundedTextField();
        countryField = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.COUNTRY));
        profileField = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.PROFILE));
        budgetField = new view.RoundedNumericTextField();
        additionalInfo = new view.RoundedTextArea();
        errorTag = new JLabel();

        jLabel1.setText("Name");

        jLabel4.setText("Profile");

        jLabel5.setText("Budget");

        saveButton.setText("Save");
        saveButton.setFocusPainted(false);

        jLabel2.setText("Notes");

        jLabel3.setText("Country");

        nameField.setMinimumSize(new java.awt.Dimension(0, 0));
        nameField.setPreferredSize(new java.awt.Dimension(360, 32));

        countryField.setMinimumSize(new java.awt.Dimension(0, 0));
        countryField.setPreferredSize(new java.awt.Dimension(360, 32));

        profileField.setMinimumSize(new java.awt.Dimension(0, 0));
        profileField.setPreferredSize(new java.awt.Dimension(360, 32));

        budgetField.setText("numericTextField1");
        budgetField.setMinimumSize(new java.awt.Dimension(0, 0));
        budgetField.setPreferredSize(new java.awt.Dimension(360, 32));

        additionalInfo.setMinimumSize(new java.awt.Dimension(0, 0));
        additionalInfo.setPreferredSize(new java.awt.Dimension(360, 200));

        errorTag.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 10));
        errorTag.setText("Position with this name already exists. Use the search box :)");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(budgetField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(additionalInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profileField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countryField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(errorTag, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorTag)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(countryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(profileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(budgetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(additionalInfo, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addComponent(saveButton)
                .addGap(17, 17, 17))
        );
    }
    
        @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
    private void initCustomComponents() {
        errorTag.setForeground(new Color(255,255,255,0));
    }
}