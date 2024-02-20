package view.components.panels.newCandidatePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import lombok.Getter;
import lombok.Setter;
import sevices.JDBCPositionService;
import util.connection.PathsPropertiesManager;
import util.enums.BoxType;
import view.RoundedNumericTextField;
import view.RoundedTextArea;
import view.RoundedTextField;
import view.components.comboBox.CustomComboBox;
import view.components.comboBox.CustomComboBoxModel;
import view.components.comboBox.PositionComboBox;

import javax.swing.*;

@Getter
@Setter
public class NewCandidatePanel extends JPanel {

    private JDBCPositionService positionService;
    private JButton additionalBenefitsButton;
    private JLabel additionalBenefitsLabel;
    private RoundedTextArea additionalInformationField;
    private JLabel additionalInformationLabel;
    private CustomComboBox countryField;
    private JLabel countryLabel;
    private JLabel currentSalaryConverter;
    private RoundedNumericTextField currentSalaryField;
    private JLabel currentSalaryLabel;
    private RoundedTextField cvField;
    private JLabel cvLabel;
    private RoundedTextField emailField;
    private JLabel emailLabel;
    private RoundedTextField firstNameField;
    private JLabel firstNameLabel;
    private RoundedTextField lastNameField;
    private JLabel lastNameLabel;
    private RoundedTextField phoneNumberField;
    private JLabel phoneNumberLabel;
    private PositionComboBox positionField;
    private JLabel positionLabel;
    private CustomComboBox profileField;
    private JLabel profileLabel;
    private JLabel salaryExpectationConverter;
    private JLabel salaryExpectationLabel;
    private RoundedNumericTextField salaryExpectationsField;
    private JButton saveButton;
    private CustomComboBox sourceField;
    private JLabel sourceLabel;
    
    public NewCandidatePanel(JDBCPositionService positionService) {
        setOpaque(false);
        this.positionService = positionService;
        initComponents();
        positionField.setSelectedItem("");
    }

    private void initComponents() {
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        phoneNumberLabel = new JLabel();
        emailLabel = new JLabel();
        countryLabel = new JLabel();
        profileLabel = new JLabel();
        cvLabel = new JLabel();
        sourceLabel = new JLabel();
        currentSalaryLabel = new JLabel();
        salaryExpectationLabel = new JLabel();
        additionalBenefitsLabel = new JLabel();
        additionalBenefitsButton = new JButton();
        additionalInformationLabel = new JLabel();
        saveButton = new JButton();
        positionLabel = new JLabel();
        sourceField = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.SOURCE));
        countryField = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.COUNTRY));
        profileField = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.PROFILE));
        lastNameField = new view.RoundedTextField();
        firstNameField = new view.RoundedTextField();
        phoneNumberField = new view.RoundedTextField();
        emailField = new view.RoundedTextField();
        cvField = new view.RoundedTextField();
        positionField = new view.components.comboBox.PositionComboBox(positionService);
        currentSalaryField = new view.RoundedNumericTextField();
        salaryExpectationsField = new view.RoundedNumericTextField();
        additionalInformationField = new view.RoundedTextArea();
        currentSalaryConverter = new JLabel();
        salaryExpectationConverter = new JLabel();

        firstNameLabel.setText("First Name");
        firstNameLabel.setMaximumSize(new Dimension(156, 24));
        firstNameLabel.setMinimumSize(new Dimension(156, 24));
        firstNameLabel.setPreferredSize(new Dimension(156, 24));

        lastNameLabel.setText("Last Name");
        lastNameLabel.setMaximumSize(new Dimension(156, 24));
        lastNameLabel.setMinimumSize(new Dimension(156, 24));
        lastNameLabel.setPreferredSize(new Dimension(156, 24));

        phoneNumberLabel.setText("Phone Number");
        phoneNumberLabel.setMaximumSize(new Dimension(156, 24));
        phoneNumberLabel.setMinimumSize(new Dimension(156, 24));
        phoneNumberLabel.setPreferredSize(new Dimension(156, 24));

        emailLabel.setText("E-mail");
        emailLabel.setMaximumSize(new Dimension(156, 24));
        emailLabel.setMinimumSize(new Dimension(156, 24));
        emailLabel.setPreferredSize(new Dimension(156, 24));

        countryLabel.setText("Country");
        countryLabel.setMaximumSize(new Dimension(156, 24));
        countryLabel.setMinimumSize(new Dimension(156, 24));
        countryLabel.setPreferredSize(new Dimension(156, 24));

        profileLabel.setText("Profile");
        profileLabel.setMaximumSize(new Dimension(156, 24));
        profileLabel.setMinimumSize(new Dimension(156, 24));
        profileLabel.setPreferredSize(new Dimension(156, 24));

        cvLabel.setText("Link to CV");
        cvLabel.setMaximumSize(new Dimension(156, 24));
        cvLabel.setMinimumSize(new Dimension(156, 24));
        cvLabel.setPreferredSize(new Dimension(156, 24));

        sourceLabel.setText("Source");
        sourceLabel.setMaximumSize(new Dimension(156, 24));
        sourceLabel.setMinimumSize(new Dimension(156, 24));
        sourceLabel.setPreferredSize(new Dimension(156, 24));

        currentSalaryLabel.setText("Current Salary");
        currentSalaryLabel.setMaximumSize(new Dimension(156, 24));
        currentSalaryLabel.setMinimumSize(new Dimension(156, 24));
        currentSalaryLabel.setPreferredSize(new Dimension(156, 24));

        salaryExpectationLabel.setText("Salary Expectations");
        salaryExpectationLabel.setMaximumSize(new Dimension(156, 24));
        salaryExpectationLabel.setMinimumSize(new Dimension(156, 24));
        salaryExpectationLabel.setPreferredSize(new Dimension(156, 24));

        additionalBenefitsLabel.setText("Additional benefits");
        additionalBenefitsLabel.setMaximumSize(new Dimension(156, 24));
        additionalBenefitsLabel.setMinimumSize(new Dimension(156, 24));
        additionalBenefitsLabel.setPreferredSize(new Dimension(156, 24));

        additionalBenefitsButton.setText("Add");
        additionalBenefitsButton.setFocusPainted(false);

        additionalInformationLabel.setText("Notes");
        additionalInformationLabel.setMaximumSize(new Dimension(156, 24));
        additionalInformationLabel.setMinimumSize(new Dimension(156, 24));
        additionalInformationLabel.setPreferredSize(new Dimension(156, 24));

        saveButton.setText("Save");
        saveButton.setFocusPainted(false);

        positionLabel.setText("Position");
        positionLabel.setMaximumSize(new Dimension(156, 24));
        positionLabel.setMinimumSize(new Dimension(156, 24));
        positionLabel.setPreferredSize(new Dimension(156, 24));

        sourceField.setMaximumSize(new Dimension(360, 32));
        sourceField.setMinimumSize(new Dimension(190, 32));
        sourceField.setPreferredSize(new Dimension(360, 32));

        countryField.setMaximumSize(new Dimension(360, 32));
        countryField.setMinimumSize(new Dimension(190, 32));
        countryField.setPreferredSize(new Dimension(360, 32));

        profileField.setMaximumSize(new Dimension(360, 32));
        profileField.setMinimumSize(new Dimension(190, 32));
        profileField.setPreferredSize(new Dimension(360, 32));

        lastNameField.setMaximumSize(new Dimension(360, 32));
        lastNameField.setMinimumSize(new Dimension(190, 32));
        lastNameField.setPreferredSize(new Dimension(360, 32));

        firstNameField.setMaximumSize(new Dimension(360, 32));
        firstNameField.setMinimumSize(new Dimension(190, 32));
        firstNameField.setPreferredSize(new Dimension(360, 32));

        phoneNumberField.setMaximumSize(new Dimension(360, 32));
        phoneNumberField.setMinimumSize(new Dimension(190, 32));
        phoneNumberField.setName("");
        phoneNumberField.setPreferredSize(new Dimension(360, 32));

        emailField.setMaximumSize(new Dimension(360, 32));
        emailField.setMinimumSize(new Dimension(190, 32));
        emailField.setPreferredSize(new Dimension(360, 32));

        cvField.setMaximumSize(new Dimension(360, 32));
        cvField.setMinimumSize(new Dimension(190, 32));
        cvField.setPreferredSize(new Dimension(360, 32));

        positionField.setMaximumSize(new Dimension(360, 32));
        positionField.setMinimumSize(new Dimension(190, 32));
        positionField.setPreferredSize(new Dimension(360, 32));

        currentSalaryField.setText("numericTextField1");
        currentSalaryField.setMaximumSize(new Dimension(360, 32));
        currentSalaryField.setMinimumSize(new Dimension(190, 32));
        currentSalaryField.setPreferredSize(new Dimension(360, 32));

        salaryExpectationsField.setText("numericTextField1");
        salaryExpectationsField.setMaximumSize(new Dimension(360, 32));
        salaryExpectationsField.setMinimumSize(new Dimension(190, 32));
        salaryExpectationsField.setPreferredSize(new Dimension(360, 32));

        additionalInformationField.setMaximumSize(new Dimension(190, 32));
        additionalInformationField.setMinimumSize(new Dimension(190, 32));
        additionalInformationField.setPreferredSize(new Dimension(100, 100));

        currentSalaryConverter.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "currencyConversion" + ".png"));
        salaryExpectationConverter.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "currencyConversion" + ".png"));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(salaryExpectationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(salaryExpectationsField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(currentSalaryLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(currentSalaryField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cvLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cvField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(phoneNumberLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(phoneNumberField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sourceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sourceField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(profileLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(profileField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(countryLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(countryField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(positionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(positionField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(firstNameField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                    .addComponent(lastNameField, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(additionalInformationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(additionalInformationField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(additionalBenefitsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(additionalBenefitsButton))
                            .addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(currentSalaryConverter)
                            .addComponent(salaryExpectationConverter))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(positionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(positionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(countryLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(countryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(profileLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(profileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(sourceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(sourceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(phoneNumberLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cvLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(cvField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(currentSalaryLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(currentSalaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentSalaryConverter, GroupLayout.Alignment.TRAILING))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(salaryExpectationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(salaryExpectationsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(salaryExpectationConverter)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(additionalBenefitsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(additionalBenefitsButton))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(additionalInformationField, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(additionalInformationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(saveButton)
                .addGap(16, 16, 16))
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}