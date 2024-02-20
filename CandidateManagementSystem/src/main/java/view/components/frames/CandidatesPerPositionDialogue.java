package view.components.frames;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import sevices.JDBCPositionService;
import view.components.panels.candidatePerPositionPanel.CandidatePerPositionPanel;

public class CandidatesPerPositionDialogue extends javax.swing.JDialog {
    
    private CandidatePerPositionPanel candidatePerPositionPanel;
    
    public CandidatesPerPositionDialogue(Frame parent, boolean modal, JDBCPositionService service, int id) {
        super(parent, modal);
        initComponents(service, id);
    }
    
    private void initComponents(JDBCPositionService service, int id) {
        candidatePerPositionPanel = new CandidatePerPositionPanel(service, id);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        candidatePerPositionPanel.setLayout(new BoxLayout(candidatePerPositionPanel, BoxLayout.Y_AXIS));
        getContentPane().add(candidatePerPositionPanel, BorderLayout.CENTER);
        pack();
    }
    
    public void setDialogueVisible(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}