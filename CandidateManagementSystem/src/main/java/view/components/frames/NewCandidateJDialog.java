package view.components.frames;

import controller.Context;
import entity.CandidateEntity;
import entity.LabelEntity;
import entity.PositionEntity;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCLabelService;
import sevices.JDBCPositionService;
import util.Utils;
import util.enums.Result;
import view.components.comboBox.CustomComboBox;
import view.components.comboBox.PositionComboBox;
import view.RoundedNumericTextField;
import view.components.panels.newCandidatePanel.NewCandidatePanel;

@Getter
@Setter
public class NewCandidateJDialog extends JDialog {

    private boolean isSaved = false;
    private JDBCPositionService positionService;
    private JDBCLabelService labelService;
    private CandidateEntity candidate;
    private ArrayList<LabelEntity> picked = new ArrayList<>();
    private Frame parent;
    private NewCandidatePanel newCandidatePanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private CustomComboBox countryBox;
    private CustomComboBox profileBox;
    private CustomComboBox sourceBox;
    private PositionComboBox positionBox;
    private JTextField cvField;
    private JScrollPane scrollPanel;
    private JLabel currentSalaryConverter;
    private JLabel salaryExpectationsConverter;
    private RoundedNumericTextField currentSalaryField;
    private RoundedNumericTextField salaryExpectationsField;
    private JTextArea additionalInformationField;
    private JButton additionalBenefitsButton;
    private JButton saveButton;
    private ConverterDialogue converterDialogue;
    private ArrayList<LabelEntity> resultLabels = new ArrayList<>();

    private AbstractAction upAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
        }
    };
    private AbstractAction downAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
        }
    };
    
    public NewCandidateJDialog(boolean modal) {
        super(Context.getParentFrame(), modal);
        this.labelService = Context.getLabelService();
        this.positionService = Context.getPositionService();
        this.parent = Context.getParentFrame();
        this.candidate = new CandidateEntity();
        initComponents();
        firstNameField = newCandidatePanel.getFirstNameField();
        lastNameField = newCandidatePanel.getLastNameField();
        phoneNumberField = newCandidatePanel.getPhoneNumberField();
        emailField = newCandidatePanel.getEmailField();
        countryBox = newCandidatePanel.getCountryField();
        profileBox = newCandidatePanel.getProfileField();
        sourceBox = newCandidatePanel.getSourceField();
        positionBox = newCandidatePanel.getPositionField();
        cvField = newCandidatePanel.getCvField();
        currentSalaryField = newCandidatePanel.getCurrentSalaryField();
        salaryExpectationsField = newCandidatePanel.getSalaryExpectationsField();
        additionalInformationField = newCandidatePanel.getAdditionalInformationField().getTextArea();
        additionalBenefitsButton = newCandidatePanel.getAdditionalBenefitsButton();
        saveButton = newCandidatePanel.getSaveButton();
        currentSalaryConverter = newCandidatePanel.getCurrentSalaryConverter();
        salaryExpectationsConverter = newCandidatePanel.getSalaryExpectationConverter();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initializeListeners();
    }
    
    private void initComponents() {

        newCandidatePanel = new NewCandidatePanel(positionService);
        scrollPanel = new JScrollPane();
        scrollPanel.setViewportView(newCandidatePanel);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder());
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scrollPanel, BorderLayout.CENTER);
        pack();    
    }
    
    private void initializeListeners() {
        saveButton.addActionListener(e -> {
            buildCandidate(candidate);
            setSaved(true);
            setVisible(false);
        });

        saveButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        saveButton.getActionMap().put("enter", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buildCandidate(candidate);
                setSaved(true);
                setVisible(false);
            }
        });
        
        saveButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK, false), "down");
        saveButton.getActionMap().put("down", downAction);
        
        saveButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK, false), "up");
        saveButton.getActionMap().put("up", upAction);
        
        
        additionalBenefitsButton.addActionListener(e -> {
            AdditionalBenefitsDialogue dialogue = new AdditionalBenefitsDialogue(parent, true);
            dialogue.initialize(candidate.getAdditionalBenefits() ,labelService);
            dialogue.setDialogueVisible();
            dialogue.dispose();
        });
        
        additionalBenefitsButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK, false), "down");
        additionalBenefitsButton.getActionMap().put("down", downAction);
        
        additionalBenefitsButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK, false), "up");
        additionalBenefitsButton.getActionMap().put("up", upAction);
        
        additionalBenefitsButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        additionalBenefitsButton.getActionMap().put("enter", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AdditionalBenefitsDialogue dialogue = new AdditionalBenefitsDialogue(parent, true);
                dialogue.initialize(candidate.getAdditionalBenefits(), labelService);
                dialogue.setDialogueVisible();
                resultLabels = dialogue.getCurrentPickedLabels();
                picked = resultLabels;
                dialogue.dispose();
            }
        });

        positionBox.addItemListener(new ItemListener(){
            PositionEntity position;
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem() != "" && e.getStateChange() == ItemEvent.SELECTED){
                    position = getSelectedPosition((String)e.getItem());
                    countryBox.setSelectedItem(Utils.getStringFromCountry(position.getCountry()));
                    profileBox.setSelectedItem(Utils.getStringFromProfile(position.getProfile()));
                }
                else if(e.getItem() == ""){
                    countryBox.setSelectedItem("");
                    profileBox.setSelectedItem("");
                }
            }
        });
        
        currentSalaryConverter.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                converterDialogue = new ConverterDialogue(getParent(), true);
                converterDialogue.setDialogueVisible();
                if(converterDialogue.isSaved()){
                    currentSalaryField.setText(String.valueOf(converterDialogue.getGbpAmount()));
                }
                converterDialogue.dispose();
            }            
        });
        
        
        salaryExpectationsConverter.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                converterDialogue = new ConverterDialogue(getParent(), true);
                converterDialogue.setDialogueVisible();
                if(converterDialogue.isSaved()){
                    salaryExpectationsField.setText(String.valueOf(converterDialogue.getGbpAmount()));
                }
                converterDialogue.dispose();
            }            
        });

        addKeyBindingsToField(firstNameField);
        addKeyBindingsToField(lastNameField);
        addKeyBindingsToField(phoneNumberField);
        addKeyBindingsToField(emailField);
        addKeyBindingsToField(cvField);
        addKeyBindingsToField(currentSalaryField);
        addKeyBindingsToField(salaryExpectationsField);
        addKeyBindingsToField(countryBox.getCustomEditor().getCustomTextField());
        addKeyBindingsToField(profileBox.getCustomEditor().getCustomTextField());
        addKeyBindingsToField(sourceBox.getCustomEditor().getCustomTextField());
        addKeyBindingsToField(positionBox.getCustomEditor().getCustomTextField());
    }
    
    private void buildCandidate (CandidateEntity candidate){
        
            long currentSalary = currentSalaryField.getText().isEmpty() ? 0 : Long.parseLong(currentSalaryField.getText());
            long salaryExpectations = salaryExpectationsField.getText().isEmpty() ? 0 : Long.parseLong(salaryExpectationsField.getText());
            PositionEntity position = getSelectedPosition(positionBox.getSelectedItemString());
           
            candidate.setFirstName(firstNameField.getText());
            candidate.setLastName(lastNameField.getText());
            candidate.setPhoneNumber(phoneNumberField.getText());
            candidate.setEmail(emailField.getText());
            candidate.setProfile(Utils.getProfileFromString((String)profileBox.getSelectedItem()));
            candidate.setCountry(Utils.getCountryFromString((String) countryBox.getSelectedItem()));
            candidate.setLinkToCV(cvField.getText());
            candidate.setSource(Utils.getSourceFromString((String) sourceBox.getSelectedItem()));
            candidate.setCurrentSalary(currentSalary);
            candidate.setSalaryExpectations(salaryExpectations);
            candidate.setPosition(position);            
            candidate.setInMail(LocalDate.now());
            candidate.setPhoneScreening(null);
            candidate.setHRInterview(null);
            candidate.setSubmittedToHM(null);
            candidate.setHMInterview(null);
            candidate.setTest(null);
            candidate.setSuitableForSinclair(CandidateEntity.FALSE);
            candidate.setOfferLetter(null);
            candidate.setSelectionClosed(null);
            candidate.setResult(Result.NONE);
            candidate.setAdditionalComments(additionalInformationField.getText());
    }

    private void addKeyBindingsToField(JTextComponent field){

        field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        field.getActionMap().put("enter", downAction);

        field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK, false), "up");
        field.getActionMap().put("up", upAction);

        field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK, false), "down");
        field.getActionMap().put("down", downAction);
    }
    
    private PositionEntity getSelectedPosition(String positionName) {
        if(positionBox.getSelectedItem()!= null) {
            for(PositionEntity p: positionService.findAll()){
                if(p.getName().equals(positionName)){
                    return p;
                }
            }
        }
        return null;
    }
}