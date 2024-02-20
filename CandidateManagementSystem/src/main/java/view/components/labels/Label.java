package view.components.labels;

import entity.LabelEntity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import view.components.panels.additionalBenefits.LabelClickedCallback;


    public class Label extends JLabel {
        
        LabelClickedCallback event;
        
        public Label(LabelEntity entity){

            this.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    event.onLabelClicked(entity, Label.this);
                }
            });
        }   
    }