package view.components.panels.additionalBenefits;

import controller.Context;
import entity.LabelEntity;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import view.components.frames.CustomErrorDialogue;


@Getter
@Setter
public class BenefitItem extends JPanel{

    private String currentName;
    private FacePanel face;
    private LabelEntity label;
    private JTextField updateField;
    private boolean isFocused = false;
    
    
    public BenefitItem(LabelEntity label) {
        initComponents();
        init(label);
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#e8e7e8")));
        setAlignmentX(0);
        setOpaque(false);
    }

    public void activateEdit(String currentName){
        remove(face);
        add(updateField);
        updateField.setText(currentName);
        updateField.requestFocusInWindow();
    }
    
    public LabelEntity deactivateEdit(ItemArea box1, ItemArea box2){
        boolean isPresent;

        remove(updateField);
        if(updateField.getText().isEmpty()) {
            new CustomErrorDialogue(false, "Label's name can't be empty", Context.getParentFrame(), true).showDialogue();
        }
        else {
            if (!updateField.getText().equals(face.getNameLabel().getText())) {
                face.getNameLabel().setText("");
                isPresent = checkIfItemPresent(box1);
                if (!isPresent) {
                    isPresent = checkIfItemPresent(box2);
                }
                if (isPresent) {
                    face.getNameLabel().setText(currentName);
                    new CustomErrorDialogue(false, "This label already exists", Context.getParentFrame(), true).showDialogue();
                }
                else {
                    face.getNameLabel().setText(updateField.getText());
                    label.setName(updateField.getText());
                    face.getEditLabel().setVisible(true);
                    face.getDeleteLabel().setVisible(true);
                }
            }
        }
        add(face);
        return label;
    }

    private void init(LabelEntity label) {
        face = new FacePanel();
        this.label = label;
        face.getNameLabel().setText(label.getName());
        currentName = label.getName();
        updateField = new JTextField();
        updateField.setPreferredSize(new Dimension(322, 50));
        updateField.setMinimumSize(new Dimension(322, 50));
        updateField.setMaximumSize(new Dimension(322, 50));
        updateField.setSize(new Dimension(322, 50));
        this.add(face, BorderLayout.CENTER);
    }

    private boolean checkIfItemPresent (ItemArea box){
        for (Component c : box.getComponents()) {
            if (c instanceof BenefitItem item) {
                if (item.getFace().getNameLabel().getText().equals(updateField.getText())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void initComponents() {

        setAlignmentX(0.0F);
        setMaximumSize(new java.awt.Dimension(333, 50));
        setMinimumSize(new java.awt.Dimension(333, 50));
        setName("");
        setPreferredSize(new java.awt.Dimension(333, 50));
        setLayout(new java.awt.BorderLayout());
        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        if(isFocused){
            g2d.setPaint(new GradientPaint(0, 0, Color.decode("#fcf1fc"),0, getHeight(), Color.decode("#ffdfff")));
        }
        else{            
            g2d.setPaint(new GradientPaint(0, 0, Color.decode("#ffffff"),0, getHeight(), Color.decode("#fff4ff")));
        }
        g2d.fillRect(0, 0, getWidth()-1, getHeight()-1);
       
        super.paintComponent(g);
    }
}