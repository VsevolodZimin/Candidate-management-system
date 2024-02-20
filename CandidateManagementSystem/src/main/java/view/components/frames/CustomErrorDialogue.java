package view.components.frames;

import view.components.panels.customErrorMessage.CustomErrorMessage;

import javax.swing.*;
import java.awt.Frame;

public class CustomErrorDialogue extends JDialog {

    private boolean isException= false;
    private String text;
    private CustomErrorMessage panel;

    public CustomErrorDialogue (boolean isException, String text, Frame parent, boolean modal) {
        super(parent, modal);
        this.text = text;
        this.isException = isException;
        initComponents();
        panel.getTextTag().setText(text);
        addListeners();
    }
    
    public CustomErrorDialogue(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        addListeners();
    }
    
    private void initComponents() {

        panel = new CustomErrorMessage(isException, text == null ? 268 : (int) 8.9 * text.length());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
    }
    
    private void addListeners(){
        panel.getConfirmButton().addActionListener(e -> CustomErrorDialogue.this.dispose());
    }
    
    public void showDialogue (){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}