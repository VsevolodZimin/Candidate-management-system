package view.components.panels;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import lombok.Getter;
import lombok.Setter;
import util.enums.Stage;
import view.RoundedTextField;

@Getter
@Setter
public class DateField extends RoundedTextField {

    private Stage stage;
    boolean isInitialized = false;
    
    public DateField(Stage stage) {
        this.stage = stage;
        setDocument(getCustomDocument());
    }

    public DateField() {
        setDocument(getCustomDocument());
    }

    private Document getCustomDocument() {
        
        return new PlainDocument(){
            
            Pattern pattern;
            Matcher matcher;
            boolean found;
            
            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                super.replace(offset, length, text, attrs);
            }

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() == 0 && str.length() == 10){         //For whole date insertion
                    pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[0-2]).(19|20)\\d\\d$");
                    matcher = pattern.matcher(str);
                    found = matcher.find();
                    if(found){
                        super.insertString(offs, str, a);
                    }
                }
                else if(getLength() <= 9 && str.length() == 1) {    //For per symbol insertion 
                    if(offs == getLength()) {                       //Insertion is only allowed at the end of the line
                        pattern = Pattern.compile("^[0-9]$");
                        matcher = pattern.matcher(str);
                        found = matcher.find();
                        if (found) {
                            switch (offs) {
                                case 2 -> {
                                    super.insertString(2, ".", a);
                                    super.insertString(3, str, a);
                                }
                                case 5 -> {
                                    super.insertString(5, ".", a);
                                    super.insertString(6, str, a);
                                }
                                default -> super.insertString(offs, str, a);
                            }
                        }
                    }
                }
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                if(offs == getLength() - 1 || offs == 0 && len == getLength()) {    //deletion is only allowed per symbol starting from the end ordeletion of the whole txt
                    super.remove(offs, len);
                    }
            }
        };
    }
}