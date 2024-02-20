
package view.components.panels.exportPanel;

import controller.Context;
import util.connection.PathsPropertiesManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import view.components.frames.CustomErrorDialogue;
import view.components.frames.CustomOptionDialogue;
import view.components.panels.exportPanel.CustomPopupFactory.PopupType;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
public class ExportPanel extends JPanel {


    private JLabel fieldTag;
    private JButton loadDumpButton;
    private JLabel loadTag;
    private JButton locationButton;
    private JLabel locationTag;
    private view.RoundedTextField pathField;
    private JButton saveDumpButton;
    private JLabel saveTag;
    private JFileChooser pathChooser;
    private JFileChooser fileChooser;
    private FileFilter filter;
    

    public ExportPanel() {
        initComponents();
        initFileChooser();
        initCustomComponents();
        addPathButtonListener();
        addSaveButtonListener();
        addLoadButtonListener();
    }
    
    private void addPathButtonListener(){
        locationButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pathChooser.setCurrentDirectory(new File(PathsPropertiesManager.getPath("dump.location")));
                pathChooser.showDialog(ExportPanel.this, "Select");
                PathsPropertiesManager.writeToProperty("dump.location", pathChooser.getSelectedFile());
                setPathText(PathsPropertiesManager.getPath("dump.location"));
            }

            private void setPathText(String path) {
                if(path != null) {
                    pathField.setText(path);
                }
            }
        });
    }

    private void addSaveButtonListener(){
        saveDumpButton.addActionListener(new ActionListener(){
            PopupWrapper savedPopup;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savedPopup = CustomPopupFactory.getPopUp(ExportPanel.this, PopupType.SAVING_DONE, null);
                    PathsPropertiesManager.createCopy();
                    if (!savedPopup.isShown()) {
                        savedPopup.showPopup();
                    }
                }
                catch (IOException ex) {
                    new CustomErrorDialogue(true, "Failed to create a copy", Context.getParentFrame(), true).showDialogue();
                }
            }
        });
    }

    private void addLoadButtonListener(){
        loadDumpButton.addActionListener(new ActionListener(){
            PopupWrapper loadedPopup;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadedPopup = CustomPopupFactory.getPopUp(ExportPanel.this, PopupType.LOADING_DONE, null);
                    CustomOptionDialogue dialogue = new CustomOptionDialogue("Before loading backup, would you like to save current database?", Context.getParentFrame(), true);
                    dialogue.showDialogue();
                    int reply = dialogue.getResult();
                    if (reply == 1) {
                        PathsPropertiesManager.createCopy();
                    }
                    int result = fileChooser.showDialog(ExportPanel.this, "Load");
                    PathsPropertiesManager.loadCopy(fileChooser.getSelectedFile());
                    Context.getPositionPanel()
                            .getHeaderPanel()
                            .getWarningLabel()
                            .updateLabelForPosition();
                    if (result == JFileChooser.APPROVE_OPTION && !loadedPopup.isShown()) {
                        loadedPopup.showPopup();
                    }
                }
                catch (IOException ex) {
                    new CustomErrorDialogue(true, "Failed to load the copy", Context.getParentFrame(), true).showDialogue();
                }
            }
        });
    }

    private void initFileChooser(){
            pathChooser = new JFileChooser();
            pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            pathChooser.setAcceptAllFileFilterUsed(false);

            filter = new FileNameExtensionFilter(null, "sqlite");

            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(PathsPropertiesManager.getPath("dump.location")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(filter);
    }

    private void initComponents() {

        saveDumpButton = new JButton();
        loadDumpButton = new JButton();
        locationButton = new JButton();
        locationTag = new JLabel();
        loadTag = new JLabel();
        saveTag = new JLabel();
        fieldTag = new JLabel();
        pathField = new view.RoundedTextField();

        saveDumpButton.setText("Save");
        saveDumpButton.setFocusPainted(false);
        saveDumpButton.setSelected(true);

        loadDumpButton.setText("Load");
        loadDumpButton.setFocusPainted(false);

        locationButton.setText("Search");
        locationButton.setFocusPainted(false);

        locationTag.setFont(new Font("Dialog", Font.PLAIN, 18));
        locationTag.setText("Set the location to store backups");

        loadTag.setFont(new Font("Dialog", Font.PLAIN, 18));
        loadTag.setText("Recover your data from an earlier backup");

        saveTag.setFont(new Font("Dialog", Font.PLAIN, 18));
        saveTag.setText("Create a dump (backup) of the current database");

        fieldTag.setFont(new Font("Dialog", Font.PLAIN, 18));
        fieldTag.setText("Current path: ");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldTag)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(locationTag)
                            .addComponent(saveTag)
                            .addComponent(loadTag))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(locationButton)
                            .addComponent(loadDumpButton)
                            .addComponent(saveDumpButton)))
                    .addComponent(pathField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locationButton)
                    .addComponent(locationTag))
                .addGap(30, 30, 30)
                .addComponent(fieldTag)
                .addGap(18, 18, 18)
                .addComponent(pathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveDumpButton)
                    .addComponent(saveTag))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loadDumpButton)
                    .addComponent(loadTag))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void initCustomComponents() {
        setBackground(Color.decode("#faf2f9"));
            pathField.setText(PathsPropertiesManager.getPath("dump.location"));
    }
}