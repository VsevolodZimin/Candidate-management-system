package view.components.panels.additionalBenefits.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import javax.swing.border.AbstractBorder;


public class FooterBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(0,0,0,0));
        Area a1 = new Area(new Rectangle2D.Double(x,y,width, height));
        g2d.draw(a1);
        g2d.setPaint(Color.decode("#703275"));
        g2d.drawLine(x, y ,width, y);
        super.paintBorder(c, g, x, y, width, height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 15,4, 20);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return new Insets(4, 21,4, 20);
    }
}