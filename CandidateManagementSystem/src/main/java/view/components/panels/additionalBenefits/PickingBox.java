
package view.components.panels.additionalBenefits;

import view.components.panels.additionalBenefits.borders.FooterBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.Getter;
import lombok.Setter;
import view.components.panels.additionalBenefits.borders.PickingBoxBorder;
import view.components.panels.additionalBenefits.borders.ScrollPaneBorder;

import javax.swing.*;

@Getter
@Setter
public class PickingBox extends JPanel {

    private Footer footer;
    private Header header;
    private ItemArea itemArea;
    private ScrollPane scrollPane;

    public PickingBox() {
        setOpaque(false);
        initComponents();
        initCustomComponents();
        setBorder(new PickingBoxBorder());
    }
    private void initComponents() {
        header = new view.components.panels.additionalBenefits.Header();
        footer = new view.components.panels.additionalBenefits.Footer();
        scrollPane = new view.components.panels.additionalBenefits.ScrollPane();
        itemArea = new view.components.panels.additionalBenefits.ItemArea();
        setLayout(new java.awt.BorderLayout());
        add(header, java.awt.BorderLayout.PAGE_START);
        add(footer, java.awt.BorderLayout.PAGE_END);
        scrollPane.setViewportView(itemArea);
        add(scrollPane, java.awt.BorderLayout.CENTER);
    }
    
    private void initCustomComponents(){
        itemArea.setOpaque(false);
        footer.setBorder(new FooterBorder());
        scrollPane.setBorder(new ScrollPaneBorder());
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.white);
        g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 45, 45);
        super.paintComponent(g);
    }
}