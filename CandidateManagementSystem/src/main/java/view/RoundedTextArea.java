package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.AbstractBorder;
import lombok.Getter;

@Getter
public class RoundedTextArea extends JScrollPane {

    private final JTextArea textArea = new JTextArea();
    
    public RoundedTextArea() {
        setOpaque(false);
        setBorder(new AbstractBorder(){
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(Color.decode("#703275"));
                g2d.drawRoundRect(x, y, width-1, height-1, 45, 45);
                super.paintBorder(c, g, x, y, width, height);
            }
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(11, 20, 11, 20);
            }
            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                return new Insets(11, 20, 11, 20);
            }
        });
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setViewportView(textArea);
        setBackground(Color.WHITE);
        getVerticalScrollBar().setUnitIncrement(10);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);
        super.paintComponent(g);
    }
}