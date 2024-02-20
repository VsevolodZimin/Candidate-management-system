package view.components.panels.exportPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Popup;
import javax.swing.Timer;
import lombok.Getter;


@Getter
public class PopupWrapper {
    
    private AbstractAction action;
    private Timer timer;
    private boolean isShown;
    private final Popup popup;
    boolean isErrorMessage;
    
    public PopupWrapper(Popup popup, boolean isErrorMessage){
        this.popup = popup;
        this.isErrorMessage = isErrorMessage;
    }

    public void showPopup(){
        isShown = true;
        action = new AbstractAction(){
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i < 3) {
                    i++;
                }
                else {
                    ((Timer)e.getSource()).stop();
                    popup.hide();
                    isShown = false;
                }
            }
        };
        timer = new Timer(isErrorMessage ? 1600 : 800, action);
        popup.show();
        timer.start();
    }
}