package view.components.comboBox;

import lombok.Getter;
import javax.swing.plaf.basic.BasicComboBoxEditor;

@Getter
public class CustomBoxEditor extends BasicComboBoxEditor {

    CustomTextField customTextField;

    public CustomBoxEditor(CustomComboBox comboBox) {
        customTextField = new CustomTextField(comboBox);
        this.editor = customTextField;  
    }
}