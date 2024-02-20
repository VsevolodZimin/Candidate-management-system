
package view.components.frames;

import java.awt.Frame;
import static java.awt.event.ItemEvent.SELECTED;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import lombok.Getter;
import util.CurrencyService;
import util.CurrencyService.Currency;
import view.components.panels.converterPanel.ConverterPanel;
import view.components.panels.converterPanel.flagDropBox.FlagComboBox;
import view.components.panels.converterPanel.flagDropBox.ItemModel;


@Getter
public class ConverterDialogue extends javax.swing.JDialog {

    private FlagComboBox currencyList;
    private JTextField fromField;
    private JTextField toField;
    private JButton saveButton;
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
    private ConverterPanel converterPanel;
    private Currency selectedCountry;
    private float currentRate;
    private long gbpAmount;
    private boolean isSaved = false;
    
    public ConverterDialogue(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initCustomComponents();
        addListeners();
        setSelectedCountry((ItemModel)currencyList.getSelectedItem());
    }

    private void initComponents() {

        converterPanel = new view.components.panels.converterPanel.ConverterPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(converterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(converterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }

    private void initCustomComponents() {
        currencyList = converterPanel.getCurrencyPicker();
        fromField = converterPanel.getForeignCurField();
        toField = converterPanel.getGbpField();
        saveButton = converterPanel.getConvertButton();
        
        bulgaria = currencyList.getBulgaria();
        chile = currencyList.getChile();
        china = currencyList.getChina();
        colombia = currencyList.getColombia();
        eu = currencyList.getEu();
        hongKong = currencyList.getHongKong();
        israel = currencyList.getIsrael();
        mexico = currencyList.getMexico();
        poland = currencyList.getPoland();
        southKorea = currencyList.getSouthKorea();
        usa = currencyList.getUsa();
    }
    
    private void addListeners(){
        currencyList.addItemListener(e -> {
            if (e.getStateChange() == SELECTED) {
                setSelectedCountry((ItemModel)e.getItem());
                if(toField.getDocument().getLength() > 0){
                    setTextToGbpField(fromField.getText());
                }
            }
        });
        
        
        fromField.setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                Pattern pattern = Pattern.compile("^[0-9]+$");
                Matcher matcher = pattern.matcher(str);
                boolean found = matcher.find();
                
                if(found && this.getLength() < 15) {
                    if(this.getLength() == 1 && this.getText(0, this.getLength()).equals("0")){
                        super.remove(0, getLength());
                        super.insertString(0, str, a);
                    }
                    else {
                        super.insertString(offs, str, a);
                    }
                    setTextToGbpField(fromField.getText());
                }
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                if(this.getLength() != 0){
                    setTextToGbpField(fromField.getText());
                }   
                else {
                    toField.setText("");
                }
            }
        });
        
        saveButton.addActionListener(e -> {
            ConverterDialogue.this.setVisible(false);
            isSaved = true;
        });
    }
    
    
    private void setTextToGbpField(String text) {
        long insertedValue = Long.parseLong(text);
        float result = insertedValue * currentRate;
        gbpAmount = (long)result;
        toField.setText(String.valueOf((long)result));
    }
    
    
    private void setSelectedCountry(ItemModel item){
        
        switch(item.getNameTag().getText()){
            case "Bulgarian Lev" -> {
                selectedCountry = Currency.BGN;
                currentRate = CurrencyService.bulgariaRate;
            }
            case "Chilean Peso" -> {
                selectedCountry = Currency.CLP;
                currentRate = CurrencyService.chileRate;
            }
            case "Chinese Yuan" -> {
                selectedCountry = Currency.CNY;
                currentRate = CurrencyService.chinaRate;
            }
            case "Colombian Peso" -> {
                selectedCountry = Currency.COP;
                currentRate = CurrencyService.colombiaRate;
            }
            case "Euro" -> {
                selectedCountry = Currency.EUR;
                currentRate = CurrencyService.euRate;
            }
            case "Hong Kong Dollar" -> {
                selectedCountry = Currency.HKD;
                currentRate = CurrencyService.hongKongRate;
            }
            case "Israeli Shekel" -> {
                selectedCountry = Currency.ILS;
                currentRate = CurrencyService.israelRate;
            }
            case "Mexican Peso" -> {
                selectedCountry = Currency.MXN;
                currentRate = CurrencyService.mexicoRate;
            }
            case "Polish Zloty" -> {
                selectedCountry = Currency.PLN;
                currentRate = CurrencyService.polandRate;
            }
            case "Korean Won" -> {
                selectedCountry = Currency.KRW;
                currentRate = CurrencyService.koreaRate;
            }
            case "US Dollar" -> {
                selectedCountry = Currency.USD;
                currentRate = CurrencyService.usaRate;
            }


        }
    }

    void setDialogueVisible() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}