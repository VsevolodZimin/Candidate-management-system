package view.components.frames;

import entity.PositionEntity;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class PositionInfoDialog extends javax.swing.JDialog {

    private final PositionEntity position;
    private JTextArea textArea;
    private JButton saveButton;
    
    
    public PositionInfoDialog(PositionEntity position, Frame parent, boolean modal) {
        super(parent, modal);
        this.position = position;
        initComponents();
        initCustomComponents();
    }

    private void initComponents() {

        positionInfoPanel = new view.components.panels.positionInfoPanel.PositionInfoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(positionInfoPanel, java.awt.BorderLayout.CENTER);

        pack();
    }


    private view.components.panels.positionInfoPanel.PositionInfoPanel positionInfoPanel;

    private void initCustomComponents() {
        saveButton = positionInfoPanel.getSaveButton();
        textArea = positionInfoPanel.getTextArea().getTextArea();
        addListeners();
        textArea.setText(position.getAdditionalInformation());
    }
    
    
    private void addListeners() {
        saveButton.addActionListener(e -> {
            position.setAdditionalInformation(textArea.getText());
            setVisible(false);
        });
    }
    
    public void setDialogueVisible(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}