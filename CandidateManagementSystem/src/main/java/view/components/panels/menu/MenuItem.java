
package view.components.panels.menu;


import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MenuItem extends JPanel {

    private JLabel lbIcon;
    private JLabel lbName;
    private final MenuItemModel item;
    
    public MenuItem(MenuItemModel item) {
        this.item = item;
        setOpaque(false);
        initComponents();
    }
    
    public void setSelected(boolean isSelected){
        if(isSelected){
            lbIcon.setIcon(item.toIcon(false));
            lbName.setText(item.getName());
            lbName.setForeground(Color.decode("#783EFF"));
        }
        else{
            lbIcon.setIcon(item.toIcon(true));
            lbName.setText(item.getName());
            lbName.setForeground(Color.decode("#FFFFFFF"));
        }
    }
    private void initComponents() {

        lbIcon = new JLabel();
        lbName = new JLabel();

        lbName.setFont(new Font("Segoe UI", 0, 18));
        lbName.setText("text");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbIcon)
                .addGap(28, 28, 28)
                .addComponent(lbName)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");
    }
}