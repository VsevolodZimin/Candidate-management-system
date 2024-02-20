package view.components.comboBox;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;


public class AutoComboBox extends JComboBox {

    private AutoComboBoxEditor autoComboBoxEditor;


    private boolean isFired;
    
    
    public class AutoComboBoxEditor extends BasicComboBoxEditor {
        public AutoComboBoxEditor(List list) {
            this.editor = new AutoTextField(list, AutoComboBox.this);
        }
    }
    
    public AutoComboBox(List list) {
        isFired = false;
        this.setFont(new Font("SegoeUI", Font.ITALIC, 18));

        autoComboBoxEditor = new AutoComboBoxEditor(list);
        setEditable(true);
        setModel(new DefaultComboBoxModel(list.toArray()) {
            @Override
            protected void fireContentsChanged(Object obj, int i, int j) {
                if (!isFired)
                    super.fireContentsChanged(obj, i, j);
            }
        });
        setEditor(autoComboBoxEditor);
    }

    void setSelectedValue(Object obj) {
        if (!isFired) {
            isFired = true;
            setSelectedItem(obj);
            fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder,1));
            System.out.println("Auto seleted item = " + getSelectedItem());
            isFired = false;
        }
    }
    @Override
    protected void fireActionEvent() {
        if (!isFired)
            super.fireActionEvent();
    }
}