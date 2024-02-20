
package view.components.comboBox;

import util.connection.PathsPropertiesManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * Custom Arrow object for custom ComboBoxes
 */

public class ArrowButton extends JButton{
    public ArrowButton(){
        super("");
        setText(null);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "arrow.png"));
    }
} 