package view;

import java.awt.Graphics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class RoundedNumericTextField extends RoundedTextField {

    public RoundedNumericTextField() {
        
        super();
        setBorder(new RoundedCornerBorder());
        setOpaque(false);
        setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                Pattern pattern = Pattern.compile("^[0-9]+$");
                Matcher matcher = pattern.matcher(str);
                boolean found = matcher.find();
                
                if(found && this.getLength() < 15) {
                    if(this.getLength() == 1 && this.getText(0, this.getLength()).equals("0")){
                        super.remove(0, getLength());
                        super.insertString(0, str, a);
                    }
                    else {
                        super.insertString(offs, str, a);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}