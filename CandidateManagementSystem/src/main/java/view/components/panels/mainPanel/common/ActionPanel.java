package view.components.panels.mainPanel.common;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;
import util.connection.PathsPropertiesManager;

import java.awt.*;

@Getter
@Setter
public class ActionPanel extends JPanel {

    private JButton delete;
    private JButton favourite;
    private int row;
    private ActionButtonEvent event;
    
    public ActionPanel(ActionButtonEvent event){
        init();
        setEvent(event);
    }
    
    public ActionPanel(){
        init();
    }

    private void init(){
        setBackground(new Color(255, 255, 255));
        GridBagConstraints gridBagConstraints;

        favourite = new JButton();
        favourite.setPreferredSize(new Dimension(23, 23));
        favourite.setContentAreaFilled(false);
        favourite.setBorder(BorderFactory.createEmptyBorder());
        favourite.addActionListener(e -> event.onAddToFavourite());
        
        delete = new JButton();
        delete.setContentAreaFilled(false);
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "delete.png"));
        delete.addActionListener(e -> event.onDelete());
        delete.setPreferredSize(new Dimension(23, 23));
        
        setBackground(new Color(255, 255, 255));
        setToolTipText("");
        setPreferredSize(new Dimension(88, 40));
        setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(17, 17, 17, 17);
        add(favourite, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(17, 17, 17, 17);
        add(delete, gridBagConstraints);
    }
    
    public void setSelectedFavourite() {
        favourite.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "fav21.png"));
    }
    
    public void setUnselectedFavourite() {
        favourite.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "noFav21.png"));
    }
}