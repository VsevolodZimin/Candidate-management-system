package view.components.panels.candidateInfoPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import lombok.Getter;
import lombok.Setter;
import view.RoundedTextArea;
import view.RoundedTextField;
import javax.swing.*;

@Getter
@Setter
public class CandidateInfoPanel extends JPanel {

    private RoundedTextField email;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private RoundedTextField linkToCV;
    private RoundedTextArea notes;
    private RoundedTextField phoneNumber;
    private JButton saveButton;
    private JButton showButton;

    public CandidateInfoPanel() {
        initComponents();
        setOpaque(false);
    }
    private void initComponents() {

        jLabel4 = new JLabel();
        saveButton = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        showButton = new JButton();
        jLabel5 = new JLabel();
        email = new RoundedTextField();
        phoneNumber = new RoundedTextField();
        linkToCV = new RoundedTextField();
        notes = new RoundedTextArea();

        jLabel4.setText("Notes:");

        saveButton.setText("Save");
        saveButton.setFocusPainted(false);

        jLabel1.setText("Phone number");

        jLabel2.setText("E-mail");

        jLabel3.setText("Link to CV");

        showButton.setText("Show");
        showButton.setFocusPainted(false);

        jLabel5.setText("Benefits");

        email.setMinimumSize(new Dimension(190, 32));
        email.setPreferredSize(new Dimension(360, 32));

        phoneNumber.setMinimumSize(new Dimension(190, 32));
        phoneNumber.setPreferredSize(new Dimension(360, 32));

        linkToCV.setMinimumSize(new Dimension(190, 32));
        linkToCV.setPreferredSize(new Dimension(360, 32));

        notes.setMinimumSize(new Dimension(190, 32));
        notes.setPreferredSize(new Dimension(360, 32));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(notes, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(showButton)
                                    .addComponent(email, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                    .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(linkToCV, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                        .addGap(51, 51, 51))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(linkToCV, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(showButton))
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addComponent(notes, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(saveButton)
                .addGap(13, 13, 13))
        );
    }
        
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Float(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}