package view.components.comboBox;

import entity.PositionEntity;
import java.awt.Graphics;
import sevices.JDBCPositionService;
import javax.swing.*;


public class PositionComboBox extends CustomComboBox {
    DefaultComboBoxModel<String> boxModel;
    JDBCPositionService service;

    public PositionComboBox(JDBCPositionService service) {
        super(new DefaultComboBoxModel<>());
            this.boxModel = (DefaultComboBoxModel<String>) this.getModel();
            this.service = service;
            updateItems();
    }

    final public void updateItems() {
        if (boxModel.getSize() != 0) {
            boxModel.removeAllElements();
        }
        boxModel.addElement("");
        for (PositionEntity position : service.findAll()) {
            boxModel.addElement(position.getName());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}