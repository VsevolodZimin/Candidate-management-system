package view.components.panels.mainPanel.candidatePanel;

import java.awt.Color;
import lombok.Getter;
import javax.swing.*;

@Getter
public class ResultButtonPanel extends JPanel {

    public ResultButtonPanel() {
        setBackground(Color.decode("#f7eef7"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb"), 1));
        initComponents();
    }
    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        fitForHireButton = new JToggleButton();
        agencyButton = new JToggleButton();
        allButton = new JToggleButton();
        offerDeclinedButton = new JToggleButton();
        hrDiscardsButton = new JToggleButton();
        hmDiscardsButton = new JToggleButton();
        hiredButton = new JToggleButton();

        fitForHireButton.setText("Fit for hire");
        fitForHireButton.setFocusPainted(false);

        agencyButton.setText("Agency");
        agencyButton.setFocusPainted(false);

        buttonGroup1.add(allButton);
        allButton.setText("All");
        allButton.setFocusPainted(false);

        buttonGroup1.add(offerDeclinedButton);
        offerDeclinedButton.setText("Offer Declined");
        offerDeclinedButton.setFocusPainted(false);

        buttonGroup1.add(hrDiscardsButton);
        hrDiscardsButton.setText("HR Discards");
        hrDiscardsButton.setFocusPainted(false);

        buttonGroup1.add(hmDiscardsButton);
        hmDiscardsButton.setText("HM Discards");
        hmDiscardsButton.setFocusPainted(false);

        buttonGroup1.add(hiredButton);
        hiredButton.setText("Hired");
        hiredButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(allButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(offerDeclinedButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hrDiscardsButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hmDiscardsButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hiredButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
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
                        .addComponent(offerDeclinedButton)
                        .addComponent(hrDiscardsButton)
                        .addComponent(hmDiscardsButton)
                        .addComponent(hiredButton))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fitForHireButton)
                        .addComponent(agencyButton)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }
    private JToggleButton agencyButton;
    private JToggleButton allButton;
    private ButtonGroup buttonGroup1;
    private JToggleButton fitForHireButton;
    private JToggleButton hiredButton;
    private JToggleButton hmDiscardsButton;
    private JToggleButton hrDiscardsButton;
    private JToggleButton offerDeclinedButton;
}