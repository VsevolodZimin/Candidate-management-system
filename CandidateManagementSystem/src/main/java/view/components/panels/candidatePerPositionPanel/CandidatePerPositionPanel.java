package view.components.panels.candidatePerPositionPanel;

import entity.CandidateEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import sevices.JDBCPositionService;
import view.components.panels.NoDataPanel;


public class CandidatePerPositionPanel extends JPanel {

    private JPanel boxArea;
    private Item header;
    private JScrollPane scrollPanel;

    public CandidatePerPositionPanel(JDBCPositionService service, int positionId) {
        initComponents();
        initialize(service, positionId);
    }
    
     public final void initialize(JDBCPositionService service, int positionId){
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        header.setBackground(Color.decode("#d6aedb"));
        header.setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb")));
        boxArea.setBorder(BorderFactory.createLineBorder(Color.decode("#d6aedb")));

        int i = 0;
             for(CandidateEntity candidate : service.findCandidatesByPosition(positionId)) {
                 Item item = new Item(candidate.getFirstName(), candidate.getLastName(), candidate.getCurrentSalary(), candidate.getSalaryExpectations());
                 item.setBackground( i % 2 == 0 ? new Color(232, 218, 239) : new Color(254, 253, 253));
                 boxArea.add(item);
                 i++;
             }
         if(i == 0){
                boxArea.setLayout(new BorderLayout());
                boxArea.add(new NoDataPanel());
            }
    }

    private void initComponents() {
        header = new Item();
        scrollPanel = new JScrollPane();
        boxArea = new JPanel();
        setLayout(new BorderLayout());
        header.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        add(header, BorderLayout.PAGE_START);
        boxArea.setLayout(new BoxLayout(boxArea, BoxLayout.Y_AXIS));
        scrollPanel.setViewportView(boxArea);
        add(scrollPanel, BorderLayout.CENTER);
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}