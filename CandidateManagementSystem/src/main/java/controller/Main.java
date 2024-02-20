package controller;
import java.awt.Font;
import repository.SQLite.SQLiteCandidateDAOImpl;
import sevices.serviceImpl.SQLiteCandidateServiceImpl;
import view.components.frames.MainFrame;
import repository.SQLite.SQLiteLabelDAOImpl;
import repository.SQLite.SQLitePositionDAOImpl;
import repository.SQLite.SQLiteStatisticsDAOImpl;
import sevices.serviceImpl.SQLiteLabelServiceImpl;
import sevices.serviceImpl.SQLitePositionServiceImpl;
import sevices.serviceImpl.SQLiteStatisticsServiceImpl;

import javax.swing.UIManager;
import util.CurrencyService;


public class Main {
    
    public static final Font MY_FONT = new Font(Font.DIALOG, Font.PLAIN, 16);
    public static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
    
    public static void main(String[] args) {

        UIManager.put("Button.font", BUTTON_FONT);
        UIManager.put("ToggleButton.font", BUTTON_FONT);
        UIManager.put("RadioButton.font", MY_FONT);
        UIManager.put("ComboBox.font", MY_FONT);
        UIManager.put("CheckBox.font", BUTTON_FONT);
        UIManager.put("Table.font", MY_FONT);
        UIManager.put("TableHeader.font", MY_FONT);
        UIManager.put("TextField.font", MY_FONT);
        UIManager.put("List.font", MY_FONT);
        UIManager.put("Label.font", MY_FONT);
        UIManager.put("OptionPane.messageFont", MY_FONT);
        UIManager.put("OptionPane.buttonFont", MY_FONT);
        UIManager.put("Panel.font", MY_FONT);
        UIManager.put("TextArea.font", MY_FONT);
        UIManager.put("TextPane.font", MY_FONT);

        
        SQLiteCandidateServiceImpl candidateService = new SQLiteCandidateServiceImpl(new SQLiteCandidateDAOImpl(), new SQLitePositionServiceImpl(new SQLitePositionDAOImpl()));
        SQLitePositionServiceImpl positionService = new SQLitePositionServiceImpl(new SQLitePositionDAOImpl());
        SQLiteLabelServiceImpl labelService = new SQLiteLabelServiceImpl(new SQLiteLabelDAOImpl());
        SQLiteStatisticsServiceImpl statisticsService = new SQLiteStatisticsServiceImpl(new SQLiteStatisticsDAOImpl()); 
        CurrencyService.init();
        new MainFrame(candidateService, positionService, labelService, statisticsService);
    }
}