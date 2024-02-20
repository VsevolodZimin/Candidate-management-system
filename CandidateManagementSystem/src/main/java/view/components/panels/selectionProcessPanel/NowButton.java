package view.components.panels.selectionProcessPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JTextField;
import lombok.Setter;
import util.Utils;


@Setter
public class NowButton extends JButton {

    private JTextField field;
    
    public NowButton() {
        this.setText("Today");
        addListeners();
    }
    
    final public void addListeners(){
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked (MouseEvent e) {
                if(field.isEditable()) {
                    String day = String.valueOf(LocalDate.now().getDayOfMonth());
                    String month = String.valueOf(LocalDate.now().getMonthValue());
                    String year = String.valueOf(LocalDate.now().getYear());
                    
                    day = Utils.addPlaceHolder(day);
                    month = Utils.addPlaceHolder(month);
                    year = Utils.addPlaceHolder(year);
                    field.requestFocusInWindow();
                    field.setText(day + "." + month + "." + year);
                }
            }
        });
    }
}
