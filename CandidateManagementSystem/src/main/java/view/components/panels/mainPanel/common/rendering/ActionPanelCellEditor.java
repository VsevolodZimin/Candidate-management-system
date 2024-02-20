package view.components.panels.mainPanel.common.rendering;

import entity.Entity;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCService;
import view.components.panels.mainPanel.common.ActionButtonEvent;
import view.components.panels.mainPanel.common.ActionPanel;

@Setter
@Getter
public class ActionPanelCellEditor extends DefaultCellEditor {
    
    private ActionPanel actionPanel;
    private ActionButtonEvent event;
    private JDBCService<? extends Entity> service;
    private Entity entity;
    private boolean isFavourite;
    
    public ActionPanelCellEditor(ActionButtonEvent event, JDBCService<? extends Entity> service){
        super(new JCheckBox());
        this.service = service;
        setEvent(event);
        actionPanel = new ActionPanel(event);
        addFavouriteListener();
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        actionPanel.setBackground(row % 2 == 0 ? new Color(232, 218, 239) : new Color(254, 253, 253));
        
        int modelIndex = table.convertRowIndexToModel(row);
        int id = (int)table.getModel().getValueAt(modelIndex, 0);

        entity = service.findById(id);
        isFavourite = entity.getImportant() == 1;
        if(entity.getImportant() == 0) {
            actionPanel.setUnselectedFavourite();
        }
        else {
            actionPanel.setSelectedFavourite();
        }
        return actionPanel;
    }
    
    private void addFavouriteListener(){
        actionPanel.getFavourite().addActionListener(e -> {
            if(!isFavourite){
                actionPanel.setSelectedFavourite();
                isFavourite = true;
            }
            else {
                actionPanel.setUnselectedFavourite();
                isFavourite = false;
            }
        });
    }
}