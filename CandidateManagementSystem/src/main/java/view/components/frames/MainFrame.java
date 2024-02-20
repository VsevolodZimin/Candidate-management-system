package view.components.frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import sevices.JDBCCandidateService;
import sevices.JDBCLabelService;
import sevices.JDBCPositionService;
import sevices.JDBCStatisticsService;
import util.connection.PathsPropertiesManager;
import util.enums.ContentType;
import view.components.panels.exportPanel.ExportPanel;
import view.components.panels.chartPanel.StatisticsContext;
import view.components.panels.chartPanel.StatisticsPanelManager;
import view.components.panels.mainPanel.candidatePanel.CandidatePanel;
import view.components.panels.mainPanel.candidatePanel.table.CandidateTable;
import view.components.panels.mainPanel.candidatePanel.table.CandidateTableModel;
import controller.Context;
import java.awt.Toolkit;
import view.components.panels.mainPanel.common.CustomPanel;
import view.components.panels.mainPanel.common.CustomTable;
import view.components.panels.mainPanel.common.CustomTableModel;
import view.components.panels.mainPanel.positionPanel.PositionPanel;
import view.components.panels.mainPanel.positionPanel.table.PositionTableModel;
import view.components.panels.menu.MenuPanel;
import view.components.panels.welcomePanel.WelcomePanel;


@Getter
public class MainFrame extends JFrame {

//Current:
    private JPanel currentPanel;
    private CustomTable currentTable;

// Declaring components:
    private CandidatePanel candidatePanel;
    private PositionPanel positionPanel;
    private ExportPanel exportPanel;
    private WelcomePanel welcomePanel;
    private MenuPanel menuPanel;

//Declaring services
    private final JDBCCandidateService candidateService;
    private final JDBCPositionService positionService;    
    private final JDBCLabelService labelService;
    private final JDBCStatisticsService statisticsService;
    private StatisticsPanelManager statisticsPanelManager;

    public MainFrame(JDBCCandidateService candidateService, JDBCPositionService positionService, JDBCLabelService labelService, JDBCStatisticsService statisticsService){
        this.candidateService = candidateService;
        this.positionService = positionService;
        this.labelService = labelService;
        this.statisticsService = statisticsService;
        Context.setPositionService(positionService);
        Context.setCandidateService(candidateService);
        Context.setLabelService(labelService);
        init();
    }    
    
    private void init(){
            
        //Initializing components
        menuPanel = new MenuPanel();
        welcomePanel = new WelcomePanel();
        candidatePanel = new CandidatePanel();
        positionPanel = new PositionPanel();
        exportPanel = new ExportPanel();
        welcomePanel = new WelcomePanel();
        statisticsPanelManager = new StatisticsPanelManager(new StatisticsContext(), statisticsService);

        getContentPane().add(menuPanel, BorderLayout.LINE_START);
        getContentPane().add(candidatePanel, BorderLayout.CENTER);
        candidatePanel.initialize();
        Context.setCandidatePanel(candidatePanel);

        getContentPane().add(positionPanel, BorderLayout.CENTER);
        positionPanel.initialize();
        Context.setPositionPanel(positionPanel);

        
        getContentPane().add(statisticsPanelManager.getStatisticsPanel(), BorderLayout.CENTER);
        getContentPane().add(welcomePanel, BorderLayout.CENTER);


//ON MENU ITEM PRESSED CODE:
        menuPanel.registerCallback((int index) -> {
            
                switch(index){
                    case 0 -> {
                        Context.setType(ContentType.CURRENT);
                        Context.reset();
                        setPanel(candidatePanel);
                        ((CandidateTableModel)candidatePanel.getTable().getModel()).populateTable();
                        ((CandidateTableModel)candidatePanel.getTable().getModel()).setPanel();
                        ((CandidateTable)candidatePanel.getTable()).getPositionBox().updateItems();
                        candidatePanel.updateButtonPanel(ContentType.CURRENT);
                    }
            
                    case 1 -> {
                        Context.setType(ContentType.CURRENT);
                        Context.reset();
                        setPanel(positionPanel);
                        ((PositionTableModel)positionPanel.getTable().getModel()).populateTable();
                        ((PositionTableModel)positionPanel.getTable().getModel()).setPanel();
                    }
            
                    case 2 -> {
                        setPanel(statisticsPanelManager.getStatisticsPanel());
                        statisticsPanelManager.loadGeneralCandidatePanel();
                    }
                    
                    case 3 -> setPanel(exportPanel);
                }
            revalidate();
            repaint();
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "175.png"));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
        
    private void setPanel (JPanel toSet) {
        if (welcomePanel != null) {
            getContentPane().remove(welcomePanel);
            getContentPane().add(toSet);
            if (toSet instanceof CandidatePanel cPanel) {
                currentTable = cPanel.getTable();
            } else if (toSet instanceof PositionPanel pPanel) {
                currentTable = pPanel.getTable();
            }
            welcomePanel = null;
        } else {
            getContentPane().remove(currentPanel);
            getContentPane().add(toSet);
            if (toSet instanceof CandidatePanel cPanel) {
                currentTable = cPanel.getTable();
            } else if (toSet instanceof PositionPanel pPanel) {
                currentTable = pPanel.getTable();
            }
        }
        currentPanel = toSet;
        if (currentPanel instanceof CustomPanel cp) {
            ((CustomTableModel) cp.getTable().getModel()).clearTable();

        }
    }
}