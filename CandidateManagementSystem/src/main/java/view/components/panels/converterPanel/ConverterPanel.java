package view.components.panels.converterPanel;


import lombok.Getter;
import view.RoundedNumericTextField;
import view.components.panels.converterPanel.flagDropBox.FlagComboBox;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

@Getter
public class ConverterPanel extends JPanel {

    private JButton convertButton;
    private FlagComboBox currencyPicker;
    private RoundedNumericTextField foreignCurField;
    private JLabel fromTag;
    private JLabel fromTag1;
    private RoundedNumericTextField gbpField;
    private JLabel toTag;
    public ConverterPanel() {
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {

        fromTag = new JLabel();
        toTag = new JLabel();
        fromTag1 = new JLabel();
        currencyPicker = new view.components.panels.converterPanel.flagDropBox.FlagComboBox();
        foreignCurField = new view.RoundedNumericTextField();
        gbpField = new view.RoundedNumericTextField();
        convertButton = new JButton();

        fromTag.setText("Value");

        toTag.setText("To GBP");

        fromTag1.setText("Currency");

        foreignCurField.setText("roundedNumericTextField1");

        gbpField.setText("roundedNumericTextField2");

        convertButton.setText("Save");
        convertButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(toTag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromTag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromTag1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(gbpField, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(foreignCurField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(currencyPicker, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(convertButton)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(fromTag1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(currencyPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fromTag)
                    .addComponent(foreignCurField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gbpField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(toTag))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(convertButton)
                .addGap(14, 14, 14))
        );
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g2d);
    }
}