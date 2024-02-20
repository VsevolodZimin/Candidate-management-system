package view.components.panels.additionalBenefits;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import view.components.panels.additionalBenefits.borders.HeaderBorder;

public class Header extends JLabel {

    public Header() {
        setOpaque(false);
        setBorder(new HeaderBorder());
        setBackground(Color.red);
        setPreferredSize(new Dimension(46,46));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#fff1fe"),0, getHeight(), Color.decode("#e4cae4")));
        Area a1 = new Area(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 43,45));
        Area a2 = new Area(new Rectangle2D.Float(0, getHeight()/2, getWidth(), getHeight()/2));
        a1.subtract(a2);
        a1.add(a2);
        g2d.fill(a1);
        super.paintComponent(g);
    }
}