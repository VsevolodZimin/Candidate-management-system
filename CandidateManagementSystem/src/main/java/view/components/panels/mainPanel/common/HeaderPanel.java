
package view.components.panels.mainPanel.common;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import lombok.Getter;
import util.connection.PathsPropertiesManager;
import view.RoundedTextField;

@Getter
public class HeaderPanel extends JPanel {

    private JButton addButton;
    private ButtonGroup buttonGroup1;
    private JToggleButton currentButton;
    private JLabel findLB;
    private JToggleButton historicalButton;
    private JToggleButton importantButton;
    private JToggleButton overdueButton;
    private RoundedTextField searchBox;
    private OverdueWarningLabel warningLabel;

    public HeaderPanel() {
        setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb"), 1));
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
        buttonGroup1 = new ButtonGroup();
        findLB = new JLabel();
        addButton = new JButton();
        searchBox = new view.RoundedTextField();
        warningLabel = new view.components.panels.mainPanel.common.OverdueWarningLabel();
        currentButton = new JToggleButton();
        importantButton = new JToggleButton();
        historicalButton = new JToggleButton();
        overdueButton = new JToggleButton();

        findLB.setText("Find");

        addButton.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "add.png"));
        addButton.setFocusPainted(false);

        warningLabel.setForeground(new Color(255, 255, 255));
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        warningLabel.setMaximumSize(new Dimension(29, 27));
        warningLabel.setMinimumSize(new Dimension(29, 27));
        warningLabel.setPreferredSize(new Dimension(29, 27));

        buttonGroup1.add(currentButton);
        currentButton.setText("Current");
        currentButton.setFocusPainted(false);

        buttonGroup1.add(importantButton);
        importantButton.setText("Important");
        importantButton.setFocusPainted(false);

        buttonGroup1.add(historicalButton);
        historicalButton.setText("Historical");
        historicalButton.setFocusPainted(false);

        buttonGroup1.add(overdueButton);
        overdueButton.setText("Overdue");
        overdueButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(currentButton)
                .addGap(8, 8, 8)
                .addComponent(historicalButton)
                .addGap(8, 8, 8)
                .addComponent(importantButton)
                .addGap(8, 8, 8)
                .addComponent(overdueButton)
                .addGap(8, 8, 8)
                .addComponent(warningLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 589, Short.MAX_VALUE)
                .addComponent(findLB)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBox, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(overdueButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historicalButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(findLB)
                                .addComponent(searchBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                            .addComponent(warningLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(importantButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );
    }
}