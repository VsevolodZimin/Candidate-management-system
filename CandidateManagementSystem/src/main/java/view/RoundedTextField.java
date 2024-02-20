package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {

    public RoundedTextField() {
        setOpaque(false);
        setBorder(new RoundedCornerBorder());
    } 
    
@Override 
protected void paintComponent(Graphics g) {
    
    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
        g2.dispose();        
    }
    super.paintComponent(g);
  }
}