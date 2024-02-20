package view.components.panels.mainPanel.common;

import controller.Context;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sevices.JDBCCandidateService;
import sevices.JDBCPositionService;
import util.connection.PathsPropertiesManager;


public class OverdueWarningLabel extends JLabel {

    boolean paintGreen;
    private final JDBCCandidateService candidateService;
    private final JDBCPositionService positionService;
    
    public OverdueWarningLabel() {
        candidateService = Context.getCandidateService();
        positionService = Context.getPositionService();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new Color(0,0,0,0));
        Area round = new Area (new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), getWidth(), getWidth()));
        Area rectangle =  new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        rectangle.subtract(round);
        g2d.fill(rectangle);
        g2d.setPaint(paintGreen ? Color.decode("#71d466") : Color.red);
        g2d.fill(round);
        super.paintComponent(g); 
    }
   
    
    public void updateLabelForCandidate(){
        int count;
        count = candidateService.findOverdue(candidateService.findCurrent(candidateService.findAll())).size();
        if(count > 0) {
            setWarning(String.valueOf(count));
        }
        else {
            setNoWarning();
        }
    }
    
    public void updateLabelForPosition(){
        int count;
        count = positionService.findOverdue(positionService.findCurrent(positionService.findAll())).size();
        if(count > 0) {
            setWarning(String.valueOf(count));
        }
        else {
            setNoWarning();
        }
    }
        
    public void setNoWarning(){
        paintGreen = true;
        setText("");
        setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "doneWhite.png"));
    }
    
    public void setWarning(String text){
        paintGreen = false;
        setIcon(null);
        setText(text);
    }
}