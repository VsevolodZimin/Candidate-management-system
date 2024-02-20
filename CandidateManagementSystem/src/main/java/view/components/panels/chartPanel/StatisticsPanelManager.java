package view.components.panels.chartPanel;

import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCStatisticsService;
import util.Utils;
import util.enums.Profile;
import util.enums.Stage;

@Getter
@Setter
public class StatisticsPanelManager {

    private StatisticsPanel statisticsPanel;
    private PositionPanel positionPanel;
    private GeneralPanel generalPanel;
    private DetailsPanel detailsPanel;
    private JPanel currentPanel;
    private StatisticsContext context;
    private JDBCStatisticsService service;

    public StatisticsPanelManager(StatisticsContext context, JDBCStatisticsService service) {
        this.context = context;
        this.service = service;
        this.statisticsPanel = new StatisticsPanel(this);
        this.positionPanel = new PositionPanel(this);
        this.generalPanel = new GeneralPanel(this);
        this.detailsPanel = new DetailsPanel(this);
    }
    
    public void loadGeneralCandidatePanel(){
        
            if(currentPanel != null){
                statisticsPanel.remove(currentPanel);
            }
            else {
                generalPanel.getAllButton().setSelected(true);
                generalPanel.getAverageButton().setSelected(true);
            }
            statisticsPanel.add(generalPanel);
            generalPanel.updateGeneralPanel();
            currentPanel = generalPanel;
            statisticsPanel.revalidate();
            statisticsPanel.repaint();
        
    }
    
    public void loadCandidateDetailsPanel(Stage stage) {

        if(currentPanel != null){
            statisticsPanel.remove(currentPanel);
        }
        detailsPanel.getChart().getChart().setTitle(Utils.getStringFromStage(stage));
        detailsPanel.getChart().getDataSet().updateCandidateData(stage, context.getCandidateProfile());
        detailsPanel.getChart().updateCandidateColor(stage);
        statisticsPanel.add(detailsPanel);
        currentPanel = detailsPanel;
        statisticsPanel.revalidate();
        statisticsPanel.repaint();
    }

    public void loadPositionDetailsPanel() {

        if(currentPanel != null){
            statisticsPanel.remove(currentPanel);
        }
        context.setPositionProfile(Profile.NONE);
        positionPanel.getAllButton().setSelected(true);
        positionPanel.getChart().getChart().setTitle("Position");
        positionPanel.getChart().getDataSet().updatePositionData(Profile.NONE);
        positionPanel.updatePositionChartPanel();
        positionPanel.getChart().updatePositionColor();
        statisticsPanel.add(positionPanel);
        currentPanel = positionPanel;
        statisticsPanel.revalidate();
        statisticsPanel.repaint();
    }
}