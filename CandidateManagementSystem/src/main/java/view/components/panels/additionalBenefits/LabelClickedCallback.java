package view.components.panels.additionalBenefits;

import entity.LabelEntity;
import view.components.labels.Label;


public interface LabelClickedCallback {
    void onLabelClicked(LabelEntity le, Label l);
}