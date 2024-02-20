package view.components.panels.mainPanel.common.rendering;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DetailsCellRenderer extends DefaultTableCellRenderer{

    JLabel label;
    
    public DetailsCellRenderer() {
        label = new JLabel("", SwingConstants.CENTER);
        label.setOpaque(true);        
        label.setText("open");
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
        label.setBackground(row % 2 == 0 ? new Color(232, 218, 239) : new Color(254, 253, 253));

        if(isSelected) {
            if(hasFocus) {
                label.setForeground(Color.BLUE);
            }
            else {
                label.setForeground(Color.GRAY);
            }
        }
        else {
            label.setForeground(Color.GRAY);
        }
        return label;
    }
}