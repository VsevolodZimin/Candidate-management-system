
package view.components.panels.exportPanel;

import util.connection.PathsPropertiesManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.PopupFactory;

public class CustomPopupFactory {
    
    private static final PopupFactory pf = PopupFactory.getSharedInstance();
    private static PopupWrapper popUp;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final static NotificationPanel savedContent;
    private final static NotificationPanel loadedContent;
    private final static NotificationPanel errorMessage;
    private final static NotificationPanel duplicate;
    private CustomPopupFactory(){}
    
    
    static {
        savedContent = new NotificationPanel();
        savedContent.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "doneGreen.png"));
        savedContent.setText("A copy of the DB has been successfully saved!");
        
        loadedContent = new NotificationPanel();
        loadedContent.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "doneGreen.png"));
        loadedContent.setText("A copy of the DB has been successfully loaded!");
        
        duplicate = new NotificationPanel();
        duplicate.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "error.png"));
        duplicate.setText("Position with this name already exists");
        
        errorMessage = new NotificationPanel(true);    
        errorMessage.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "error.png"));
        
    }
    
    public static PopupWrapper getPopUp(JComponent comp, PopupType type, String text){

        switch(type){
            case SAVING_DONE -> {
                setTxt(savedContent, text);
                popUp = new PopupWrapper(pf.getPopup(comp, savedContent, (int)screenSize.getWidth() - savedContent.getW() - 50 , (int)screenSize.getHeight() - savedContent.getH() - 30), false);
                savedContent.setPopUp(popUp);
                return popUp;
            }
            case LOADING_DONE -> {
                setTxt(loadedContent, text);
                popUp = new PopupWrapper(pf.getPopup(comp, loadedContent, (int)screenSize.getWidth() - loadedContent.getW() - 50 , (int)screenSize.getHeight() - loadedContent.getH() - 30), false);
                loadedContent.setPopUp(popUp);
                return new PopupWrapper(pf.getPopup(comp, loadedContent, (int)screenSize.getWidth() - loadedContent.getW() - 50 , (int)screenSize.getHeight() - loadedContent.getH() - 30), false);
            }
            
            case DUPLICATE -> {
                setTxt(duplicate, text);
                popUp = new PopupWrapper(pf.getPopup(comp, duplicate, (int)screenSize.getWidth() - duplicate.getW() - 50 , (int)screenSize.getHeight() - duplicate.getH() - 30), false);
                duplicate.setPopUp(popUp);
                return new PopupWrapper(pf.getPopup(comp, duplicate, (int)screenSize.getWidth() - duplicate.getW() - 50 , (int)screenSize.getHeight() - duplicate.getH() - 30), false);
            }
            
            case ERROR -> {
                setTxt(errorMessage, text);
                popUp = new PopupWrapper(pf.getPopup(comp, errorMessage, (int)screenSize.getWidth() - errorMessage.getW() - 50 , (int)screenSize.getHeight() - errorMessage.getH() - 30), false);
                errorMessage.setPopUp(popUp);
                return new PopupWrapper(pf.getPopup(comp, errorMessage, (int)screenSize.getWidth() - errorMessage.getW() - 50 , (int)screenSize.getHeight() - errorMessage.getH() - 30), true);
            }
        }
        return null;
    }
    
    public enum PopupType {
        SAVING_DONE, LOADING_DONE, ERROR, DUPLICATE
    }
    
    private static void setTxt(NotificationPanel panel, String text){
        if(text != null) {
            panel.setText(text);
        }
    }
}