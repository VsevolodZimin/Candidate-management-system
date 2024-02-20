package view.components.panels.additionalBenefits;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JScrollPane;


public class ScrollPane extends JScrollPane {

    public ScrollPane() {
        setOpaque(false);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setUnitIncrement(10);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(0,126,200));
        g2d.fillRect(1, +20, getWidth()-20, getHeight()-1);
        super.paintComponent(g);
    }    
}