package view.components.frames;

import controller.Context;
import entity.CandidateEntity;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import lombok.Getter;
import util.Utils;
import util.enums.Result;
import util.enums.Stage;
import static util.enums.Stage.IN_MAIL;
import view.components.comboBox.CustomComboBox;
import view.components.panels.DateField;
import view.components.panels.selectionProcessPanel.NowButton;
import view.components.panels.selectionProcessPanel.SelectionProcessPanel;

@Getter
public class SelectionProcessDialog extends javax.swing.JDialog {
    
    private SelectionProcessPanel selectionProcessPanel;

    
    private final SortedMap<Stage, DateField> mapOfFields = new TreeMap<>();
    private final SortedMap<Stage, LocalDate> mapOfDates = new TreeMap<>();       
    
    private NowButton inMailButton;
    private NowButton phoneScreeningButton;
    private NowButton hrInterviewButton;
    private NowButton submittedButton;
    private NowButton hmInterviewButton;
    private NowButton testButton;
    private NowButton offerLetterButton;
    private NowButton selectionClosedButton;

    private DateField inMailField;
    private DateField phoneScreeningField;
    private DateField hrInterviewField;
    private DateField submittedField;
    private DateField hmInterviewField;
    private DateField testField;
    private DateField offerLetterField;
    private DateField selectionClosedField;

    private CustomComboBox resultComboBox;
    private JCheckBox suitableCheckBox;
    private JCheckBox agencyCheckBox;
    private JButton saveButton;
    @Getter
    private final CandidateEntity candidate;
   
    public SelectionProcessDialog(boolean modal, CandidateEntity candidate) {
        super(Context.getParentFrame(), modal);
        this.candidate = candidate;
        initComponents();
        pack();
    }

    
        private void initComponents() {

        selectionProcessPanel = new view.components.panels.selectionProcessPanel.SelectionProcessPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(selectionProcessPanel, java.awt.BorderLayout.CENTER);


        pack();
    }
    
    public void initialize(){
        inMailField = selectionProcessPanel.getInMailField();
        phoneScreeningField = selectionProcessPanel.getPhoneScreeningField();
        hrInterviewField = selectionProcessPanel.getHrInterviewField();
        submittedField = selectionProcessPanel.getSubmittedField();
        hmInterviewField = selectionProcessPanel.getHmInterviewField();
        testField = selectionProcessPanel.getTestField();
        offerLetterField = selectionProcessPanel.getOfferLetterField();
        selectionClosedField = selectionProcessPanel.getSelectionClosedField();
                
        initializeField(inMailField, candidate.getInMail());
        initializeField(phoneScreeningField, candidate.getPhoneScreening());
        initializeField(hrInterviewField, candidate.getHRInterview());
        initializeField(submittedField, candidate.getSubmittedToHM());
        initializeField(hmInterviewField, candidate.getHMInterview());
        initializeField(testField, candidate.getTest());
        initializeField(offerLetterField, candidate.getOfferLetter());
        initializeField(selectionClosedField, candidate.getSelectionClosed());
        
        addKeyBindings(inMailField);
        addKeyBindings(phoneScreeningField);
        addKeyBindings(hrInterviewField);
        addKeyBindings(submittedField);
        addKeyBindings(hmInterviewField);
        addKeyBindings(testField);
        addKeyBindings(offerLetterField);
        addKeyBindings(selectionClosedField);
        
        addFieldFocusListener(inMailField);
        addFieldFocusListener(phoneScreeningField);
        addFieldFocusListener(hrInterviewField);
        addFieldFocusListener(submittedField);
        addFieldFocusListener(hmInterviewField);
        addFieldFocusListener(testField);
        addFieldFocusListener(offerLetterField);
        addFieldFocusListener(selectionClosedField);
        
        inMailButton = selectionProcessPanel.getInMailButton();
        phoneScreeningButton = selectionProcessPanel.getPhoneScreeningButton();
        hrInterviewButton = selectionProcessPanel.getHrInterviewButton();
        submittedButton = selectionProcessPanel.getSubmittedButton();
        hmInterviewButton = selectionProcessPanel.getHmInterviewButton();
        testButton = selectionProcessPanel.getTestButton();
        offerLetterButton = selectionProcessPanel.getOfferLetterButton();
        selectionClosedButton = selectionProcessPanel.getSelectionClosedButton();
        
        resultComboBox = selectionProcessPanel.getResultComboBox();
        suitableCheckBox = selectionProcessPanel.getSuitableCheckBox();
        agencyCheckBox = selectionProcessPanel.getAgencyCheckBox();
        saveButton = selectionProcessPanel.getSaveButton();
        
        resultComboBox.setSelectedItem(Utils.getStringFromResult(candidate.getResult()));
        suitableCheckBox.setSelected(candidate.getSuitableForSinclair() == CandidateEntity.TRUE);
        agencyCheckBox.setSelected(candidate.getFromAgency() == CandidateEntity.TRUE);
        
        inMailButton.setField(inMailField);
        phoneScreeningButton.setField(phoneScreeningField);
        hrInterviewButton.setField(hrInterviewField);
        submittedButton.setField(submittedField);
        hmInterviewButton.setField(hmInterviewField);
        testButton.setField(testField);
        offerLetterButton.setField(offerLetterField);
        selectionClosedButton.setField(selectionClosedField);
        
        
        mapOfDates.put(Stage.IN_MAIL, candidate.getInMail());
        mapOfDates.put(Stage.PHONE_SCREENING, candidate.getPhoneScreening());
        mapOfDates.put(Stage.HR_INTERVIEW, candidate.getHRInterview());
        mapOfDates.put(Stage.SUBMITTED_TO_HM, candidate.getSubmittedToHM());
        mapOfDates.put(Stage.HM_INTERVIEW, candidate.getHMInterview());
        mapOfDates.put(Stage.TEST, candidate.getTest());
        mapOfDates.put(Stage.OFFER_LETTER, candidate.getOfferLetter());
        mapOfDates.put(Stage.SELECTION_CLOSED, candidate.getSelectionClosed());
        
        mapOfFields.put(Stage.IN_MAIL, inMailField);
        mapOfFields.put(Stage.PHONE_SCREENING, phoneScreeningField);
        mapOfFields.put(Stage.HR_INTERVIEW, hrInterviewField);
        mapOfFields.put(Stage.SUBMITTED_TO_HM, submittedField);
        mapOfFields.put(Stage.HM_INTERVIEW, hmInterviewField);
        mapOfFields.put(Stage.TEST, testField);
        mapOfFields.put(Stage.OFFER_LETTER, offerLetterField);
        mapOfFields.put(Stage.SELECTION_CLOSED, selectionClosedField);
        setMode();
        addListeners();
    }
    
    private void initializeField(JTextField field, LocalDate date){
        if(date != null) {
            String day = String.valueOf(date.getDayOfMonth());
            String month = String.valueOf(date.getMonthValue());
            String year = String.valueOf(date.getYear());
            day = Utils.addPlaceHolder(day);
            month = Utils.addPlaceHolder(month);
            year = Utils.addPlaceHolder(year);
            field.setText(day + "." +  month + "." + year);
        }
    }
    
    private void addFieldFocusListener (DateField field){
        
        field.addFocusListener(new FocusListener(){
            
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                LocalDate date = Utils.getDateFromString(field.getText());
                if (!Utils.checkDateForValidity(field, date, mapOfDates)) {
                    date = null;
                    field.setText("");
                }
                mapOfDates.put(field.getStage(), date);
                if(!agencyCheckBox.isSelected()) {
                    checkIfEditable();
                }
            }
        });
    }
    
    private void addListeners() {
        saveButton.addActionListener(e -> {
            LocalDate currentDate;
            DateField currentField;
            Stage currentStage;

            for(Entry<Stage, DateField> entry : mapOfFields.entrySet()) {
                currentField = entry.getValue();
                currentStage = entry.getKey();
                currentDate = mapOfDates.get(currentStage);

                if(currentStage == IN_MAIL){        // IN_MAIL is set automatically at the creation of the candidate, so no need to check it;
                     candidate.setInMail(mapOfDates.get(currentStage));
                }
                else if(currentField.isEditable() && currentDate != null) {             // To be passed to the DB the field needs to be both editable and have a value
                    switch(entry.getKey()){
                        case PHONE_SCREENING -> candidate.setPhoneScreening(mapOfDates.get(currentStage));
                        case HR_INTERVIEW -> candidate.setHRInterview(mapOfDates.get(currentStage));
                        case SUBMITTED_TO_HM -> candidate.setSubmittedToHM(mapOfDates.get(currentStage));
                        case HM_INTERVIEW -> candidate.setHMInterview(mapOfDates.get(currentStage));
                        case TEST -> candidate.setTest(mapOfDates.get(currentStage));
                        case OFFER_LETTER -> candidate.setOfferLetter(mapOfDates.get(currentStage));
                        case SELECTION_CLOSED -> candidate.setSelectionClosed(mapOfDates.get(currentStage));
                    }
                }
                else {
                    switch(entry.getKey()){         // Empty but editable or filled in but uneditable are a NO
                        case PHONE_SCREENING -> candidate.setPhoneScreening(null);
                        case HR_INTERVIEW -> candidate.setHRInterview(null);
                        case SUBMITTED_TO_HM -> candidate.setSubmittedToHM(null);
                        case HM_INTERVIEW -> candidate.setHMInterview(null);
                        case TEST -> candidate.setTest(null);
                        case OFFER_LETTER -> candidate.setOfferLetter(null);
                        case SELECTION_CLOSED -> candidate.setSelectionClosed(null);
                    }
                }
            }
            if(suitableCheckBox.isSelected())
                candidate.setSuitableForSinclair(CandidateEntity.TRUE);
            else
                candidate.setSuitableForSinclair(CandidateEntity.FALSE);
            if(agencyCheckBox.isSelected())
                candidate.setFromAgency(CandidateEntity.TRUE);
            else
                candidate.setFromAgency(CandidateEntity.FALSE);

            Result result =  Utils.getResultFromString((String)resultComboBox.getSelectedItem());
            candidate.setResult(result);
            setVisible(false);
        });
        
        selectionProcessPanel.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionProcessPanel.requestFocusInWindow();
            }
        });
        
        agencyCheckBox.addActionListener(e -> setMode());
    }
    
    private void addKeyBindings(DateField field) {
        field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        field.getActionMap().put("enter", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
    }
    
    private void setMode(){
        if(agencyCheckBox.isSelected()){
            setAgencyMode();
        }
        else {
            checkIfEditable();
        }
    }
    
    private void checkIfEditable (){
        
        boolean toDisable = false;
        DateField field;
        LocalDate date;
        Stage stage;
        
        for(Entry entry : mapOfDates.entrySet()) {
            stage = (Stage)entry.getKey();
            date = (LocalDate)entry.getValue();
            field = mapOfFields.get(stage);

            if(!toDisable) {
                if(date == null ){
                    toDisable = true;
                }
                field.setEditable(true);
                field.setBackground(Color.WHITE);

            }
            else {
                field.setEditable(false);      
                field.setBackground(new Color(244,213,244, 50));
            }
        }
        mapOfFields.get(Stage.IN_MAIL).setEditable(false);
        mapOfFields.get(Stage.IN_MAIL).setBackground(new Color(244,213,244, 50));
    }
    
    
    private void setAgencyMode(){
        inMailField.setEditable(false);
        phoneScreeningField.setEditable(false);
        hrInterviewField.setEditable(false);
        submittedField.setEditable(false);
        hmInterviewField.setEditable(false);
        testField.setEditable(false);
        offerLetterField.setEditable(false);
        selectionClosedField.setEditable(true);
    }
    
    public void setDialogueVisible(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}