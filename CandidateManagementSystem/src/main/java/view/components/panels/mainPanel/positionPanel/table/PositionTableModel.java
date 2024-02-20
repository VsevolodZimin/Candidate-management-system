package view.components.panels.mainPanel.positionPanel.table;
import entity.PositionEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JPanel;
import sevices.JDBCPositionService;
import util.Utils;
import view.components.panels.exportPanel.CustomPopupFactory;
import view.components.panels.exportPanel.PopupWrapper;
import controller.Context;
import view.components.panels.mainPanel.common.CustomTableModel;


public class PositionTableModel extends CustomTableModel {
    
    private final PositionTable table;
    private final JDBCPositionService service;

    public PositionTableModel(PositionTable table){
        this.table = table;
        this.service = table.getPositionService();
        this.addColumn("id");
        this.addColumn("Name");
        this.addColumn("Country");
        this.addColumn("Profile");
        this.addColumn("Budget");
        this.addColumn("Candidates");
        this.addColumn("Info");
        this.addColumn("Open Date");
        this.addColumn("Close Date");
        this.addColumn("");    
        addListeners();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        
        switch (column) {
            case 1 -> {
                if(!findDuplicate((String)aValue)){
                    super.setValueAt(aValue, row, column);
                    table.updatePosition(table.getIdFromModelRow(), table.getPositionService());
                }
                else {
                    PopupWrapper popup = CustomPopupFactory.getPopUp((JPanel)table.getParent().getParent().getParent(), CustomPopupFactory.PopupType.DUPLICATE, null);
                    popup.showPopup();
                }
            }
            case 4 -> {
                if (!aValue.equals("")) {
                    super.setValueAt(Long.valueOf((String) aValue), row, column);
                    table.updatePosition(table.getIdFromModelRow(), table.getPositionService());
                }
            }
            case 5, 6, 9 -> {}
            case 8 -> {
                String creationDateStr = getValueAt(row, 7).toString();
                String closingDateStr = aValue.toString();
                LocalDate creationDate = Utils.getDateFromString(creationDateStr);
                LocalDate closingDate = Utils.getDateFromString(closingDateStr);
                if (creationDate != null && closingDate != null) {
                    if (!creationDate.isAfter(closingDate)) {
                        super.setValueAt(aValue, row, column);
                    }
                }
                else {
                    super.setValueAt("", row, column);
                }
                table.updatePosition(table.getIdFromModelRow(), table.getPositionService());
                populateTable();
                Context.getPositionPanel()
                        .getHeaderPanel()
                        .getWarningLabel()
                        .updateLabelForPosition();
            }
            default -> {
                super.setValueAt(aValue, row, column);
                table.updatePosition(table.getIdFromModelRow(), table.getPositionService());
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 7;
    }
    
    @Override
    public void populateTable() {
        
        ArrayList<PositionEntity> candidates = new ArrayList<>(); 
        clearTable();
        switch(Context.getType()) {
            case CURRENT -> candidates = service.findCurrent(service.findAll());
            case HISTORICAL -> candidates = service.findHistorical(service.findAll());
            case IMPORTANT -> candidates = service.findImportant(service.findAll());
            case OVERDUE -> candidates = service.findOverdue(service.findCurrent(service.findAll()));
        }
        
        for(PositionEntity position : candidates){
            addRow(positionToArray(position));
        }
    }
    
    public Object[] positionToArray(PositionEntity position){
        
        String country;        
        String profile;
        
        switch(position.getCountry()){
            case APAC -> country = "APAC";
            case BULGARIA -> country = "Bulgaria";
            case COLOMBIA -> country = "Colombia";
            case FRANCE -> country = "France";
            case ITALY -> country = "Italy";
            case MEXICO -> country = "Mexico";
            case OTHER -> country = "Other";
            case SPAIN -> country = "Spain";
            case UK -> country = "UK";
            default -> country = "";
        }
        
        switch(position.getProfile()){
            case JUNIOR -> profile = "Junior";
            case MIDDLE -> profile = "Middle";
            case SENIOR -> profile = "Senior";
            case MANAGER -> profile = "Manager";
            case DIRECTOR -> profile = "Director";
            default -> profile = "";
        }
        
        Object[] data = new Object[10];
        data[0] = position.getId();
        data[1] = position.getName();
        data[2] = country;
        data[3] = profile;
        data[4] = position.getBudget();
        data[5] = "";
        data[6] = "";
        data[7] = Utils.formatToEU(Utils.getDBStringFromDate(position.getOpenDate()));
        data[8] = Utils.formatToEU(Utils.getDBStringFromDate(position.getCloseDate()));
        data[9] = "";

        return data;
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 4) {
            return Long.class;
        }
        else {
            return String.class;
        }
    }
    
    private boolean findDuplicate (String positionName) {
        for(PositionEntity position : service.findAll()){
            if(position.getName().equalsIgnoreCase(positionName)){
                return true;
            }
        }
        return false;
    }
    
    private void addListeners (){
        addTableModelListener(e -> setPanel());
    }

    public void setPanel() {
        if(Context.getPositionPanel() != null && Context.getPositionPanel().getTable() != null){
            if(Context.getPositionPanel().getTable().getRowCount() == 0) {
                Context.getPositionPanel().remove(Context.getPositionPanel().getScrollPane());
                Context.getPositionPanel().add(Context.getNoDataPanel());
                Context.getPositionPanel().setNoDataPanelSet(true);
                Context.getPositionPanel().revalidate();
                Context.getPositionPanel().repaint();
            }
            else if(Context.getPositionPanel().getTable().getRowCount() > 0 && Context.getPositionPanel().isNoDataPanelSet()) {
                Context.getPositionPanel().remove(Context.getNoDataPanel());
                Context.getPositionPanel().add(Context.getPositionPanel().getScrollPane());
                Context.getPositionPanel().setNoDataPanelSet(false);
                Context.getPositionPanel().revalidate();
                Context.getPositionPanel().repaint();
            }
        }
    }
}