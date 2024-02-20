/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

package view.components.comboBox;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.text.*;

public class AutoTextField extends JTextField {
    
    private List<String> dataList;
    private final AutoComboBox autoComboBox;
    
    public AutoTextField(List list, AutoComboBox b) {
        if (list == null) {
            throw new IllegalArgumentException("values cannot be null");
        } 
        else {
            dataList = list;
            autoComboBox = b;
            init();
        }
    }
    
    private void init() {
        setDocument(new AutoDocument());
        if (!dataList.isEmpty())
            setText(dataList.get(0));
    }
    
    private String getMatch(String toMacth) {
        for (String listItem : dataList) {
            if (listItem != null) {
                if (listItem.toLowerCase().startsWith(toMacth.toLowerCase()))
                    return listItem;
            }
        }
        return null;
    }
   
    
    @Override
    public void replaceSelection(String s) {
        AutoDocument _lb = (AutoDocument) getDocument();
        if (_lb != null)
            try {
                int i = Math.min(getCaret().getDot(), getCaret().getMark());
                int j = Math.max(getCaret().getDot(), getCaret().getMark());
                _lb.replace(i, j - i, s, null);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    class AutoDocument extends PlainDocument {
        @Override
        public void replace(int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
            super.remove(offset, length);
            insertString(offset, text, attributeSet);
        }
        
        @Override
        public void insertString(int offset, String text, AttributeSet attributeSet) throws BadLocationException {   
            if (text == null || text.isEmpty())
                return;
            String s1 = getText(0, getLength());
            String match = s1 + text;
            for(String s: dataList) {
                if (text.equals(s) || s1.equals(s)) {
                    match = text;
                    break;
                }
            }
            String s2 = getMatch(match);
            int j = (offset + text.length()) - 1;
            if (s2 == null) {
                super.remove(0, getLength());
            }
            else {
                autoComboBox.setSelectedValue(s2);
                super.remove(0, getLength());
                super.insertString(0, s2, attributeSet);
            setSelectionStart(j + 1);
            setSelectionEnd(getLength());
            }
        }

   
        @Override
        public void remove(int offset, int length) throws BadLocationException {
            super.remove(0, getLength());
        }
    }
}