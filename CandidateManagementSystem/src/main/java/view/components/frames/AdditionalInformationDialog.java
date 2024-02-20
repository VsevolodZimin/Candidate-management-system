package view.components.frames;

import controller.Context;
import entity.CandidateEntity;
import entity.LabelEntity;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import view.components.panels.candidateInfoPanel.CandidateInfoPanel;

@Getter
public class AdditionalInformationDialog extends JDialog {

    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField linkedToCVField;
    private JTextArea textPane;
    private JButton showButton;
    private JButton saveButton;
    private CandidateEntity candidate;
    private ArrayList<LabelEntity> pickedLabels;
    private CandidateInfoPanel additionalInformationPanel;
    
    public AdditionalInformationDialog(CandidateEntity candidate, boolean modal) {
        super(Context.getParentFrame(), modal);
        setResizable(false);
        initComponents();
        initialize(candidate);
        pickedLabels = new ArrayList<>(candidate.getAdditionalBenefits());
        addListener();
    }

    private void initialize(CandidateEntity candidate){
        this.candidate = candidate;
        phoneNumberField = additionalInformationPanel.getPhoneNumber();
        emailField = additionalInformationPanel.getEmail();
        linkedToCVField = additionalInformationPanel.getLinkToCV();
        textPane = additionalInformationPanel.getNotes().getTextArea();
        showButton = additionalInformationPanel.getShowButton();
        saveButton = additionalInformationPanel.getSaveButton();
        phoneNumberField.setText(candidate.getPhoneNumber());
        emailField.setText(candidate.getEmail());
        linkedToCVField.setText(candidate.getLinkToCV());
        textPane.setText(candidate.getAdditionalComments());
    }
    
    private void addListener(){
        
        showButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdditionalBenefitsDialogue dialogue = new AdditionalBenefitsDialogue(Context.getParentFrame(), true);
                dialogue.initialize(pickedLabels, Context.getLabelService());
                dialogue.setDialogueVisible();
                pickedLabels = dialogue.getCurrentPickedLabels();
                dialogue.dispose();
            }
        });
        
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                candidate.setPhoneNumber(phoneNumberField.getText());
                candidate.setEmail(emailField.getText());
                candidate.setLinkToCV(linkedToCVField.getText());
                candidate.setPhoneNumber(phoneNumberField.getText());
                candidate.setAdditionalComments(textPane.getText());
                setVisible(false);
            }
        });
    }

    private void initComponents() {
        additionalInformationPanel = new view.components.panels.candidateInfoPanel.CandidateInfoPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(additionalInformationPanel, java.awt.BorderLayout.CENTER);
        pack();
    }

    public void setDialogueVisible() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}