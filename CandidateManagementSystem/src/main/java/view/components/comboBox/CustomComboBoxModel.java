package view.components.comboBox;

import util.enums.BoxType;

import javax.swing.*;
import java.util.ArrayList;

public class CustomComboBoxModel extends DefaultComboBoxModel<String> {

    public CustomComboBoxModel(BoxType type) {
        addAll(setData(type));
    }

    private ArrayList<String> setData(BoxType type){
        ArrayList<String> data = new ArrayList<>();

        switch(type) {
            case COUNTRY -> {
                data.add("");
                data.add("Italy");
                data.add("Spain");
                data.add("UK");
                data.add("Bulgaria");
                data.add("Colombia");
                data.add("Mexico");
                data.add("APAC");
                data.add("France");
                data.add("Other");
            }

            case PROFILE -> {
                data.add("");
                data.add("Junior");
                data.add("Middle");
                data.add("Senior");
                data.add("Manager");
                data.add("Director");
            }

            case SOURCE -> {

                data.add("");
                data.add("LinkedIn");
                data.add("Referral");
                data.add("Headhunter");
                data.add("External agency");
                data.add("Database");
                data.add("Other");
            }

            case RESULT -> {
                data.add("");
                data.add("Hired");
                data.add("Offer Declined");
                data.add("HR Discards");
                data.add("HM Discards");
            }
        }
        return data;
    }
}