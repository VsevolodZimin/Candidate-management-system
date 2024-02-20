package view.components.panels.mainPanel.common;

import lombok.Getter;
import lombok.Setter;
import sevices.JDBCCandidateService;
import sevices.JDBCLabelService;
import sevices.JDBCPositionService;
import util.enums.ContentType;
import util.enums.Result;
import util.enums.Stage;

import java.awt.*;

@Setter
@Getter
public class Context {

    private Frame parentFrame;
    private JDBCPositionService positionService;
    private JDBCCandidateService candidateService;
    private JDBCLabelService labelService;
    private ContentType type = ContentType.CURRENT;
    private Stage stage = Stage.NONE;
    private Result result = Result.NONE;
    private boolean isFromAgency;
    private boolean isFitForHire;

    public Context(Frame parentFrame, JDBCPositionService positionService, JDBCCandidateService candidateService, JDBCLabelService labelService){
        this.parentFrame = parentFrame;
        this.positionService = positionService;
        this.candidateService = candidateService;
        this.labelService = labelService;
    }
}