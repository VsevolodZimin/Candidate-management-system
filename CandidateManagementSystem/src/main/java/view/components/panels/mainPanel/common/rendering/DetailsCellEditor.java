package view.components.panels.mainPanel.common.rendering;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class DetailsCellEditor extends CustomCellEditor {
   
    JLabel label;
    
    public DetailsCellEditor(JLabel label) {
        super(new JTextField());
        this.label = label;
        this.label.setText("open");
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        clickCountToStart = 1;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        JTextField field = (JTextField) this.getComponent();
        field.setEditable(false);
        label.setOpaque(true);
        label.setBackground(row % 2 == 0 ? new Color(232, 218, 239) : new Color(254,253,253));
        label.setForeground(Color.BLUE);
        return label;
    }    
}