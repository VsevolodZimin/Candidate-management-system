package view.components.panels.mainPanel.common.rendering;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import lombok.Getter;
import lombok.Setter;
import view.components.comboBox.CustomComboBox;

@Getter
@Setter
public class CustomCellEditor extends DefaultCellEditor {

    private JTextField textField;
    private CustomComboBox box;

    public CustomCellEditor(JTextField textField){
        super(textField);
        setTextField(textField);
        setBox(box);
        clickCountToStart = 2;
    }

    public CustomCellEditor(CustomComboBox box){
        super(box);
        setTextField((JTextField) box.getEditor().getEditorComponent());
        setBox(box);
        clickCountToStart = 2;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}