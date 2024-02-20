package view.components.panels.selectionProcessPanel;

import lombok.Getter;
import lombok.Setter;
import util.enums.BoxType;
import util.enums.Stage;
import view.components.comboBox.CustomComboBoxModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


@Getter
@Setter
public class SelectionProcessPanel extends JPanel {
    
    public SelectionProcessPanel() {
        initComponents();
        setOpaque(false);
        suitableCheckBox.setBackground(Color.decode("#f7eef7"));
        agencyCheckBox.setBackground(Color.decode("#f7eef7"));
    }

    
    private void initComponents() {

        hrInterview = new JLabel();
        submittedTag = new JLabel();
        testTag = new JLabel();
        offerLetterTag = new JLabel();
        selectionClosedTag = new JLabel();
        suitableCheckBox = new JCheckBox();
        resultTag = new JLabel();
        hmInterviewTag = new JLabel();
        saveButton = new JButton();
        inMailTag = new JLabel();
        agencyCheckBox = new JCheckBox();
        screeningTag = new JLabel();
        inMailButton = new view.components.panels.selectionProcessPanel.NowButton();
        phoneScreeningButton = new view.components.panels.selectionProcessPanel.NowButton();
        testButton = new view.components.panels.selectionProcessPanel.NowButton();
        hmInterviewButton = new view.components.panels.selectionProcessPanel.NowButton();
        hrInterviewButton = new view.components.panels.selectionProcessPanel.NowButton();
        submittedButton = new view.components.panels.selectionProcessPanel.NowButton();
        selectionClosedButton = new view.components.panels.selectionProcessPanel.NowButton();
        offerLetterButton = new view.components.panels.selectionProcessPanel.NowButton();
        resultComboBox = new view.components.comboBox.CustomComboBox(new CustomComboBoxModel(BoxType.RESULT));
        inMailField = new view.components.panels.DateField(Stage.IN_MAIL);
        phoneScreeningField = new view.components.panels.DateField(Stage.PHONE_SCREENING);
        hrInterviewField = new view.components.panels.DateField(Stage.HR_INTERVIEW);
        submittedField = new view.components.panels.DateField(Stage.SUBMITTED_TO_HM);
        hmInterviewField = new view.components.panels.DateField(Stage.HM_INTERVIEW);
        testField = new view.components.panels.DateField(Stage.TEST);
        offerLetterField = new view.components.panels.DateField(Stage.OFFER_LETTER);
        selectionClosedField = new view.components.panels.DateField(Stage.SELECTION_CLOSED);

        hrInterview.setText("HR Interview");

        submittedTag.setText("Submitted to HM");

        testTag.setText("Test");

        offerLetterTag.setText("Offer Letter");

        selectionClosedTag.setText("Selection Closed");

        suitableCheckBox.setText("Suitable for hire");
        suitableCheckBox.setFocusPainted(false);

        resultTag.setText("Result");

        hmInterviewTag.setText("HM Interview");

        saveButton.setText("Save");
        saveButton.setFocusPainted(false);

        inMailTag.setText("In-Mail");

        agencyCheckBox.setText("Agency");
        agencyCheckBox.setFocusPainted(false);

        screeningTag.setText("Phone Screening");

        inMailButton.setFocusPainted(false);

        phoneScreeningButton.setFocusPainted(false);

        testButton.setFocusPainted(false);

        hmInterviewButton.setFocusPainted(false);

        hrInterviewButton.setFocusPainted(false);

        submittedButton.setFocusPainted(false);

        selectionClosedButton.setFocusPainted(false);

        offerLetterButton.setFocusPainted(false);

        resultComboBox.setMinimumSize(new Dimension(190, 32));
        resultComboBox.setPreferredSize(new Dimension(360, 32));

        inMailField.setText("dateField1");
        inMailField.setMinimumSize(new Dimension(190, 32));
        inMailField.setPreferredSize(new Dimension(360, 32));

        phoneScreeningField.setText("dateField1");
        phoneScreeningField.setMinimumSize(new Dimension(190, 32));
        phoneScreeningField.setPreferredSize(new Dimension(360, 32));

        hrInterviewField.setText("dateField1");
        hrInterviewField.setMinimumSize(new Dimension(190, 32));
        hrInterviewField.setPreferredSize(new Dimension(360, 32));

        submittedField.setText("dateField1");
        submittedField.setMinimumSize(new Dimension(190, 32));
        submittedField.setPreferredSize(new Dimension(360, 32));

        hmInterviewField.setText("dateField1");
        hmInterviewField.setMinimumSize(new Dimension(190, 32));
        hmInterviewField.setPreferredSize(new Dimension(360, 32));

        testField.setMinimumSize(new Dimension(190, 32));
        testField.setPreferredSize(new Dimension(360, 32));

        offerLetterField.setText("dateField1");
        offerLetterField.setMinimumSize(new Dimension(190, 32));
        offerLetterField.setPreferredSize(new Dimension(360, 32));

        selectionClosedField.setText("dateField1");
        selectionClosedField.setMinimumSize(new Dimension(190, 32));
        selectionClosedField.setPreferredSize(new Dimension(360, 32));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(screeningTag)
                            .addComponent(hrInterview)
                            .addComponent(inMailTag)
                            .addComponent(hmInterviewTag)
                            .addComponent(submittedTag)
                            .addComponent(testTag)
                            .addComponent(offerLetterTag)
                            .addComponent(selectionClosedTag))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(inMailField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(phoneScreeningField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(hrInterviewField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(submittedField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(hmInterviewField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(testField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(offerLetterField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(selectionClosedField, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(phoneScreeningButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(inMailButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(testButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hmInterviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hrInterviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(submittedButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectionClosedButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(offerLetterButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultTag)
                        .addGap(30, 30, 30)
                        .addComponent(resultComboBox, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(suitableCheckBox)
                .addGap(18, 18, 18)
                .addComponent(agencyCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(inMailTag)
                        .addComponent(inMailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inMailButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(screeningTag)
                            .addComponent(phoneScreeningButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneScreeningField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(hrInterview)
                            .addComponent(hrInterviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hrInterviewField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(submittedTag)
                            .addComponent(submittedButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(submittedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(hmInterviewTag)
                            .addComponent(hmInterviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hmInterviewField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(testTag)
                            .addComponent(testButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(testField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(offerLetterTag)
                            .addComponent(offerLetterButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(offerLetterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectionClosedTag)
                    .addComponent(selectionClosedButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectionClosedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(resultTag)
                    .addComponent(resultComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(suitableCheckBox)
                    .addComponent(agencyCheckBox))
                .addGap(18, 18, 18))
        );
    }

    
        @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
    

    private JCheckBox agencyCheckBox;
    private view.components.panels.selectionProcessPanel.NowButton hmInterviewButton;
    private view.components.panels.DateField hmInterviewField;
    private JLabel hmInterviewTag;
    private JLabel hrInterview;
    private view.components.panels.selectionProcessPanel.NowButton hrInterviewButton;
    private view.components.panels.DateField hrInterviewField;
    private view.components.panels.selectionProcessPanel.NowButton inMailButton;
    private view.components.panels.DateField inMailField;
    private JLabel inMailTag;
    private view.components.panels.selectionProcessPanel.NowButton offerLetterButton;
    private view.components.panels.DateField offerLetterField;
    private JLabel offerLetterTag;
    private view.components.panels.selectionProcessPanel.NowButton phoneScreeningButton;
    private view.components.panels.DateField phoneScreeningField;
    private view.components.comboBox.CustomComboBox resultComboBox;
    private JLabel resultTag;
    private JButton saveButton;
    private JLabel screeningTag;
    private view.components.panels.selectionProcessPanel.NowButton selectionClosedButton;
    private view.components.panels.DateField selectionClosedField;
    private JLabel selectionClosedTag;
    private view.components.panels.selectionProcessPanel.NowButton submittedButton;
    private view.components.panels.DateField submittedField;
    private JLabel submittedTag;
    private JCheckBox suitableCheckBox;
    private view.components.panels.selectionProcessPanel.NowButton testButton;
    private view.components.panels.DateField testField;
    private JLabel testTag;
}