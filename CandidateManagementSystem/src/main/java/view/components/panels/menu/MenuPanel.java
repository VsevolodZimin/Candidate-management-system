package view.components.panels.menu;

import javax.swing.*;
import util.connection.PathsPropertiesManager;
import view.WindowEventCallback;

import java.awt.*;

public class MenuPanel extends JPanel {

    private Box.Filler filler1;
    private JLabel jLabel1;
    private view.components.panels.menu.ListMenu listMenu;
    private WindowEventCallback windowEventCallback;
    private final MenuItem menuItem = new MenuItem(null);
    private final Dimension dimensionMenu;
    private Timer timer;
    
    int currentIndex = -1;
    int selectedIndex;
    int currentY;
    int targetY;
    int speed;
    int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int menuItemHeight = (int)menuItem.getPreferredSize().getHeight();
    boolean goingUp;
    
     
    public MenuPanel() {
        initComponents();
        dimensionMenu = new Dimension(SCREEN_WIDTH/7, SCREEN_HEIGHT + 1);
        this.setOpaque(false);
        menuItem.getPreferredSize().getHeight();
        listMenu.setOpaque(false);
        this.setPreferredSize(dimensionMenu);
        
        listMenu.registerCallback(index -> {
            selectedIndex = index;
            targetY = menuItemHeight * index + listMenu.getY();
            setSpeed();
            goingUp = currentY > targetY;
            if(selectedIndex != currentIndex)
                if(!timer.isRunning())
                    timer.start();
        });
        
        timer = new Timer(0, e -> {
                if(goingUp){
                    if(currentY > targetY){
                        if(currentY - targetY > speed)
                            currentY -= speed;
                        else
                            currentY = targetY;
                        listMenu.setAnimationFinished(false);
                    }
                    else {
                        timer.stop();
                        listMenu.setAnimationFinished(true);
                        windowEventCallback.triggerEvent(selectedIndex);
                    }
                }
                else {
                    if(currentY < targetY){
                        if(targetY - currentY > speed)
                            currentY += speed;
                        else
                            currentY = targetY ;
                        listMenu.setAnimationFinished(false);
                    }
                    else {
                        timer.stop();
                        listMenu.setAnimationFinished(true);
                        windowEventCallback.triggerEvent(selectedIndex);
                    }
                }
                repaint();
                currentIndex = selectedIndex;
        });
    }
    
    private void initComponents() {
        jLabel1 = new JLabel();
        listMenu = new view.components.panels.menu.ListMenu();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 32767));

        setName("menuPanel");

        jLabel1.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "logoSinclair" + ".png"));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(listMenu, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(listMenu, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
    }

    public void registerCallback(WindowEventCallback event){
        windowEventCallback = event;
    }
    
    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g2D = (Graphics2D) gr;
        GradientPaint g = new GradientPaint(0, 0, new Color(207,162,209),0, getHeight(), Color.decode("#B8B8B8"));
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setPaint(g);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        if (currentIndex >= 0) {
            int menuX = 10;
            int height = menuItemHeight;
            int width = getWidth();
            g2D.setColor(new Color(242, 242, 242));
            g2D.fillRoundRect(menuX, currentY, width+10, height, 35, 35);
        }
    }
    
    private void setSpeed(){
        int distance;
        if(currentIndex < 0) {
            speed = 15;
        }
        else{
            distance = targetY - currentY;
            if (distance < 0) distance *= -1.2;
            else distance *= 1.2;
            speed = ((distance)/28) + 1;
            if(speed <= 2) speed = 3;
        }
    }
}