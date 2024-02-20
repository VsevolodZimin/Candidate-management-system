package view.components.panels.mainPanel.candidatePanel;

import java.awt.Color;
import javax.swing.*;
import lombok.Getter;

@Getter
public class StageButtonPanel extends JPanel {

    private JToggleButton agencyButton;
    private JToggleButton allButton;
    private ButtonGroup buttonGroup1;
    private JToggleButton fitForHireButton;
    private JToggleButton hmInterview;
    private JToggleButton hrInterview;
    private JToggleButton offerLetter;
    private JToggleButton phoneScreening;
    private JToggleButton selectionClosed;
    private JToggleButton submittedToHM;
    private JToggleButton test;

    public StageButtonPanel() {
        setBackground(Color.decode("#f7eef7"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb"), 1));
        initComponents();
    }

    private void initComponents() {
        buttonGroup1 = new ButtonGroup();
        fitForHireButton = new JToggleButton();
        agencyButton = new JToggleButton();
        allButton = new JToggleButton();
        phoneScreening = new JToggleButton();
        hrInterview = new JToggleButton();
        submittedToHM = new JToggleButton();
        hmInterview = new JToggleButton();
        test = new JToggleButton();
        offerLetter = new JToggleButton();
        selectionClosed = new JToggleButton();

        fitForHireButton.setText("Fit for hire");
        fitForHireButton.setFocusPainted(false);

        agencyButton.setText("Agency");
        agencyButton.setRequestFocusEnabled(false);

        buttonGroup1.add(allButton);
        allButton.setText("All");
        allButton.setFocusPainted(false);

        buttonGroup1.add(phoneScreening);
        phoneScreening.setText("Phone Screening");
        phoneScreening.setFocusPainted(false);

        buttonGroup1.add(hrInterview);
        hrInterview.setText("HR Intrview");
        hrInterview.setFocusPainted(false);

        buttonGroup1.add(submittedToHM);
        submittedToHM.setText("Submitted to HM");
        submittedToHM.setFocusPainted(false);

        buttonGroup1.add(hmInterview);
        hmInterview.setText("HM Interview");
        hmInterview.setFocusPainted(false);

        buttonGroup1.add(test);
        test.setText("Test");
        test.setFocusPainted(false);

        buttonGroup1.add(offerLetter);
        offerLetter.setText("Offer Letter");
        offerLetter.setFocusPainted(false);

        buttonGroup1.add(selectionClosed);
        selectionClosed.setText("Selection Closed");
        selectionClosed.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(allButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(phoneScreening)
                .addGap(12, 12, 12)
                .addComponent(hrInterview, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(submittedToHM)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hmInterview)
                .addGap(12, 12, 12)
                .addComponent(test, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(offerLetter)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectionClosed)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(fitForHireButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(agencyButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(allButton)
                        .addComponent(phoneScreening)
                        .addComponent(hrInterview)
                        .addComponent(submittedToHM)
                        .addComponent(hmInterview)
                        .addComponent(test)
                        .addComponent(offerLetter)
                        .addComponent(selectionClosed))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fitForHireButton)
                        .addComponent(agencyButton)))
                .addGap(14, 14, 14))
        );
    }
}