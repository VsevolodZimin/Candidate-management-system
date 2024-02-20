package view.components.frames;

import controller.Context;
import static entity.Entity.FALSE;
import static entity.Entity.TRUE;
import entity.LabelEntity;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import lombok.Getter;
import lombok.Setter;
import sevices.JDBCCandidateService;
import sevices.JDBCLabelService;
import util.connection.PathsPropertiesManager;
import view.RoundedTextField;
import view.components.panels.additionalBenefits.AdditionalBenefitsPanel;
import view.components.panels.additionalBenefits.ItemArea;
import view.components.panels.additionalBenefits.BenefitItem;

@Getter
@Setter
public class AdditionalBenefitsDialogue extends JDialog {

    private CustomErrorDialogue errorDialogue;
    private CustomOptionDialogue optionDialogue;
    private AdditionalBenefitsPanel additionalBenefitsPanel;
    private ArrayList<LabelEntity> currentToPickLabels;
    private ArrayList<LabelEntity> currentPickedLabels;
    private ArrayList<LabelEntity> allLabels;
    private final ArrayList<BenefitItem> currentToPickItems = new ArrayList<>();
    private final ArrayList<BenefitItem> currentPickedItems = new ArrayList<>();
    private JDBCLabelService labelService;
    private JDBCCandidateService candidateService;
    private RoundedTextField existingField;
    private JTextField newField;
    private JButton saveButton;
    private ItemArea toPickBox;
    private ItemArea pickedBox;
    
    public AdditionalBenefitsDialogue(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        pack();
    }

    private void initComponents() {
        additionalBenefitsPanel = new view.components.panels.additionalBenefits.AdditionalBenefitsPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(additionalBenefitsPanel, java.awt.BorderLayout.CENTER);
        pack();
    }

    public final void initialize(ArrayList<LabelEntity> pickedLabels, JDBCLabelService labelService) {
        this.labelService = labelService;
        currentPickedLabels = pickedLabels;
        errorDialogue = new CustomErrorDialogue(Context.getParentFrame(), true);
        optionDialogue = new CustomOptionDialogue(Context.getParentFrame(), true);
        allLabels = new ArrayList<>(labelService.findAll());
        (currentToPickLabels = new ArrayList<>(allLabels)).removeAll(currentPickedLabels);
        existingField = additionalBenefitsPanel.getExistingField();
        newField = additionalBenefitsPanel.getNewField();
        toPickBox = additionalBenefitsPanel.getToPickBox().getItemArea();
        pickedBox = additionalBenefitsPanel.getPickedBox().getItemArea();
        saveButton = additionalBenefitsPanel.getSaveButton();
        
        for(LabelEntity labelEntity : currentToPickLabels){
            labelEntity.setIsPicked(FALSE);
            BenefitItem item = new BenefitItem(labelEntity);
            item.setBackground(getBackground());
            toPickBox.add(item);
            currentToPickItems.add(item);
            addItemListeners(item, labelService);
        }
        
        for(LabelEntity labelEntity : currentPickedLabels){
            labelEntity.setIsPicked(TRUE);
            BenefitItem item = new BenefitItem(labelEntity);
            item.setBackground(getBackground());
            pickedBox.add(item);
            currentPickedItems.add(item);
            addItemListeners(item, labelService);
        }
        addPanelListeners();
    }
    
    public final void createNewItem(LabelEntity labelEntity, JDBCLabelService service) {
        labelEntity.setIsPicked(TRUE);
        BenefitItem item = new BenefitItem(labelEntity);
        item.setBackground(getBackground());
        addItemListeners(item, service);
        pickedBox.add(item);
        currentPickedLabels.add(labelEntity);
        currentPickedItems.add(item);
        revalidate();
        repaint();
    }
    
    public void addItemListeners(BenefitItem item, JDBCLabelService labelService){
        LabelEntity labelEntity = item.getLabel();
        item.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){
                if(labelEntity.getIsPicked() == FALSE) {
                    selectItem(item, labelEntity);
                    existingField.requestFocusInWindow();
                }
                else {
                    deselectItem(item, labelEntity);
                    existingField.requestFocusInWindow();
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent event){
                getBox(item).setItemPosition(0);
                item.setFocused(true);
                updateFocusPaint();
                revalidate();
                repaint();
            }
            
            @Override
            public void mouseExited (MouseEvent event){
                item.setFocused(false);
                revalidate();
                repaint();
            }
        });
        
        
        JLabel deleteLabel = item.getFace().getDeleteLabel();
        deleteLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event){
                updateFocusPaint();
                deleteLabel.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "delete.png"));
            }
            
            @Override
            public void mouseExited (MouseEvent event){
                updateFocusPaint();
                deleteLabel.setIcon(null);
            }
            
            @Override
            public void mouseClicked(MouseEvent event){
                optionDialogue.showDialogue();
                int reply = optionDialogue.getResult();
                if(reply == 1){
                    labelService.delete(labelEntity);
                    if(labelEntity.getIsPicked() == TRUE) {
                        currentPickedItems.remove(item);
                        currentPickedLabels.remove(labelEntity);
                    }
                    else if(labelEntity.getIsPicked() == FALSE){
                        currentToPickItems.remove(item);
                        currentToPickLabels.remove(labelEntity);
                    }
                    getBox(item).resetItemsPosition();
                    getBox(item).remove(item);
                    revalidate();
                    repaint();
                }
                optionDialogue.dispose();
                optionDialogue.setResult(0);
            }
        });

        JLabel editLabel = item.getFace().getEditLabel();
        editLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event){
                updateFocusPaint();
                editLabel.setIcon(new ImageIcon(PathsPropertiesManager.absolutePath + PathsPropertiesManager.iconDir + "edit.png"));
            }
            
            @Override
            public void mouseExited (MouseEvent event){
                updateFocusPaint();
                editLabel.setIcon(null);
            }
            
            @Override
            public void mouseClicked(MouseEvent event){
                item.activateEdit(item.getFace().getNameLabel().getText());
            }
        });
        
        
        JTextField updateField = item.getUpdateField();
        updateField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        updateField.getActionMap().put("enter", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                    getBox(item).requestFocusInWindow();
            }            
        });
        
        
        updateField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "escape");
        updateField.getActionMap().put("escape", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                    existingField.requestFocusInWindow();
            }            
        });
        
        updateField.addFocusListener(new FocusListener(){

            LabelEntity label;

            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                existingField.requestFocusInWindow();
                label = item.deactivateEdit(toPickBox, pickedBox);
                labelService.update(label);
                revalidate();
                repaint();
            }
        });
    }
    
    private void addPanelListeners() {
        addListenersToPickBox(toPickBox);
        addListenersToPickBox(pickedBox);
        
        
        newField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        newField.getActionMap().put("enter", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldText = newField.getText();
                if(checkItemListForMatch(fieldText)){
                    errorDialogue = new CustomErrorDialogue(false, "Label with this name already exists", Context.getParentFrame(), true);
                    errorDialogue.showDialogue();

                }
                else if(fieldText.isEmpty()){
                    errorDialogue = new CustomErrorDialogue(false, "You can't create an empty label", Context.getParentFrame(), true);
                    errorDialogue.showDialogue();
                }
                else {
                    LabelEntity le = new LabelEntity(newField.getText());
                    labelService.create(le);
                    createNewItem(le, labelService);
                    newField.setText("");
                    existingField.requestFocusInWindow();
                }
            }            
        });
        
        newField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    String fieldText = e.getDocument().getText(0, e.getDocument().getLength());
                    if(checkItemListForMatch(fieldText)){
                        newField.setForeground(Color.RED);
                    }
                    else{
                        newField.setForeground(Color.BLACK);
                    }

                } 
                catch (BadLocationException ex) {
                    new CustomErrorDialogue(true, "Character sequence error", Context.getParentFrame(), true).showDialogue();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    String fieldText = e.getDocument().getText(0, e.getDocument().getLength());
                    if(checkItemListForMatch(fieldText)){
                        newField.setForeground(Color.RED);
                    }
                    else{
                        newField.setForeground(Color.BLACK);
                    }
                } 
                catch (BadLocationException ex) {
                    new CustomErrorDialogue(true, "Character sequence error", Context.getParentFrame(), true).showDialogue();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        newField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                existingField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        existingField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        existingField.getActionMap().put("enter", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                BenefitItem item; 
                LabelEntity labelEntity;
                String fieldText = existingField.getText();
                updateFocusPaint();
                for(Component comp : toPickBox.getComponents()){
                    item = (BenefitItem)comp;
                    labelEntity = item.getLabel();
                    String labelText = item.getFace().getNameLabel().getText();
                    if(fieldText.equalsIgnoreCase(labelText)){
                        selectItem(item, labelEntity);
                    }
                }
                existingField.setText("");
                revalidate();
                repaint();
            }
        });

        
        
        existingField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateListOfItemsInBox(e.getDocument().getText(0, e.getDocument().getLength()));
                }
                catch (BadLocationException ex) {
                    new CustomErrorDialogue(true, "Character sequence error", Context.getParentFrame(), true).showDialogue();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateListOfItemsInBox(e.getDocument().getText(0, e.getDocument().getLength()));
                }
                catch (BadLocationException ex) {
                    new CustomErrorDialogue(true, "Character sequence error", Context.getParentFrame(), true).showDialogue();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        
        
        existingField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                newField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        saveButton.addActionListener((ActionEvent e) -> this.setVisible(false));
        
        saveButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        saveButton.getActionMap().put("enter", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
    
    private void updateListOfItemsInBox(String fieldText) {
        for(BenefitItem item1 : currentToPickItems){
            if(item1.getFace().getNameLabel().getText().toLowerCase().contains(fieldText.toLowerCase())){
                boolean present = false;
                BenefitItem item2;
                for(Component comp : toPickBox.getComponents()){
                    item2 = (BenefitItem)comp;
                    if(item2 == item1){
                        present = true;
                    }
                }
                if(!present){
                    toPickBox.add(item1);
                }
            }
            else if(!item1.getFace().getNameLabel().getText().toLowerCase().contains(fieldText.toLowerCase())){
                toPickBox.remove(item1);
            }
        }
        revalidate();
        repaint();
    }
    
    
    private void addListenersToPickBox(ItemArea box){
        box.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                box.requestFocusInWindow();
            }
        });
    }
        
    private void selectItem(BenefitItem item, LabelEntity label){
        toPickBox.remove(item);
        currentToPickItems.remove(item);
        currentToPickLabels.remove(label);
        pickedBox.add(item);
        currentPickedItems.add(item);
        currentPickedLabels.add(label);
        label.setIsPicked(TRUE);
        additionalBenefitsPanel.revalidate();
        additionalBenefitsPanel.repaint(); 
    }
    
    private void deselectItem(BenefitItem item, LabelEntity label){
        pickedBox.remove(item);
        currentPickedItems.remove(item);
        currentPickedLabels.remove(label);
        toPickBox.add(item);
        currentToPickItems.add(item);
        currentToPickLabels.add(label);
        label.setIsPicked(FALSE);
        additionalBenefitsPanel.revalidate();
        additionalBenefitsPanel.repaint();
    }
    
    private void updateFocusPaint(){
        for(Component c : toPickBox.getComponents()){
            BenefitItem item = (BenefitItem)c;
            item.getFace().getDeleteLabel().setIcon(null);
            item.getFace().getEditLabel().setIcon(null);
        }
        
        for(Component c : pickedBox.getComponents()){
            BenefitItem item = (BenefitItem)c;
            item.getFace().getDeleteLabel().setIcon(null);
            item.getFace().getEditLabel().setIcon(null);
        }
    }
    
    private boolean checkItemListForMatch(String fieldText) {
        for(BenefitItem item : currentPickedItems) {
            if(fieldText.equalsIgnoreCase(item.getFace().getNameLabel().getText())) {
                return true;
            }
        }
        for(BenefitItem item : currentToPickItems) {
            if(fieldText.equalsIgnoreCase(item.getFace().getNameLabel().getText())) {
                return true;
            }
        }
        return false;
    }
    
    void setDialogueVisible() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private ItemArea getBox(BenefitItem item){
        return (ItemArea)item.getParent();
    }
}