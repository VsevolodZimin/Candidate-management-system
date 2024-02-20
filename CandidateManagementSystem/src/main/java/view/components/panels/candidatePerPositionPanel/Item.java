
package view.components.panels.candidatePerPositionPanel;

import javax.swing.*;

public class Item extends JPanel {

    private JLabel currentSalary;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel salaryExpectations;

    public Item() {
        initComponents();
    }
    public Item(String firstName, String lastName, long currentSalary, long salaryExpectations) {
        initComponents();
        initCustomComponents(firstName, lastName, currentSalary, salaryExpectations);
    }

    private void initComponents() {

        lastName = new JLabel();
        firstName = new JLabel();
        salaryExpectations = new JLabel();
        currentSalary = new JLabel();

        lastName.setText("Last name");

        firstName.setText("First name");

        salaryExpectations.setText("Salary expectations");

        currentSalary.setText("Current salary");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(firstName, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lastName, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(currentSalary, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(salaryExpectations, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lastName)
                    .addComponent(firstName)
                    .addComponent(salaryExpectations)
                    .addComponent(currentSalary))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }



    private void initCustomComponents(String firstName, String lastName, long currentSalary, long salaryExpectations) {
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.currentSalary.setText(String.valueOf(currentSalary));
        this.salaryExpectations.setText(String.valueOf(salaryExpectations));    
    }
}