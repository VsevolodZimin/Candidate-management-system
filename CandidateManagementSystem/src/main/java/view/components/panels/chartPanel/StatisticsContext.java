package view.components.panels.chartPanel;

import lombok.Getter;
import lombok.Setter;
import util.enums.Profile;

@Getter
@Setter
public class StatisticsContext {
    
    private boolean isMean = true;
    private Profile candidateProfile = Profile.NONE;
    private Profile positionProfile = Profile.NONE;
}