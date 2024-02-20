package view.components.frames;

import controller.Context;
import entity.PositionEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.Getter;
import javax.swing.JTextArea;
import sevices.JDBCPositionService;
import view.components.comboBox.CustomComboBox;
import view.RoundedNumericTextField;
import view.components.panels.newPositionPanel.NewPositionPanel;

@Getter
public class NewPositionDialog extends javax.swing.JDialog {
    
    private NewPositionPanel newPositionPanel;
    private PositionEntity position;
    private CustomComboBox countryField;
    private CustomComboBox profileField;
    private JTextField nameField; 
    private RoundedNumericTextField budgetField;
    private JTextArea additionalInfo;
    private JButton saveButton;
    private JDBCPositionService service;
    private JLabel errorTag; 
    
    
    public NewPositionDialog(boolean modal) {
        super(Context.getParentFrame(), modal);
        this.service = Context.getPositionService();

        initComponents();
        initialize(service);
        addListeners();
        setVisible(true);
    }
    
    
    private void initComponents() {
        newPositionPanel = new NewPositionPanel();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(newPositionPanel);
        pack();
    }
    
    private void initialize(JDBCPositionService service){
        nameField = newPositionPanel.getNameField();
        countryField = newPositionPanel.getCountryField();
        profileField = newPositionPanel.getProfileField();
        budgetField = newPositionPanel.getBudgetField();
        additionalInfo = newPositionPanel.getAdditionalInfo().getTextArea();
        errorTag = newPositionPanel.getErrorTag();
        budgetField.setText("0");
        saveButton = newPositionPanel.getSaveButton();
        this.service = service;
    }
    
    private void addListeners(){
        
        saveButton.addActionListener(new ActionListener(){
            boolean exists = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                 for(PositionEntity position : service.findAll()){
                     if(position.getName().equalsIgnoreCase(nameField.getText())){
                         exists = true;
                     }
                 }
                 if(!exists) {
                     position = new PositionEntity();
                     position.setName(nameField.getText());
                     position.setCountry(util.Utils.getCountryFromString((String)countryField.getSelectedItem()));
                     position.setProfile(util.Utils.getProfileFromString((String)profileField.getSelectedItem()));
                     position.setOpenDate(LocalDate.now());
                     position.setBudget(Integer.parseInt(budgetField.getText()));
                     position.setAdditionalInformation(additionalInfo.getText());
                     setVisible(false);
                 }
                 else{
                     errorTag.setForeground(Color.red);
                 }
                 exists = false;
            }
        });
        this.dispose();
    }
}