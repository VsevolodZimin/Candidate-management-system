package view.components.panels.mainPanel.candidatePanel.table;

import controller.Context;
import entity.CandidateEntity;
import entity.PositionEntity;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import lombok.Getter;
import lombok.Setter;
import sevices.JDBCCandidateService;
import sevices.JDBCLabelService;
import sevices.serviceImpl.SQLiteCandidateServiceImpl;
import sevices.serviceImpl.SQLitePositionServiceImpl;
import util.Utils;
import util.enums.BoxType;
import view.components.comboBox.CustomComboBox;
import view.components.comboBox.CustomComboBoxModel;
import view.components.comboBox.PositionComboBox;
import view.components.frames.AdditionalInformationDialog;
import view.components.frames.SelectionProcessDialog;
import view.components.panels.mainPanel.common.CustomTable;
import view.components.panels.mainPanel.common.rendering.*;
import view.RoundedNumericTextField;
import java.awt.Color;
import java.awt.Graphics;


/**
 * Table holding candidate entities
 */
@Setter
@Getter
public class CandidateTable extends CustomTable {
    
    //Table columns
    public static final int FIRST_NAME = 0; 
    public static final int LAST_NAME = 1; 
    public static final int COUNTRY = 2; 
    public static final int PROFILE = 3; 
    public static final int SOURCE = 4; 
    public static final int CURRENT_SALARY = 5; 
    public static final int SALARY_EXPECTATIONS = 6; 
    public static final int SELECTION_PROCESS = 7; 
    public static final int ADDITIONAL_INFORMATION = 8; 
    public static final int POSITION = 9; 
    public static final int RESULT = 10; 
    public static final int ACTION_PANEL = 11; 
    
    private TableColumn countryColumn;
    private TableColumn sourceColumn;
    private TableColumn profileColumn;
    private TableColumn selectionProcessColumn;
    private TableColumn additionalInformationColumn;
    private TableColumn salaryExpectationColumn;
    private TableColumn currentSalaryColumn;
    private TableColumn resultColumn;
    private TableColumn positionColumn;
    private TableColumn actionPanelColumn;    
    private JDBCLabelService labelService;
    private SQLiteCandidateServiceImpl candidateService;
    private SQLitePositionServiceImpl positionService;
    private CustomComboBox countryBox;
    private CustomComboBox profileBox;
    private CustomComboBox sourceBox;
    private CustomComboBox resultBox;
    private PositionComboBox positionBox;

    public CandidateTable(){
        this.labelService = Context.getLabelService();
        this.positionService = (SQLitePositionServiceImpl)Context.getPositionService();
        this.candidateService = (SQLiteCandidateServiceImpl)Context.getCandidateService();
    }

    @Override
    public void initializeTable(JTextField searchBox) {
        setModel(new CandidateTableModel(this));
        setGridColor(Color.decode("#d6aedb"));
        setBackground(Color.decode("#d6aedb"));
        TableRowSorter<CandidateTableModel> rowSorter = new TableRowSorter<>(getTableModel());
        rowSorter.setRowFilter(new RowFilter<>() {
            @Override
            public boolean include(RowFilter.Entry<? extends CandidateTableModel, ? extends Integer> entry) {
                for (int i = 0; i < getTableModel().getColumnCount(); i++) {
                    if (Utils.formatToString(entry.getStringValue(i)).contains(Utils.formatToString(searchBox.getText())))
                        return true;
                }
                return false;
            }
        });
        setRowSorter(rowSorter);
        getTableHeader().setBackground(Color.decode("#d6aedb"));
        getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb")));
        getTableModel().populateTable();

//Initializing table columns:

        getTableHeader().setReorderingAllowed(false);

        getColumnModel().removeColumn(getColumnModel().getColumn(0));
        countryColumn = getColumnModel().getColumn(COUNTRY);
        profileColumn = getColumnModel().getColumn(PROFILE);
        sourceColumn = getColumnModel().getColumn(SOURCE);
        currentSalaryColumn = getColumnModel().getColumn(CURRENT_SALARY);
        salaryExpectationColumn = getColumnModel().getColumn(SALARY_EXPECTATIONS);
        selectionProcessColumn = getColumnModel().getColumn(SELECTION_PROCESS);
        additionalInformationColumn = getColumnModel().getColumn(ADDITIONAL_INFORMATION);
        positionColumn = getColumnModel().getColumn(POSITION);
        resultColumn = getColumnModel().getColumn(RESULT);
        actionPanelColumn = getColumnModel().getColumn(ACTION_PANEL);

        countryBox = new CustomComboBox(new CustomComboBoxModel(BoxType.COUNTRY));
        profileBox = new CustomComboBox(new CustomComboBoxModel(BoxType.PROFILE));
        sourceBox = new CustomComboBox(new CustomComboBoxModel(BoxType.SOURCE));
        resultBox = new CustomComboBox(new CustomComboBoxModel(BoxType.RESULT));
        positionBox = new PositionComboBox(positionService);

        setSurrendersFocusOnKeystroke(true);

        countryColumn.setCellEditor(new CustomCellEditor(countryBox));
        profileColumn.setCellEditor(new CustomCellEditor(profileBox));
        sourceColumn.setCellEditor(new CustomCellEditor(sourceBox));
        salaryExpectationColumn.setCellEditor(new CustomCellEditor(new RoundedNumericTextField()));
        salaryExpectationColumn.setCellRenderer(new CustomCellRenderer());
        currentSalaryColumn.setCellEditor(new CustomCellEditor(new RoundedNumericTextField()));
        currentSalaryColumn.setCellRenderer(new CustomCellRenderer());
        selectionProcessColumn.setCellRenderer(new DetailsCellRenderer());
        selectionProcessColumn.setCellEditor(new DetailsCellEditor(new JLabel()));
        additionalInformationColumn.setCellRenderer(new DetailsCellRenderer());
        additionalInformationColumn.setCellEditor(new DetailsCellEditor(new JLabel()));
        resultColumn.setCellEditor(new CustomCellEditor(resultBox));
        positionColumn.setCellEditor(new CustomCellEditor(positionBox));
        actionPanelColumn.setCellRenderer(new ActionPanelCellRenderer(candidateService));
        actionPanelColumn.setCellEditor(new ActionPanelCellEditor(getActionPanelEvent(), candidateService));
        actionPanelColumn.setResizable(false);
        
        addListeners();
    }
    
    
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int rowIndex = rowAtPoint(event.getPoint());
                int columnIndex = columnAtPoint(event.getPoint());
                CandidateEntity candidate;
                if (rowIndex >= 0 && columnIndex >= 0) {
                    switch (columnIndex) {
                        case SELECTION_PROCESS -> {
                            int id = getIdFromModelRow();
                            candidate = candidateService.findById(id);
                            SelectionProcessDialog dialog = new SelectionProcessDialog(true, candidate);
                            dialog.initialize();
                            dialog.setDialogueVisible();
                            dialog.dispose();
                            candidateService.update(candidate);
                            getTableModel().populateTable();
                            Context.getCandidatePanel()
                                    .getHeaderPanel()
                                    .getWarningLabel()
                                    .updateLabelForCandidate();
                        }
                        case ADDITIONAL_INFORMATION -> {
                            int id = getIdFromModelRow();
                            candidate = candidateService.findById(id);
                            AdditionalInformationDialog dialog = new AdditionalInformationDialog(candidate, true);
                            dialog.setDialogueVisible();
                            candidateService.updateCandidateWithLabels(candidate, dialog.getPickedLabels());
                            dialog.dispose();
                        }
                    }
                }
            }
        });
    }


    public CandidateEntity buildCandidateFromTableCells (CandidateEntity candidate) {

        PositionEntity position = null;
        for (PositionEntity p : positionService.findAll()) {
            if(p.getName().equals((getValueAt(getSelectedRow(), POSITION)))){
                position = p;
            }
        }

        if(candidate != null) {
            candidate.setFirstName((String)getValueAt(getSelectedRow(), FIRST_NAME));
            candidate.setLastName((String)getValueAt(getSelectedRow(), LAST_NAME));
            candidate.setCountry(Utils.getCountryFromString((String)getValueAt(getSelectedRow(), COUNTRY)));
            candidate.setProfile(Utils.getProfileFromString((String)getValueAt(getSelectedRow(), PROFILE)));
            candidate.setSource(Utils.getSourceFromString((String)getValueAt(getSelectedRow(), SOURCE)));
            candidate.setCurrentSalary((long)getValueAt(getSelectedRow(), CURRENT_SALARY));
            candidate.setSalaryExpectations((long)getValueAt(getSelectedRow(), SALARY_EXPECTATIONS));
            candidate.setPosition(position);
            candidate.setResult(Utils.getResultFromString((String)getValueAt(getSelectedRow(), RESULT)));
            return candidate;
        }
        return null;
    }
    

    void updateCandidate(int id, JDBCCandidateService service){
        service.update(buildCandidateFromTableCells(service.findById(id)));
    }
    
    private CandidateTableModel getTableModel(){
        return (CandidateTableModel)getModel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
    }
}