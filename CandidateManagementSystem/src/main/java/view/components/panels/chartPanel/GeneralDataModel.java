package view.components.panels.chartPanel;

import lombok.Getter;
import org.jfree.data.category.DefaultCategoryDataset;
import sevices.JDBCStatisticsService;
import util.enums.Profile;

@Getter
public class GeneralDataModel extends DefaultCategoryDataset {
    
    private final JDBCStatisticsService service;

    public GeneralDataModel(JDBCStatisticsService service) {
        this.service = service;
    }
    
    public void updateData(boolean isMean, Profile profile) {
        
        float[] centralTendencies = service.getCentralTendencies(isMean, profile);
        
        clear();
        setValue(centralTendencies[0], "1", "Phone screening");
        setValue(centralTendencies[1], "1", "HR interview");
        setValue(centralTendencies[2], "1", "Submitted to HM");
        setValue(centralTendencies[3], "1", "HM interview");
        setValue(centralTendencies[4], "1", "Test");
        setValue(centralTendencies[5], "1", "Offer letter");
        setValue(centralTendencies[6], "1", "Selection closed");
        setValue(centralTendencies[7], "1", "Whole process");
    }
}