
package view.components.panels.converterPanel.flagDropBox;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import lombok.Getter;
import util.connection.PathsPropertiesManager;
import util.CurrencyService.Currency;
import javax.swing.*;

@Getter
public class ItemModel extends JPanel {

    private JLabel flagTag;
    private JLabel nameTag;

    public ItemModel(Currency country) {
        setOpaque(true);
        setBackground(Color.red);
        initComponents();
        initCustomComponents(country);
    }
    private void initComponents() {

        flagTag = new JLabel();
        nameTag = new JLabel();
        nameTag.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        nameTag.setText("Chilean Peso");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(flagTag)
                .addGap(18, 18, 18)
                .addComponent(nameTag, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(flagTag, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addComponent(nameTag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }


    private void InitItem(Currency country) {
        switch (country) {
            case BGN -> {        
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "bulgaria.png"));
                nameTag.setText("Bulgarian Lev");
            }
            case CLP -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "chile.png"));
                nameTag.setText("Chilean Peso");
            }
            case CNY -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "china.png"));
                nameTag.setText("Chinese Yuan");
            }
            case COP -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "colombia.png"));
                nameTag.setText("Colombian Peso");
            }
            case EUR -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "eu.png"));
                nameTag.setText("Euro");
            }
            case HKD -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "hong_kong.png"));                nameTag.setText("Hong Kong Dollar");
            }
            case ILS -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "israel.png"));
                nameTag.setText("Israeli Shekel");
            }
            case MXN -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "mexico.png"));
                nameTag.setText("Mexican Peso");
            }
            case PLN -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "poland.png"));
                nameTag.setText("Polish Zloty");
            }
            case KRW -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "south_korea.png"));
                nameTag.setText("Korean Won");
            }
            case USD -> {
                flagTag.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.flagIconDir + "usa.png"));
                nameTag.setText("US Dollar");
            }
        }
    }
    
    private void initCustomComponents(Currency country){
        InitItem(country);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.white);
            }
            
        });
    }
}