package view.components.panels.chartPanel;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;


@Getter
public class DetailsPanel extends JPanel {

    private JButton backButton;
    private Box.Filler filler1;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private final DetailsChart chart;
    private final StatisticsPanelManager panelManager;
    
    public DetailsPanel(StatisticsPanelManager panelManager) {
        initComponents();
        headerPanel.setBackground(Color.decode("#faf2f9"));
        footerPanel.setBackground(Color.decode("#faf2f9"));
        this.panelManager = panelManager;
        chart = new DetailsChart(panelManager.getService(), panelManager.getContext());
        add(chart, BorderLayout.CENTER);
        addBackButtonListener();
    }
    
    private void addBackButtonListener(){
        backButton.addActionListener(e -> {
            panelManager.loadGeneralCandidatePanel();
            panelManager.setCurrentPanel(panelManager.getGeneralPanel());
        });
    }

    private void initComponents() {

        headerPanel = new JPanel();
        backButton = new JButton();
        footerPanel = new JPanel();
        filler1 = new Box.Filler(new Dimension(0, 24), new Dimension(0, 24), new Dimension(32767, 24));

        setLayout(new BorderLayout());

        backButton.setText("back");
        backButton.setFocusPainted(false);

        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(backButton)
                .addContainerGap(393, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        add(headerPanel, BorderLayout.PAGE_START);

        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                .addGap(0, 479, Short.MAX_VALUE)
                .addComponent(filler1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filler1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
        );

        add(footerPanel, BorderLayout.PAGE_END);
    }
}