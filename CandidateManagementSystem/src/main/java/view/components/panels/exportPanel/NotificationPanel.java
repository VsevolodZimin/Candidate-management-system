
package view.components.panels.exportPanel;

import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Setter
@Getter
public class NotificationPanel extends JPanel {

    private int w;
    private int h;
    private boolean isShowed;
    private AbstractAction action;
    private boolean isErrorMessage = false;
    private PopupWrapper popUp;
    private JLabel icon;
    private JLabel text;
    
    public NotificationPanel() {
        initComponents();
        initCustomComponents();
    }
    
    public NotificationPanel(boolean isErrorMessage) {
        initComponents();
        initCustomComponents();
        this.isErrorMessage = isErrorMessage;
    }

    private void initComponents() {
        text = new JLabel();
        icon = new JLabel();
        text.setText("A copy of the database has been successfully saved!");
        text.setMaximumSize(null);
        text.setMinimumSize(null);
        text.setPreferredSize(null);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(icon, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(text, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(icon, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
            .addComponent(text, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        );
    }
    
    private void initCustomComponents() {
        setOpaque(false);
        w = getPreferredSize().width;
        h = getPreferredSize().height;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, Color.decode("#ffffff"),0, getHeight(), Color.decode(isErrorMessage ? "#ad4949" : "#e9e9e9")));
        g2d.fill(new Rectangle2D.Double(0,0,getWidth()-1, getHeight()-1));
        super.paintComponent(g); 
    }
    
    public void setIcon(ImageIcon icn){
        icon.setIcon(icn);
    }
    
    public void setText(String txt){
        text.setText(txt);
    }
}