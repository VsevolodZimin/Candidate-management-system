package view.components.panels.additionalBenefits.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.AbstractBorder;

public class ScrollPaneBorder extends AbstractBorder {

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLUE);
        g2d.drawRect(x+1, y, width-2, height);
        super.paintBorder(c, g, x, y, width, height);
    }
}