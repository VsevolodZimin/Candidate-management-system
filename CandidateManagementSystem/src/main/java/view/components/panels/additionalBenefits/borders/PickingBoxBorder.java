package view.components.panels.additionalBenefits.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;


public class PickingBoxBorder extends AbstractBorder{
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Area a1 = new Area(new RoundRectangle2D.Double(x, y, width-1, height-1, 45, 45));
        Area a2 = new Area(new Rectangle2D.Double(x, y, width-1, height-1));
        a2.subtract(a1);
        g2d.setPaint(new Color(0,0,0,0));
        g2d.fill(a2);
        g2d.setPaint(Color.decode("#703275"));
        g2d.draw(a1);
        super.paintBorder(c, g, x, y, width, height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return new Insets(1, 1, 1, 1);
    }
}