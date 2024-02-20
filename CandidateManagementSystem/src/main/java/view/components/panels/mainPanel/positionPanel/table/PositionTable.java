package view.components.panels.mainPanel.positionPanel.table;
import controller.Context;
import entity.Entity;
import entity.PositionEntity;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import lombok.Getter;
import sevices.JDBCPositionService;
import util.Utils;
import util.enums.BoxType;
import view.components.comboBox.CustomComboBox;
import view.components.comboBox.CustomComboBoxModel;
import view.components.frames.CandidatesPerPositionDialogue;
import view.components.frames.PositionInfoDialog;
import view.components.panels.DateField;
import view.RoundedNumericTextField;
import view.components.panels.mainPanel.common.CustomTable;
import view.components.panels.mainPanel.common.rendering.*;
import java.awt.Color;
import javax.swing.BorderFactory;

@Getter
public class PositionTable extends CustomTable {

    public static final int NAME = 0; 
    public static final int COUNTRY = 1; 
    public static final int PROFILE = 2; 
    public static final int BUDGET = 3; 
    public static final int CANDIDATES = 4; 
    public static final int INFO = 5; 
    public static final int OPEN_DATE = 6; 
    public static final int CLOSE_DATE = 7; 
    public static final int ACTION_PANEL = 8; 
    
    JDBCPositionService positionService;
    TableColumn countryColumn;
    TableColumn profileColumn;
    TableColumn budgetColumn;
    TableColumn candidateColumn;
    TableColumn openDateColumn;
    TableColumn closeDateColumn;
    TableColumn additionalInfo;
    TableColumn actionPanelColumn;
    CustomComboBox countryBox;
    CustomComboBox profileBox;
    PositionTableModel positionTableModel;
    PositionInfoDialog positionDialog;
    CandidatesPerPositionDialogue candidateChoiceDialogue;
    

    
    public PositionTable(){
      
    }
    
    @Override
    public void initializeTable(JTextField searchBox) {

        positionService = Context.getPositionService();
        positionTableModel = new PositionTableModel(this);
        setModel(positionTableModel);
        setBackground(Color.decode("#d6aedb"));
        TableRowSorter<PositionTableModel> rowSorter = new TableRowSorter<>(positionTableModel);
        rowSorter.setRowFilter(new RowFilter<>() {
            @Override
            public boolean include(RowFilter.Entry<? extends PositionTableModel, ? extends Integer> entry) {
                for (int i = 0; i < positionTableModel.getColumnCount(); i++) {
                    if (Utils.formatToString(entry.getStringValue(i)).contains(Utils.formatToString(searchBox.getText()))) {
                        return true;
                    }
                }
                return false;
            }
        });

        Comparator<String> comp = (date1, date2) -> {
            int day = date1.substring(0, 2).compareTo(date2.substring(0, 2));
            int month = date1.substring(3, 5).compareTo(date2.substring(3, 5));
            int year = date1.substring(6).compareTo(date2.substring(6));
            return year != 0 ? year : (month != 0 ? month : day);
        };
        
        rowSorter.setComparator(BUDGET, (Comparator<Integer>) Integer::compareTo);
        getTableHeader().setBackground(Color.decode("#d6aedb"));
        getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb")));
        

        rowSorter.setComparator( OPEN_DATE, comp);
        rowSorter.setComparator( CLOSE_DATE, comp);
        
        setRowSorter(rowSorter);

//Initializing table columns:
        getTableHeader().setReorderingAllowed(false);

        getColumnModel().removeColumn(getColumnModel().getColumn(0));
        
        countryBox = new CustomComboBox(new CustomComboBoxModel(BoxType.COUNTRY));
        profileBox = new CustomComboBox(new CustomComboBoxModel(BoxType.PROFILE));
        
        countryColumn = getColumnModel().getColumn(COUNTRY);
        profileColumn = getColumnModel().getColumn(PROFILE);
        budgetColumn = getColumnModel().getColumn(BUDGET);
        candidateColumn = getColumnModel().getColumn(CANDIDATES);
        additionalInfo = getColumnModel().getColumn(INFO);
        openDateColumn = getColumnModel().getColumn(OPEN_DATE);
        closeDateColumn = getColumnModel().getColumn(CLOSE_DATE);
        actionPanelColumn = getColumnModel().getColumn(ACTION_PANEL);
        
        countryColumn.setCellEditor(new CustomCellEditor(countryBox));
        profileColumn.setCellEditor(new CustomCellEditor(profileBox));
        budgetColumn.setCellEditor(new CustomCellEditor(new RoundedNumericTextField()));
        budgetColumn.setCellRenderer(new CustomCellRenderer());
        candidateColumn.setCellEditor(new DetailsCellEditor(new JLabel()));
        candidateColumn.setCellRenderer(new DetailsCellRenderer());
        openDateColumn.setCellEditor(new CustomCellEditor(new DateField()));
        closeDateColumn.setCellEditor(new CustomCellEditor(new DateField()));
        additionalInfo.setCellEditor(new DetailsCellEditor(new JLabel()));
        additionalInfo.setCellRenderer(new DetailsCellRenderer());
        actionPanelColumn.setCellEditor(new ActionPanelCellEditor(getActionPanelEvent(), positionService));
        actionPanelColumn.setCellRenderer(new ActionPanelCellRenderer(positionService));
        actionPanelColumn.setResizable(false);

        setSurrendersFocusOnKeystroke(true);

        positionTableModel.populateTable();
        addListener(Context.getParentFrame());

    }

    public PositionEntity buildPositionFromTableCells (Entity entity) {
        if(entity != null){
            PositionEntity position;
            position = (PositionEntity) entity;
            position.setName((String)getValueAt(getSelectedRow(), NAME));
            position.setCountry(Utils.getCountryFromString((String)getValueAt(getSelectedRow(), COUNTRY)));
            position.setProfile(Utils.getProfileFromString((String)getValueAt(getSelectedRow(), PROFILE)));
            position.setBudget((long)getValueAt(getSelectedRow(), BUDGET));
            position.setAdditionalInformation((String)getValueAt(getSelectedRow(), INFO));
            position.setOpenDate(Utils.getDateFromString((String)getValueAt(getSelectedRow(), OPEN_DATE)));
            position.setCloseDate(Utils.getDateFromString((String)getValueAt(getSelectedRow(), CLOSE_DATE)));
            return position;
        }
        return null;
    }
    
    private void addListener(Frame frame){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int rowIndex = rowAtPoint(event.getPoint());
                int columnIndex = columnAtPoint(event.getPoint());
                PositionEntity position;
                if (rowIndex >= 0 && columnIndex >= 0) {
                    switch (columnIndex) {
                        case CANDIDATES -> {
                            int id = getIdFromModelRow();
                            candidateChoiceDialogue = new CandidatesPerPositionDialogue(frame, true, positionService, id);
                            candidateChoiceDialogue.setDialogueVisible();
                            candidateChoiceDialogue.dispose();
                        }
                        case INFO -> {
                            int id = getIdFromModelRow();
                            position = positionService.findById(id);
                            positionDialog = new PositionInfoDialog(position, frame, true);
                            positionDialog.setDialogueVisible();
                            positionDialog.dispose();
                            positionService.update(position);
                        }
                    }
                }
            }
        });
    }
    void updatePosition(int id, JDBCPositionService service){
            service.update(buildPositionFromTableCells(service.findById(id)));
    }
}