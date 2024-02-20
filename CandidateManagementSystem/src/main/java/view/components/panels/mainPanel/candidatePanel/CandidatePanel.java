package view.components.panels.mainPanel.candidatePanel;

import controller.Context;
import entity.CandidateEntity;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCCandidateService;
import util.enums.ContentType;
import util.enums.Result;
import util.enums.Stage;
import view.components.frames.NewCandidateJDialog;
import view.components.panels.mainPanel.candidatePanel.table.CandidateTable;
import view.components.panels.mainPanel.candidatePanel.table.CandidateTableModel;
import view.components.panels.mainPanel.common.CustomPanel;

@Getter 
@Setter 
public class CandidatePanel extends CustomPanel {
    private StageButtonPanel stageButtons;
    private ResultButtonPanel resultButtons;
    private JPanel noButtons;
    private JPanel currentButtonPanel;
    
    private JToggleButton allStageButton;
    private JToggleButton phoneScreeningButton;
    private JToggleButton HRInterviewButton;
    private JToggleButton submittedToHMButton;
    private JToggleButton HMInterviewButton;
    private JToggleButton testButton;
    private JToggleButton offerLetterButton;
    private JToggleButton selectionClosedButton;
    private JToggleButton currentAgencyButton;
    private JToggleButton currentFitForHireButton;

    private JToggleButton allResultButton;
    private JToggleButton offerDeclinedButton;
    private JToggleButton hrDiscardsButton;
    private JToggleButton hmDiscardsButton;
    private JToggleButton hiredButton;
    private JToggleButton hired;
    private JToggleButton historyAgencyButton;
    private JToggleButton historyFitForHireButton;
    
    private boolean isNoDataPanel = false;


    
    private JDBCCandidateService candidateService;
    

    public void initialize () {
        this.candidateService = Context.getCandidateService();
        super.initializePanel(new CandidateTable());
        table.initializeTable(searchBox);
        scrollPane.setViewportView(table);
        scrollPane.setName("candidate");
        headerPanel.getWarningLabel().updateLabelForCandidate();
        add(scrollPane, BorderLayout.CENTER);
        
        stageButtons = new StageButtonPanel();
        resultButtons = new ResultButtonPanel();
        noButtons = new JPanel();

        allStageButton = stageButtons.getAllButton();
        phoneScreeningButton = stageButtons.getPhoneScreening();
        HRInterviewButton = stageButtons.getHrInterview();
        submittedToHMButton = stageButtons.getSubmittedToHM();
        HMInterviewButton = stageButtons.getHmInterview();
        testButton = stageButtons.getTest();
        offerLetterButton = stageButtons.getOfferLetter();
        selectionClosedButton = stageButtons.getSelectionClosed();
        currentAgencyButton = stageButtons.getAgencyButton();
        currentFitForHireButton = stageButtons.getFitForHireButton();

        allResultButton = resultButtons.getAllButton();
        offerDeclinedButton = resultButtons.getOfferDeclinedButton();
        hrDiscardsButton = resultButtons.getHrDiscardsButton();
        hmDiscardsButton = resultButtons.getHmDiscardsButton();
        hiredButton = resultButtons.getHiredButton();
        historyAgencyButton = resultButtons.getAgencyButton();
        historyFitForHireButton = resultButtons.getFitForHireButton();

        Context.setStage(Stage.NONE);
        addListeners();
    }

    public void addListeners() {
        addButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event) {
                    NewCandidateJDialog dialogue = new NewCandidateJDialog(true);
                    dialogue.setVisible(true);
                    CandidateEntity candidate = dialogue.getCandidate();
                    if(dialogue.isSaved()) {
                        candidateService.create(candidate);
                        getModel().populateTable();
                    }
                    dialogue.dispose();
                }
        });

       currentButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.CURRENT);
                Context.setStage(Stage.NONE);
                Context.setFromAgency(false);
                Context.setFitForHire(false);
                getModel().populateTable();
                updateButtonPanel(ContentType.CURRENT);
            }
        });
        
        historicalButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.HISTORICAL);
                Context.setStage(Stage.NONE);
                Context.setFromAgency(false);
                Context.setFitForHire(false);
                getModel().populateTable();
                updateButtonPanel(ContentType.HISTORICAL);
            }
        });
        
        overdueButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.OVERDUE);
                Context.setStage(Stage.NONE);
                Context.setFromAgency(false);
                Context.setFitForHire(false);
                getModel().populateTable();
                updateButtonPanel(ContentType.OVERDUE);

            }
        });
        
        importantButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.IMPORTANT);
                Context.setStage(Stage.NONE);
                Context.setFromAgency(false);
                Context.setFitForHire(false);
                getModel().populateTable();
                updateButtonPanel(ContentType.IMPORTANT);
            }
        });
        
        searchBox.getDocument().addDocumentListener(new DocumentListener() {  
            @Override
            public void insertUpdate(DocumentEvent e) {
                getModel().fireTableDataChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getModel().fireTableDataChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getModel().fireTableDataChanged();
            }
        });
        
        allStageButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.NONE));
        phoneScreeningButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.PHONE_SCREENING));
        HRInterviewButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.HR_INTERVIEW));
        submittedToHMButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.SUBMITTED_TO_HM));
        HMInterviewButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.HM_INTERVIEW));
        testButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.TEST));
        offerLetterButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.OFFER_LETTER));
        selectionClosedButton.addActionListener((ActionEvent e) -> filterTableContentsByStage(Stage.SELECTION_CLOSED));
        allResultButton.addActionListener((ActionEvent e) -> filterTableContentsByResult(Result.NONE));
        offerDeclinedButton.addActionListener((ActionEvent e) -> filterTableContentsByResult(Result.OFFER_DECLINED));
        hrDiscardsButton.addActionListener((ActionEvent e) -> filterTableContentsByResult(Result.HR_DISCARDS));
        hmDiscardsButton.addActionListener((ActionEvent e) -> filterTableContentsByResult(Result.HM_DISCARDS));
        hiredButton.addActionListener((ActionEvent e) -> filterTableContentsByResult(Result.HIRED));
        currentAgencyButton.addActionListener((ActionEvent e) -> filterFromAgency());
        historyAgencyButton.addActionListener((ActionEvent e) -> filterFromAgency());
        historyFitForHireButton.addActionListener((ActionEvent e) -> filterFitForHire());
        currentFitForHireButton.addActionListener((ActionEvent e) -> filterFitForHire());
    }
    
    
    public void updateButtonPanel(ContentType type){
        if(currentButtonPanel != null) {
            buttonPanel.remove(currentButtonPanel);
        }
        switch(type){
            case CURRENT -> {
                buttonPanel.add(stageButtons, BorderLayout.CENTER);
                currentButtonPanel = stageButtons;
                allStageButton.setSelected(true);
                currentAgencyButton.setSelected(false);
                currentFitForHireButton.setSelected(false);
            }
            case HISTORICAL -> {
                buttonPanel.add(resultButtons, BorderLayout.CENTER);
                currentButtonPanel = resultButtons;
                allResultButton.setSelected(true);
                historyAgencyButton.setSelected(false);
                historyFitForHireButton.setSelected(false);

            }
            default -> {
                buttonPanel.add(noButtons, BorderLayout.CENTER);
                currentButtonPanel = noButtons;
            }
        }
        revalidate();
        repaint();
    }
    
    private void filterFromAgency(){
            Context.setFromAgency(!Context.isFromAgency());
            getModel().populateTable();
            revalidate();
            repaint();
       }
    
    private void filterFitForHire(){
        Context.setFitForHire(!Context.isFitForHire());
        getModel().populateTable();
        revalidate();
        repaint();
    }
    
    private void filterTableContentsByStage(Stage stage){
        Context.setStage(stage);
        getModel().populateTable();
        revalidate();
        repaint();
    }
    
    
    private void filterTableContentsByResult(Result result){
        Context.setResult(result);
        getModel().populateTable();
        revalidate();
        repaint();
    }
    
    private CandidateTableModel getModel(){
        return (CandidateTableModel)table.getModel();
    }
}