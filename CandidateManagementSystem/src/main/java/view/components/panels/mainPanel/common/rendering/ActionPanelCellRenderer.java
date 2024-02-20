package view.components.panels.mainPanel.common.rendering;
import entity.Entity;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sevices.JDBCService;
import view.components.panels.mainPanel.common.ActionPanel;

public class ActionPanelCellRenderer extends DefaultTableCellRenderer {
    
    JDBCService<? extends Entity> service;
    
    public ActionPanelCellRenderer(JDBCService<? extends Entity> service) {
        this.service = service;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ActionPanel action = new ActionPanel();
        action.setBackground(row % 2 == 0 ? new Color(232, 218, 239) : new Color(254, 253, 253));
        int modelIndex = table.convertRowIndexToModel(row);
        int id = (int)table.getModel().getValueAt(modelIndex, 0);
        Entity entity = service.findById(id);
        if(entity.getImportant() == 0) {
            action.setUnselectedFavourite();
        }
        else {
            action.setSelectedFavourite();
        }
        return action;
    }
}