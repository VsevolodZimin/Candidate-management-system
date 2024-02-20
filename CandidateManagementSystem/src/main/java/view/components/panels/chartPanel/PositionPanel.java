package view.components.panels.chartPanel;

import view.components.panels.NoDataPanel;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCStatisticsService;
import util.enums.Profile;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class PositionPanel extends JPanel {

    private JDBCStatisticsService service;
    private StatisticsContext context;
    private StatisticsPanelManager panelManager;
    private NoDataPanel noDataPanel;
    private DetailsChart chart;
    private JPanel currentChartPanel;
    private JToggleButton allButton;
    private JButton backButton;
    private ButtonGroup buttonGroup1;
    private JToggleButton directorButton;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private JToggleButton juniorButton;
    private JToggleButton managerButton;
    private JToggleButton middleButton;
    private JToggleButton seniorButton;
    
    public PositionPanel(StatisticsPanelManager panelManager) {
        this.service = panelManager.getService();
        this.context = panelManager.getContext();
        this.chart = new DetailsChart(service, context);
        this.panelManager = panelManager;
        noDataPanel = new NoDataPanel();
        initComponents();
        headerPanel.setBackground(Color.decode("#faf2f9"));
        footerPanel.setBackground(Color.decode("#faf2f9"));
        add(chart);   
        addStageButtonListeners();
        addBackButtonListeners();
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        footerPanel = new JPanel();
        allButton = new JToggleButton();
        middleButton = new JToggleButton();
        juniorButton = new JToggleButton();
        seniorButton = new JToggleButton();
        managerButton = new JToggleButton();
        directorButton = new JToggleButton();
        headerPanel = new JPanel();
        backButton = new JButton();

        setLayout(new BorderLayout());

        buttonGroup1.add(allButton);
        allButton.setText("All");
        allButton.setFocusPainted(false);

        buttonGroup1.add(middleButton);
        middleButton.setText("Middle");
        middleButton.setFocusPainted(false);

        buttonGroup1.add(juniorButton);
        juniorButton.setText("Junior");
        juniorButton.setFocusPainted(false);

        buttonGroup1.add(seniorButton);
        seniorButton.setText("Senior");
        seniorButton.setFocusPainted(false);

        buttonGroup1.add(managerButton);
        managerButton.setText("Manager");
        managerButton.setFocusPainted(false);

        buttonGroup1.add(directorButton);
        directorButton.setText("Director");
        directorButton.setFocusPainted(false);

        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(allButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(juniorButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(middleButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seniorButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(managerButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(directorButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(906, Short.MAX_VALUE))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(allButton)
                    .addComponent(middleButton)
                    .addComponent(seniorButton)
                    .addComponent(managerButton)
                    .addComponent(directorButton)
                    .addComponent(juniorButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(footerPanel, BorderLayout.PAGE_END);

        backButton.setText("back");
        backButton.setFocusPainted(false);

        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(backButton)
                .addContainerGap(1451, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        add(headerPanel, BorderLayout.PAGE_START);
    }
        
    private void addBackButtonListeners(){
        backButton.addActionListener(e -> panelManager.loadGeneralCandidatePanel());
    }
    
    private void addStageButtonListeners(){
        allButton.addActionListener(e -> {
            context.setPositionProfile(Profile.NONE);
            chart.getChart().setTitle("Position (All)");
            updatePositionChartPanel();
        });
        
        juniorButton.addActionListener(e -> {
            context.setPositionProfile(Profile.JUNIOR);
            chart.getChart().setTitle("Position (junor)");
            updatePositionChartPanel();
        });
        
        middleButton.addActionListener(e -> {
            context.setPositionProfile(Profile.MIDDLE);
            chart.getChart().setTitle("Position (middle)");
            updatePositionChartPanel();
        });
        
        
        seniorButton.addActionListener(e -> {
            context.setPositionProfile(Profile.SENIOR);
            chart.getChart().setTitle("Position (senior)");
            updatePositionChartPanel();

        });
        
        managerButton.addActionListener(e -> {
            context.setPositionProfile(Profile.MANAGER);
            chart.getChart().setTitle("Position (manager)");
            updatePositionChartPanel();

        });
        
        directorButton.addActionListener(e -> {
            context.setPositionProfile(Profile.DIRECTOR);
            chart.getChart().setTitle("Position (director)");
            updatePositionChartPanel();
        });
    }
    
    
    public void updatePositionChartPanel(){
        
        float[] positionCount = new float[26];

        for (int i = 0; i <= 125; i = i + 5) {
            positionCount[i/5] = service.countPositions(true, i, i + 5, context.getPositionProfile());
        }

        if(isDataPresent(positionCount) && currentChartPanel == null) {
            add(chart, BorderLayout.CENTER);
            chart.getDataSet().updatePositionData(context.getPositionProfile());
            currentChartPanel = chart;
        }
        else if(isDataPresent(positionCount) && currentChartPanel instanceof NoDataPanel) {
            remove(currentChartPanel);
            add(chart, BorderLayout.CENTER);
            chart.getDataSet().updatePositionData(context.getPositionProfile());
            currentChartPanel = chart;
        }
        else if(isDataPresent(positionCount) && currentChartPanel instanceof DetailsChart) {
            chart.getDataSet().updatePositionData(context.getPositionProfile());
        }
        else if(!isDataPresent(positionCount) && currentChartPanel == null) {
            add(noDataPanel, BorderLayout.CENTER);
            currentChartPanel = noDataPanel;
        }
        else if(!isDataPresent(positionCount) && currentChartPanel instanceof DetailsChart) {
            remove(currentChartPanel);
            add(noDataPanel, BorderLayout.CENTER);
            currentChartPanel = noDataPanel;
        }
        revalidate();
        repaint();
    }
    
    private boolean isDataPresent(float[] centralTendencies) {
        for (float centralTendency : centralTendencies) {
            if (centralTendency > 0) {
                return true;
            }
        }
        return false;
    }
}