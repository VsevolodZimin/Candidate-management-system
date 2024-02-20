package view.components.panels.menu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import lombok.Getter;

@Getter
public class ListMenu extends JList<MenuItemModel>{
    private final DefaultListModel<MenuItemModel> model = new DefaultListModel();
    private final int modelSize = model.size();
    private boolean animationFinished = false;
    private MenuItemSelectedCallback menuCallback;
    
    
    public ListMenu() {
        addData();
        setModel(model);
        initCellRenderer();
        addListeners();
    }
    
    private void addData() {
        model.addElement(new MenuItemModel("8", "Candidates"));
        model.addElement(new MenuItemModel("5", "Positions"));
        model.addElement(new MenuItemModel("1", "Metrics"));
        model.addElement(new MenuItemModel("2", "Export"));
    }
    
    
    private void initCellRenderer(){
        setCellRenderer(new DefaultListCellRenderer(){
        @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object obj, int index, boolean isSelected, boolean focus) {
                MenuItemModel data;
                data = (MenuItemModel) obj;
                MenuItem item = new MenuItem(data);
                item.setSelected(isSelected && animationFinished);
                return item;
            }
        });
        ((DefaultListCellRenderer)getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void addListeners(){
         addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed (MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    menuCallback.drawMovingPanel(index);
                    repaint();
                }
            }
        });
        
        
        for (MouseMotionListener mml : getMouseMotionListeners()){
            removeMouseMotionListener(mml);
        }
        
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        getActionMap().put("down", new AbstractAction(){
             @Override
             public void actionPerformed(ActionEvent e) {}
         });
        
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        getActionMap().put("up", new AbstractAction(){
             @Override
             public void actionPerformed(ActionEvent e) {}
         });
        }
    
    public void setAnimationFinished(boolean animationFinished) {
        this.animationFinished = animationFinished;
    }
       
    
    void registerCallback(MenuItemSelectedCallback callback) {
        menuCallback = callback;
    }
}