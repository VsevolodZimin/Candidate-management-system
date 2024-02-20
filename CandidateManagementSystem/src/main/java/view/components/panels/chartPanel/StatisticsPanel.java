package view.components.panels.chartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import lombok.Getter;
import sevices.JDBCStatisticsService;

@Getter
public class StatisticsPanel extends JPanel {
    
    private final JDBCStatisticsService service;
    private StatisticsContext context;
    private JButton positionButton;
    private JRadioButton allButton;
    private JRadioButton averageButton;
    private JRadioButton directorButton;
    private JPanel footerPanel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JRadioButton juniorButton;
    private JRadioButton managerButton;
    private JRadioButton medianButton;
    private JRadioButton middleButton;
    private JRadioButton seniorButton;

    public StatisticsPanel(StatisticsPanelManager panelManager) {
        this.service = panelManager.getService();
        initComponents();
    }
    
    private void initComponents() {
        context = new StatisticsContext();
        footerPanel = new JPanel();
        positionButton = new JButton();
        juniorButton = new JRadioButton();
        middleButton = new JRadioButton();
        seniorButton = new JRadioButton();
        managerButton = new JRadioButton();
        directorButton = new JRadioButton();
        allButton = new JRadioButton();
        medianButton = new JRadioButton();
        averageButton = new JRadioButton();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();

        setLayout(new BorderLayout());
        setOpaque(false);
        juniorButton.setText("Junior");
        middleButton.setText("Middle");
        seniorButton.setText("Senior");
        managerButton.setText("Manager");
        directorButton.setText("Director");
        allButton.setText("All");
        medianButton.setText("Median");
        averageButton.setText("Mean");
        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        jSeparator2.setOrientation(SwingConstants.VERTICAL);
        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(allButton)
                .addGap(18, 18, 18)
                .addComponent(juniorButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(middleButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seniorButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(managerButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(directorButton)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(averageButton)
                .addGap(18, 18, 18)
                .addComponent(medianButton)
                .addGap(19, 19, 19))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(juniorButton)
                        .addComponent(middleButton)
                        .addComponent(seniorButton)
                        .addComponent(managerButton)
                        .addComponent(directorButton)
                        .addComponent(allButton)
                        .addComponent(medianButton)
                        .addComponent(averageButton)))
                .addContainerGap(16, Short.MAX_VALUE))
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