package view.components.panels.menu;

import lombok.Getter;
import util.connection.PathsPropertiesManager;
import javax.swing.ImageIcon;

public class MenuItemModel {
    private final String icon;
    @Getter
    private String name;

    public MenuItemModel(String icon, String name){
        this.icon = icon;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ImageIcon toIcon(boolean isSelected){
        if(isSelected){
            return new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.menuIconDir + icon + ".png");
        }
        else
            return new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.menuIconDir + icon + "_selected" + ".png");
    }
}