package view.components.panels.mainPanel.common;


import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
public abstract class CustomTableModel extends DefaultTableModel{
    
    public abstract void populateTable() throws SQLException;
    
    public void clearTable() {
        while(getRowCount() > 0){
            removeRow(0);
        }
    }
}