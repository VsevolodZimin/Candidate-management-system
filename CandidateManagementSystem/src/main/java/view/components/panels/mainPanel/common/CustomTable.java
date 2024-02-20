package view.components.panels.mainPanel.common;

import controller.Context;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCService;
import view.components.panels.mainPanel.candidatePanel.table.CandidateTable;
import entity.CandidateEntity;
import entity.Entity;
import entity.PositionEntity;
import java.awt.event.*;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.*;
import sevices.JDBCCandidateService;
import sevices.JDBCPositionService;
import util.enums.ContentType;
import java.awt.Color;
import javax.swing.BorderFactory;
import view.components.frames.CustomOptionDialogue;
import view.components.panels.mainPanel.common.rendering.CustomCellRenderer;


@Setter
@Getter
public abstract class CustomTable extends JTable{

    CustomOptionDialogue optionDialogue;
    
    private JDBCService<? extends Entity> service;

    public CustomTable() {
        setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb"), 1));
        initializeKeyBindings();
        this.service = this instanceof CandidateTable ? Context.getCandidateService() : Context.getPositionService();
        this.optionDialogue = new CustomOptionDialogue(Context.getParentFrame(), true);
    }

    @Override
    public boolean getScrollableTracksViewportWidth() { // if TRUE the columns will be stretched equally along the width of the parent
        return getPreferredSize().width < getParent().getWidth();
    }
    
    @Override
    public void doLayout(){
        
        TableColumn resizingColumn = null;
        
        if(tableHeader != null)
            resizingColumn = tableHeader.getResizingColumn();
        
        if(resizingColumn == null) {    //did not resize columnIndex
            setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            super.doLayout();
        }
        else {
            resizingColumn.setPreferredWidth((resizingColumn.getWidth()));
            TableColumnModel cm = getColumnModel();
            for(int i = 0; i < cm.getColumnCount(); i++){
                TableColumn column = cm.getColumn(i);
                column.setPreferredWidth(column.getWidth());
            }
        }
        if(columnModel.getTotalColumnWidth() < getParent().getWidth()){
            setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
            super.doLayout();
        }
        setAutoResizeMode(AUTO_RESIZE_OFF);
        super.doLayout();
        setRowHeight(45);
        setShowGrid(false);
        setRenderer();

    }
    
    private void setRenderer(){
        this.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }
    
    public abstract void initializeTable(JTextField searchBox);

    
    protected ActionButtonEvent getActionPanelEvent(){
        return new ActionButtonEvent(){
            int id;
            int modelRow;
            Entity entity;
            
            
            @Override
            public void onDelete() {
                id = getIdFromModelRow();
                modelRow = getModelRow();
                entity = service.findById(id);
                optionDialogue.showDialogue();

                int reply = optionDialogue.getResult();
                if(reply == 1) {
                    if (service instanceof JDBCCandidateService s) {
                        s.delete((CandidateEntity) entity);
                        Context.getCandidatePanel()
                                .getHeaderPanel()
                                .getWarningLabel()
                                .updateLabelForCandidate();
                    }
                    else if (service instanceof JDBCPositionService s) {
                        s.delete((PositionEntity) entity);
                        Context.getPositionPanel()
                                .getHeaderPanel()
                                .getWarningLabel()
                                .updateLabelForPosition();
                    }
                    ((DefaultTableModel)getModel()).removeRow(modelRow);
                }
                optionDialogue.dispose();
                optionDialogue.setResult(0);
            }

            @Override
            public void onAddToFavourite() {
                modelRow = getModelRow();
                id = getIdFromModelRow();
                entity = service.findById(id);
                if (entity.getImportant() == Entity.TRUE) {
                    entity.setImportant(Entity.FALSE);
                    if (Context.getType() == ContentType.IMPORTANT) {
                        ((DefaultTableModel) getModel()).removeRow(modelRow);
                    }
                }
                else {
                    entity.setImportant(Entity.TRUE);
                }
                if(service instanceof JDBCCandidateService s) {
                    s.update((CandidateEntity) entity);
                }
                else if(service instanceof JDBCPositionService s) {
                    s.update((PositionEntity) entity);
                }
            }
        };
    }
    
    public int getIdFromModelRow() {
        if(getModelRow() >= 0){
            return (int)getModel().getValueAt(getModelRow(),0);
        }
        else {
            return -1;
        }
    }

    public int getModelRow() {
        if (getSelectedRow() >= 0) {        
            return getRowSorter().convertRowIndexToModel(getSelectedRow());
        } 
        else {
            return -1;
        }
    }

    private void initializeKeyBindings(){

        AbstractAction emptyAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), "alt");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, 0, true), "ctrl");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_WINDOWS, 0, false), "windows");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CAPS_LOCK, 0, false), "caps");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false), "f1");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false), "f2");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false), "f3");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false), "f4");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false), "f5");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false), "f6");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, false), "f7");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0, false), "f8");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0, false), "f9");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, false), "f10");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0, false), "f11");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0, false), "f12");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAUSE, 0, false), "pause");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0, false), "prscr");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_NUM_LOCK, 0, false), "nmlck");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), "ctrl up");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), "ctrl down");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK), "ctrl left");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK), "ctrl right");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), "alt up");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), "alt down");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK), "alt left");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK), "alt right");

        getActionMap().put("alt", emptyAction);
        getActionMap().put("ctrl", emptyAction);
        getActionMap().put("windows", emptyAction);
        getActionMap().put("caps", emptyAction);
        getActionMap().put("pause", emptyAction);
        getActionMap().put("prscr", emptyAction);
        getActionMap().put("nmlck", emptyAction);
        getActionMap().put("f1", emptyAction);
        getActionMap().put("f2", emptyAction);
        getActionMap().put("f3", emptyAction);
        getActionMap().put("f4", emptyAction);
        getActionMap().put("f5", emptyAction);
        getActionMap().put("f6", emptyAction);
        getActionMap().put("f7", emptyAction);
        getActionMap().put("f8", emptyAction);
        getActionMap().put("f9", emptyAction);
        getActionMap().put("f10", emptyAction);
        getActionMap().put("f11", emptyAction);
        getActionMap().put("f12", emptyAction);
    }
}