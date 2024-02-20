
package view;

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

public class TopRoundBorder extends AbstractBorder {

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode("#703275"));
        Area roundedBorder = getRoundedBorder(x, y, width, height);
        Area lowerRectBorder = getRectBorder(x, width, height);
        lowerRectBorder.subtract(roundedBorder);
        roundedBorder.add(lowerRectBorder);
        g2d.draw(roundedBorder);
        super.paintBorder(c, g, x, y, width, height);
    }
    
    public Area getRoundedBorder(int x, int y, int width, int height){
        return new Area (new RoundRectangle2D.Double(x, y, width-1, height-2, height, height-4));
    }
    
    public Area getRectBorder(int x, int width, int height){
        return new Area(new Rectangle2D.Double(x, height/2, width-1, height));
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4,15,4,20);
    }
}