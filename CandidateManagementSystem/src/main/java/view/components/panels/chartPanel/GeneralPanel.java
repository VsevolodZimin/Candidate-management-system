package view.components.panels.chartPanel;

import view.components.panels.NoDataPanel;

import lombok.Getter;
import lombok.Setter;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.entity.CategoryItemEntity;
import sevices.JDBCStatisticsService;
import util.enums.Profile;
import util.enums.Stage;

import javax.swing.*;
import java.awt.*;


@Getter
@Setter
public class GeneralPanel extends JPanel {

    private JToggleButton allButton;
    private JToggleButton averageButton;
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JToggleButton directorButton;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private JToggleButton juniorButton;
    private JToggleButton managerButton;
    private JToggleButton medianButton;
    private JToggleButton middleButton;
    private JButton positionButton;
    private JToggleButton seniorButton;
    private JPanel currentPanel;
    private StatisticsPanelManager panelManager;
    private GeneralChart chart;
    private StatisticsContext context;
    private JDBCStatisticsService service;
    private NoDataPanel noDataPanel;
    
    
    public GeneralPanel(StatisticsPanelManager panelManager){
        initComponents();
        headerPanel.setBackground(Color.decode("#faf2f9"));
        footerPanel.setBackground(Color.decode("#faf2f9"));
        this.panelManager = panelManager;
        this.context = panelManager.getContext();
        this.service = panelManager.getService();
        this.noDataPanel = new NoDataPanel();
        chart = new GeneralChart(service);
        add(chart);
        addCentralTendenciesButtonListeners();
        addRadioButtonListeners();
        addPositionButtonListener();
        addChartMouseListener();
    }
    
    private void addPositionButtonListener(){
        positionButton.addActionListener(e -> panelManager.loadPositionDetailsPanel());
    }
    
    private void addCentralTendenciesButtonListeners(){
        averageButton.addActionListener(e -> {
            context.setMean(true);
            updateGeneralPanel();
        });
        
        medianButton.addActionListener(e -> {
            context.setMean(false);
            updateGeneralPanel();
        });
    }
    
    private void addRadioButtonListeners(){
        
        juniorButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.JUNIOR);
            updateGeneralPanel();
        });
        
        
        middleButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.MIDDLE);
            updateGeneralPanel();
        });
        
        seniorButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.SENIOR);
            updateGeneralPanel();
        });
        
        managerButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.MANAGER);
            updateGeneralPanel();
        });
        
        directorButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.DIRECTOR);
            updateGeneralPanel();
        });
        
        allButton.addActionListener(e -> {
            context.setCandidateProfile(Profile.NONE);
            updateGeneralPanel();
        });
    }
    
    private void addChartMouseListener(){
        chart.addChartMouseListener(new ChartMouseListener(){
        
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                if(cme.getEntity() instanceof CategoryItemEntity bar){
                    String column = (String) bar.getColumnKey();
                    switch (column) {
                        case "Phone screening" -> panelManager.loadCandidateDetailsPanel(Stage.PHONE_SCREENING);
                        case "HR interview" -> panelManager.loadCandidateDetailsPanel(Stage.HR_INTERVIEW);
                        case "Submitted to HM" -> panelManager.loadCandidateDetailsPanel(Stage.SUBMITTED_TO_HM);
                        case "HM interview" -> panelManager.loadCandidateDetailsPanel(Stage.HM_INTERVIEW);
                        case "Test" -> panelManager.loadCandidateDetailsPanel(Stage.TEST);
                        case "Offer letter" -> panelManager.loadCandidateDetailsPanel(Stage.OFFER_LETTER);
                        case "Selection closed" -> panelManager.loadCandidateDetailsPanel(Stage.SELECTION_CLOSED);
                        case "Whole process" -> panelManager.loadCandidateDetailsPanel(Stage.NONE);
                    }
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {}
        });
    }
    
     public void updateGeneralPanel(){

        float[] centralTendencies = service.getCentralTendencies(context.isMean(), context.getCandidateProfile());

        if(isDataPresent(centralTendencies) && currentPanel == null) {
            add(chart, BorderLayout.CENTER);
            chart.getDataSet().updateData(context.isMean(), context.getCandidateProfile());
            currentPanel = chart;
        }
        else if(isDataPresent(centralTendencies) && currentPanel instanceof NoDataPanel) {
            remove(currentPanel);
            add(chart, BorderLayout.CENTER);
            chart.getDataSet().updateData(context.isMean(), context.getCandidateProfile());
            currentPanel = chart;
        }
        else if(isDataPresent(centralTendencies) && currentPanel instanceof GeneralChart) {
            chart.getDataSet().updateData(context.isMean(), context.getCandidateProfile());
        }
        else if(!isDataPresent(centralTendencies) && currentPanel == null) {
            add(noDataPanel, BorderLayout.CENTER);
            currentPanel = noDataPanel;
        }
        else if(!isDataPresent(centralTendencies) && currentPanel instanceof GeneralChart) {
            remove(currentPanel);
            add(noDataPanel, BorderLayout.CENTER);
            currentPanel = noDataPanel;
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
    
    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        headerPanel = new JPanel();
        positionButton = new JButton();
        footerPanel = new JPanel();
        averageButton = new JToggleButton();
        medianButton = new JToggleButton();
        directorButton = new JToggleButton();
        managerButton = new JToggleButton();
        seniorButton = new JToggleButton();
        middleButton = new JToggleButton();
        juniorButton = new JToggleButton();
        allButton = new JToggleButton();

        setLayout(new BorderLayout());

        positionButton.setText("Positions");
        positionButton.setFocusPainted(false);

        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(positionButton)
                .addContainerGap(833, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(positionButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(headerPanel, BorderLayout.PAGE_START);

        buttonGroup2.add(averageButton);
        averageButton.setText("Mean");
        averageButton.setFocusPainted(false);

        buttonGroup2.add(medianButton);
        medianButton.setText("Median");
        medianButton.setFocusPainted(false);

        buttonGroup1.add(directorButton);
        directorButton.setText("Director");
        directorButton.setFocusPainted(false);

        buttonGroup1.add(managerButton);
        managerButton.setText("Manager");
        managerButton.setFocusPainted(false);

        buttonGroup1.add(seniorButton);
        seniorButton.setText("Senior");
        seniorButton.setFocusPainted(false);

        buttonGroup1.add(middleButton);
        middleButton.setText("Middle");
        middleButton.setFocusPainted(false);

        buttonGroup1.add(juniorButton);
        juniorButton.setText("Junior");
        juniorButton.setFocusPainted(false);

        buttonGroup1.add(allButton);
        allButton.setText("All");
        allButton.setFocusPainted(false);

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
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(averageButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(medianButton)
                .addGap(17, 17, 17))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(averageButton)
                        .addComponent(medianButton))
                    .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(allButton)
                        .addComponent(juniorButton)
                        .addComponent(middleButton)
                        .addComponent(seniorButton)
                        .addComponent(managerButton)
                        .addComponent(directorButton)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(footerPanel, BorderLayout.PAGE_END);
    }
}