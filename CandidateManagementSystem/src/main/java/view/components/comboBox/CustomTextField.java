
package view.components.comboBox;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.Context;
import view.RoundedTextField;
import view.components.frames.CustomErrorDialogue;


public class CustomTextField extends RoundedTextField {

    CustomComboBox comboBox;
    CustomDocument doc;
    public CustomTextField(CustomComboBox comboBox) {
        super();
        doc = new CustomDocument();
        setDocument(doc);
        setBorder(BorderFactory.createEmptyBorder());
        this.comboBox = comboBox;
        addListeners();
    }

    private void addListeners(){
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_DOWN_MASK, false), "backspace");
        this.getActionMap().put("backspace", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0, false), "backspace");
        this.getActionMap().put("backspace", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doc.remove(0, doc.getLength());
                    comboBox.setSelectedItemString("");
                }
                catch (BadLocationException ex) {
                    ex.printStackTrace();
                    new CustomErrorDialogue(true, "Character sequence error", Context.getParentFrame(), true).showDialogue();
                }
            }
        });
        
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {  //Resetting selection after focus lost
                setSelectionStart(getDocument().getLength());
                setSelectionEnd(getDocument().getLength());            
            }
        });
    }
    
    class CustomDocument extends PlainDocument {
        StringBuilder currentStr = new StringBuilder();
        String result;
        
        @Override
        public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            remove(offset, length);
            insertString(offset, text, attrs);
        }

        @Override
        public void insertString(int offset, String text, AttributeSet attrs) throws BadLocationException {
                currentStr.append(getText(0, getLength()));
                currentStr.append(text);
                result = match(currentStr.toString());
                super.remove(0, getLength());
                super.insertString(0, result, attrs);
                CustomTextField.this.setSelectionStart(offset + text.length());
                CustomTextField.this.setSelectionEnd(this.getLength());
                comboBox.setSelectedItemString(result);
                currentStr.delete(0, currentStr.length());
        }

        @Override
        public void remove(int offset, int length) throws BadLocationException {
            super.remove(offset, length);
        }
        
        private String match(String fieldText){
            for(int i = 0; i < comboBox.getModel().getSize(); i++) {
                String boxItem = (String)comboBox.getModel().getElementAt(i);
                if(boxItem.toLowerCase().startsWith(fieldText.toLowerCase())){
                    return boxItem;
                }
            }
            return "";
        }
    }
}