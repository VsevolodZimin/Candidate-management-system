package view.components.panels.mainPanel.candidatePanel.table;

import controller.Context;
import entity.CandidateEntity;
import java.util.ArrayList;
import java.util.Vector;
import sevices.JDBCCandidateService;
import static util.enums.ContentType.CURRENT;
import static util.enums.ContentType.HISTORICAL;
import util.enums.Result;
import util.enums.Stage;
import view.components.panels.mainPanel.common.CustomTableModel;

public class CandidateTableModel extends CustomTableModel {

    CandidateTable table;
    JDBCCandidateService service;
    
    public CandidateTableModel(CandidateTable table){

        this.table = table;
        service = table.getCandidateService();
        addListeners();

        this.addColumn("id");
        this.addColumn("First Name");
        this.addColumn("Last Name");
        this.addColumn("Country");
        this.addColumn("Profile");
        this.addColumn("Source"); 
        this.addColumn("Current salary");
        this.addColumn("Salary expectations");
        this.addColumn("Selection process");
        this.addColumn("Additional information");
        this.addColumn("Position");
        this.addColumn("Result");
        this.addColumn("");
     }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch (column) {
            case 6,7 -> {
                if (aValue.equals("")) {
                    super.setValueAt(0, row, column);
                }
                else {
                    super.setValueAt(Long.valueOf((String)aValue), row, column);
                }   
                table.updateCandidate(table.getIdFromModelRow(), table.getCandidateService());
            }

            case 11 -> {
                    super.setValueAt(aValue, row, column);
                    table.updateCandidate(table.getIdFromModelRow(), table.getCandidateService());
                    populateTable();
                    Context.updateWarningCountForCandidate();
            }
            default -> {
                super.setValueAt(aValue, row, column);
                table.updateCandidate(table.getIdFromModelRow(), table.getCandidateService());
            }
        }
    }

    @Override
        public void insertRow(int row, Vector<?> rowData) {
            super.insertRow(row, rowData);
        }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0 || columnIndex == 6 || columnIndex == 7){
            return Long.class;
        }
        else {
            return String.class;
        }
    }
    
    @Override
    public void populateTable() {
        ArrayList<CandidateEntity> candidates = new ArrayList<>(); 
        clearTable();
        switch(Context.getType()){
            case CURRENT -> candidates = service.findCurrent(service.findAll());
            case HISTORICAL -> candidates = service.findHistorical(service.findAll());
            case IMPORTANT -> candidates = service.findImportant(service.findAll());
            case OVERDUE -> candidates = service.findOverdue(service.findCurrent(service.findAll()));
        }
        
        if(Context.getType() == CURRENT){
                switch(Context.getStage()){
                    case PHONE_SCREENING -> candidates = service.findByStage(Stage.PHONE_SCREENING, candidates);
                    case HR_INTERVIEW -> candidates = service.findByStage(Stage.HR_INTERVIEW, candidates);
                    case SUBMITTED_TO_HM -> candidates = service.findByStage(Stage.SUBMITTED_TO_HM, candidates);
                    case HM_INTERVIEW -> candidates = service.findByStage(Stage.HM_INTERVIEW, candidates);
                    case TEST -> candidates = service.findByStage(Stage.TEST, candidates);
                    case OFFER_LETTER -> candidates = service.findByStage(Stage.OFFER_LETTER,candidates);
                    case SELECTION_CLOSED -> candidates = service.findByStage(Stage.SELECTION_CLOSED, candidates);
            }
        }
        
        if(Context.getType() == HISTORICAL){
                switch(Context.getResult()){
                    case OFFER_DECLINED -> candidates = service.findByResult(Result.OFFER_DECLINED, candidates);
                    case HR_DISCARDS -> candidates = service.findByResult(Result.HR_DISCARDS, candidates);
                    case HM_DISCARDS -> candidates = service.findByResult(Result.HM_DISCARDS, candidates);
                    case HIRED -> candidates = service.findByResult(Result.HIRED, candidates);
            }
        }
        
        if(Context.isFitForHire()) {
            candidates = service.findFitForHire(candidates);
        }
        
        if(Context.isFromAgency()){
            candidates = service.findFromAgency(candidates);
        }
        
        for(CandidateEntity candidate : candidates){
            addRow (candidateToArray(candidate));
        }
    }
    
    public Object[] candidateToArray(CandidateEntity candidate){
        
        String country;        
        String profile;        
        String source;        
        String result;     

        switch(candidate.getResult()){
            case OFFER_DECLINED -> result = "Offer Declined";
            case HM_DISCARDS -> result = "HM Discards";
            case HR_DISCARDS -> result = "HR Discards";
            case HIRED -> result = "Hired";
            default -> result = "";
        }
        
        switch(candidate.getCountry()){
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
        
        switch(candidate.getProfile()) {
            case JUNIOR -> profile = "Junior";
            case MIDDLE -> profile = "Middle";
            case SENIOR -> profile = "Senior";
            case MANAGER -> profile = "Manager";
            case DIRECTOR -> profile = "Director";
            default -> profile = "";
        }
        
        switch(candidate.getSource()){
            case DATABASE -> source = "Database";
            case EXTERNAL_AGENCY -> source = "External Agency";
            case HEADHUNTER -> source = "Headhunter";
            case LINKEDIN -> source = "LinkedIn";
            case OTHERS -> source = "Other";
            case REFERRAL -> source = "Referral";
            default -> source = "";
        }
        
        Object[] data = new Object[13];
        data[0] = candidate.getId();
        data[1] = candidate.getFirstName();
        data[2] = candidate.getLastName();
        data[3] = country; 
        data[4] = profile;
        data[5] = source;
        data[6] = candidate.getCurrentSalary();
        data[7] = candidate.getSalaryExpectations();
        data[8] = "";
        data[9] = "";
        data[10] = candidate.getPosition() == null ? "" : candidate.getPosition().getName();
        data[11] = result;
        data[12] = "";
        return data;
    }
    
    private void addListeners (){
         
        addTableModelListener(e -> {
            setPanel();
        });
    }

    public void setPanel(){
        if(Context.getCandidatePanel() != null && Context.getCandidatePanel().getTable() != null){
            if(Context.getCandidatePanel().getTable().getRowCount() == 0) {
                Context.getCandidatePanel().remove(Context.getCandidatePanel().getScrollPane());
                Context.getCandidatePanel().add(Context.getNoDataPanel());
                Context.getCandidatePanel().setNoDataPanel(true);
                Context.getCandidatePanel().revalidate();
                Context.getCandidatePanel().repaint();
            }
            else if(Context.getCandidatePanel().getTable().getRowCount() > 0 && Context.getCandidatePanel().isNoDataPanel()) {
                Context.getCandidatePanel().remove(Context.getNoDataPanel());
                Context.getCandidatePanel().add(Context.getCandidatePanel().getScrollPane());
                Context.getCandidatePanel().setNoDataPanel(false);
                Context.getCandidatePanel().revalidate();
                Context.getCandidatePanel().repaint();
            }
        }
    }
}