package view.components.panels.additionalBenefits;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemArea extends javax.swing.JPanel {
    
    private int itemPosition = -1;
    private boolean goingUp = false;
    private boolean isToPickBox;
    private boolean isMovingBetweenBoxes = false;

    
    public ItemArea() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void resetItemsPosition(){
        itemPosition = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#ffffff"),0, getHeight(), Color.decode("#fff4ff")));
        g2d.fillRect(0, 0, getWidth()-1, getHeight()-1);
        super.paintComponent(g);
    }
}