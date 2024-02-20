package view.components.panels.mainPanel.positionPanel;

import controller.Context;
import entity.PositionEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCPositionService;
import util.enums.ContentType;
import static util.enums.ContentType.CURRENT;

import view.components.frames.NewPositionDialog;
import view.components.panels.mainPanel.candidatePanel.ResultButtonPanel;
import view.components.panels.mainPanel.candidatePanel.StageButtonPanel;
import view.components.panels.mainPanel.common.CustomPanel;
import view.components.panels.mainPanel.positionPanel.table.PositionTable;
import view.components.panels.mainPanel.positionPanel.table.PositionTableModel;


@Getter
@Setter
public class PositionPanel extends CustomPanel {

    private NewPositionDialog dialog;
    private StageButtonPanel stageButtons;
    private ResultButtonPanel resultButtons;
    private JPanel noButtons;
    private JDBCPositionService service;
    private boolean isNoDataPanelSet = false;

    
    public void initialize () {
        setOpaque(false);
        super.initializePanel(new PositionTable());
        headerPanel.getWarningLabel().updateLabelForPosition();
        service = Context.getPositionService();
        table.initializeTable(searchBox);
        scrollPane.setName("position");

        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);
        
        stageButtons = new StageButtonPanel();
        resultButtons = new ResultButtonPanel();
        noButtons = new JPanel();
        addListeners();
    }
    
    private void addListeners (){
        addButton.addMouseListener(new MouseAdapter() {
            PositionEntity position = new PositionEntity();
            @Override
            public void mouseClicked(MouseEvent event) {
                dialog = new NewPositionDialog(true);
                position = dialog.getPosition();
                if(position != null) {
                    service.create(position);
                    if(Context.getType() == CURRENT){
                        getTableModel().populateTable();
                    }
                }
            }
        });
        
        currentButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.CURRENT);
                getTableModel().populateTable();
            }
        });
        
        historicalButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.HISTORICAL);
                getTableModel().populateTable();
            }
        });
        
        overdueButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                    Context.setType(ContentType.OVERDUE);
                    getTableModel().populateTable();

            }
        });
        
        importantButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Context.setType(ContentType.IMPORTANT);
                getTableModel().populateTable();
            }
        });
        
        searchBox.getDocument().addDocumentListener(new DocumentListener() {  
            @Override
            public void insertUpdate(DocumentEvent e) {
                getTableModel().fireTableDataChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getTableModel().fireTableDataChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getTableModel().fireTableDataChanged();
            }
        });
    }

    private PositionTableModel getTableModel(){
        return (PositionTableModel)table.getModel();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}