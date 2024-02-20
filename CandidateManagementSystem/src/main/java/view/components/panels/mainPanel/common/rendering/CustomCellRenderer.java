
package view.components.panels.mainPanel.common.rendering;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class CustomCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setBackground(row % 2 == 0 ? new Color(232, 218, 239) : new Color(254,253,253));
        return component;
    }
}