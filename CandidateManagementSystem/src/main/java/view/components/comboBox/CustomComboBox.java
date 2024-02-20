package view.components.comboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCPositionService;
import view.RoundedCornerBorder;
import view.TopRoundBorder;

@Getter
@Setter
public class CustomComboBox extends JComboBox {
    protected CustomBoxEditor customEditor;
    protected String selectedItemString;
    protected JDBCPositionService service;
    protected TopRoundBorder topRoundBorder = new TopRoundBorder();
    protected RoundedCornerBorder roundCornerBorder = new RoundedCornerBorder();
    protected BasicComboPopup popup = new BasicComboPopup(this);
    protected boolean isFired = false;
    
    
    public CustomComboBox() {
        popup.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setComponentPopupMenu(popup);
        setOpaque(false);
        initializeArrow();
        setBackground(Color.WHITE);
        addListeners();
        removeMouseListeners();
    }
    
    public CustomComboBox(DefaultComboBoxModel<String> boxModel) {
        
        setModel(boxModel);
        setEditable(true);
        popup.setBorder(BorderFactory.createLineBorder(Color.decode("#703275")));
        setComponentPopupMenu(popup);
        setOpaque(false);
        initializeArrow();
        customEditor = new CustomBoxEditor(this);
        setEditor(customEditor);
        setBackground(Color.WHITE);
        addListeners();
        removeMouseListeners();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(isFired){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getHeight()+7, getHeight()-3);
            g2d.fillRect(0, getHeight()/2, getWidth()-1, getHeight()/2);
            this.setBorder(topRoundBorder);
        }
        else {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);                        
            Area s1 = new Area(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, getHeight()-3, getHeight()-3));
            Area s2 = new Area(new Rectangle2D.Double(0,0,getWidth(), getHeight()));
            s2.subtract(s1);
            g2d.setPaint(new Color(0,0,0,0));
            g2d.fill(s2);
            g2d.setPaint(Color.WHITE);
            g2d.fill(s1);
            this.setBorder(roundCornerBorder);
 
        }
        super.paintComponent(g);
  }

    @Override
    public void setUI(ComboBoxUI ui) {
        super.setUI(new CustomComboBoxUI());
    }

  
  private void initializeArrow(){
      for(Component comp : getComponents()){
          if(comp instanceof AbstractButton) {
              JButton arrow = (JButton)comp;
              arrow.setContentAreaFilled(false);
              arrow.setBorder(BorderFactory.createEmptyBorder());
          }
      }
  }

    private void addListeners() {
        addPopupMenuListener(new PopupMenuListener(){
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                isFired = true;
                revalidate();
                repaint();

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                isFired = false;
                revalidate();
                repaint();

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                isFired = false;
                revalidate();
                repaint();
            }
        });  
    }
    
    private void removeMouseListeners(){
        for(MouseListener ml : getMouseListeners()){
            removeMouseListener(ml);
        }  
    }
}