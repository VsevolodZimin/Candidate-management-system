
package view.components.frames;

import java.awt.*;
import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import view.components.panels.customOptionPanel.CustomOptionMessage;

@Getter
@Setter
public class CustomOptionDialogue extends JDialog {

    private CustomOptionMessage panel;
    private JButton cancel;
    private JButton confirm;
    private int result = 0;
    private String text = "Are you sure you want to do it?";

    public CustomOptionDialogue(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initCustomComponents();
        addListeners();
    }

    public CustomOptionDialogue(String text, Frame parent, boolean modal) {
        super(parent, modal);
        this.text = text;
        initComponents();
        initCustomComponents();
        addListeners();
        panel.getTextTag().setText(text);
    }
    private void initComponents() {

        panel = new CustomOptionMessage(text, (int) 8.9 * text.length());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void initCustomComponents(){
        cancel = panel.getCancelButton();
        confirm = panel.getConfirmButton();
    }
    
    private void addListeners(){
        cancel.addActionListener(e -> setVisible(false));
        
        confirm.addActionListener(e -> {
            result = 1;
            setVisible(false);
        });
    }
    
    public void showDialogue (){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}