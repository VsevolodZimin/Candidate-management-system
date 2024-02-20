package view.components.comboBox;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBoxUI extends BasicComboBoxUI {

    @Override
    protected JButton createArrowButton() {
        ArrowButton arrow = new ArrowButton();
        arrow.setContentAreaFilled(false);
        arrow.setBorder(BorderFactory.createEmptyBorder());
        arrow.setText("");
        return arrow;
    }

    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup p = new BasicComboPopup(comboBox);
        p.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        p.getList().setBackground(Color.WHITE);
        return p;
    }
}