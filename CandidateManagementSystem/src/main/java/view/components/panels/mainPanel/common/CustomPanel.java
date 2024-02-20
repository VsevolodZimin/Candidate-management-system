package view.components.panels.mainPanel.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class CustomPanel extends JPanel {

    boolean isInitialized = false;
    
    protected CustomTable table;
    protected HeaderPanel headerPanel;
    protected JPanel buttonPanel;
    protected JLabel findLB;
    protected JScrollPane scrollPane;
    protected JTextField searchBox;
    protected JButton addButton;
    protected JToggleButton historicalButton;
    protected JToggleButton currentButton;
    protected JToggleButton importantButton;
    protected JToggleButton overdueButton;

    public void initializePanel(CustomTable table) {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        this.table = table;
        headerPanel = new HeaderPanel();
        findLB = headerPanel.getFindLB();
        searchBox = headerPanel.getSearchBox();
        addButton = headerPanel.getAddButton();
        currentButton = headerPanel.getCurrentButton();
        importantButton = headerPanel.getImportantButton();
        historicalButton = headerPanel.getHistoricalButton();
        overdueButton = headerPanel.getOverdueButton();
        buttonPanel = new JPanel();
        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.decode("#d6aedb"));
        scrollPane.setViewport(new JViewport(){
            {
                setOpaque(false);
            }
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(new GradientPaint(0, 0, Color.decode("#faf2f9"),0, getHeight(), Color.decode("#ffffff")));
                g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
                super.paintComponent(g);
            }
        });
        
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        setLayout(new BorderLayout());
        headerPanel.setName("headerPanel");
        findLB.setText("Find");
        add(headerPanel, BorderLayout.PAGE_START);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setName("buttonPanel");
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#f1e2f1"),0, getHeight(), Color.decode("#f7eef7")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
        super.paintComponent(g); 
    }
}