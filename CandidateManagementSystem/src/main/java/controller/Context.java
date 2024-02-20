package controller;

import lombok.Getter;
import lombok.Setter;
import sevices.JDBCCandidateService;
import sevices.JDBCLabelService;
import sevices.JDBCPositionService;
import util.enums.ContentType;
import util.enums.Result;
import util.enums.Stage;

import java.awt.*;
import view.components.panels.NoDataPanel;
import view.components.panels.mainPanel.candidatePanel.CandidatePanel;
import view.components.panels.mainPanel.positionPanel.PositionPanel;

public class Context {

    @Setter
    @Getter
    private static Frame parentFrame;
    
    @Setter
    @Getter
    private static CandidatePanel candidatePanel;
    
    @Setter
    @Getter
    private static PositionPanel positionPanel;
    
    @Setter
    @Getter
    private static NoDataPanel noDataPanel = new NoDataPanel();
    
    @Setter
    @Getter
    private static JDBCPositionService positionService;
    
    @Setter
    @Getter
    private static JDBCCandidateService candidateService;
    
    @Setter
    @Getter
    private static JDBCLabelService labelService;
    
    @Setter
    @Getter
    private static ContentType type = ContentType.CURRENT;
    
    @Setter
    @Getter
    private static Stage stage = Stage.NONE;
    
    @Setter
    @Getter
    private static Result result = Result.NONE;
    
    @Setter
    @Getter
    private static boolean isFromAgency;
    
    @Getter
    private static boolean isFitForHire;

    private Context(){
    }
    
    public static void setFitForHire(boolean b){
        isFitForHire = b;
    }
    
    public static void reset(){
        setType(ContentType.CURRENT);
        setStage(Stage.NONE);
        setResult(Result.NONE);
        setFromAgency(false);
        setFitForHire(false);
        candidatePanel.getHeaderPanel().getCurrentButton().setSelected(true);
        positionPanel.getHeaderPanel().getCurrentButton().setSelected(true);
    }

    public static void updateWarningCountForCandidate() {
        candidatePanel.getHeaderPanel().getWarningLabel().updateLabelForCandidate();
    }
}