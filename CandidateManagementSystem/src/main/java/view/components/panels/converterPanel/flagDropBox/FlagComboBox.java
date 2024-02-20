
package view.components.panels.converterPanel.flagDropBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import lombok.Getter;
import view.components.comboBox.CustomComboBox;
import util.CurrencyService;

@Getter
public class FlagComboBox extends CustomComboBox{
    
    private ItemModel bulgaria;
    private ItemModel chile;
    private ItemModel china;
    private ItemModel colombia;
    private ItemModel eu;
    private ItemModel hongKong;
    private ItemModel israel;
    private ItemModel mexico;
    private ItemModel poland;
    private ItemModel southKorea;
    private ItemModel usa;
    
    public FlagComboBox(){
        super();
        setOpaque(false);
        initComboboxItems();
        initModel();
        initRenderer();
        setBorder(BorderFactory.createLineBorder(Color.decode("#703275")));
    }
    
    private void initComboboxItems() {
        bulgaria = new ItemModel(CurrencyService.Currency.BGN);
        chile = new ItemModel(CurrencyService.Currency.CLP);
        china = new ItemModel(CurrencyService.Currency.CNY);
        colombia = new ItemModel(CurrencyService.Currency.COP);
        eu = new ItemModel(CurrencyService.Currency.EUR);
        hongKong = new ItemModel(CurrencyService.Currency.HKD);
        israel = new ItemModel(CurrencyService.Currency.ILS);
        mexico = new ItemModel(CurrencyService.Currency.MXN);
        poland = new ItemModel(CurrencyService.Currency.PLN);
        southKorea = new ItemModel(CurrencyService.Currency.KRW);
        usa = new ItemModel(CurrencyService.Currency.USD);
    }
    
    
    private void initRenderer(){
        setRenderer(new DefaultListCellRenderer(){
            
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                ItemModel item = (ItemModel)value;
                item.setBackground(isSelected ? Color.decode("#fcf1fc") : Color.white);
                return item;
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void initModel() {
        setModel(new DefaultComboBoxModel(new ItemModel[] {bulgaria, chile, china, colombia, eu, hongKong, israel, mexico, poland, southKorea, usa}));
    }
}