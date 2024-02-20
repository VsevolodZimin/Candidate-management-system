package view.components.panels.chartPanel;

import lombok.Getter;
import org.jfree.data.category.DefaultCategoryDataset;
import sevices.JDBCStatisticsService;
import util.enums.Profile;
import util.enums.Stage;

@Getter
public class DetailDataModel extends DefaultCategoryDataset {
    
    private final JDBCStatisticsService service;
    private final StatisticsContext context;
    
    public DetailDataModel(JDBCStatisticsService service, StatisticsContext context) {
        this.service = service;
        this.context = context;
    }
    
    public void updateCandidateData(Stage stage, Profile profile) {
        int candidateCount;
        String beforeDate = "";
        String afterDate = "";

        clear();

        switch(stage){
            case PHONE_SCREENING -> {
                beforeDate = "in_mail";
                afterDate = "phone_screening";
            }
            case HR_INTERVIEW -> {
                beforeDate = "phone_screening"; 
                afterDate = "hr_interview";
            }
            case SUBMITTED_TO_HM -> {
                beforeDate = "hr_interview"; 
                afterDate = "submit_to_hm";
            }
            case HM_INTERVIEW -> {
                beforeDate = "submit_to_hm"; 
                afterDate = "hm_interview";
            }
            case TEST -> {
                beforeDate = "hm_interview"; 
                afterDate = "test";
            }
            case OFFER_LETTER -> {
                beforeDate = "test"; 
                afterDate = "offer_letter";
            }
            case SELECTION_CLOSED -> {
                beforeDate = "offer_letter"; 
                afterDate = "selection_closed";
            }
            case NONE -> {
                beforeDate = "in_mail"; 
                afterDate = "selection_closed";
            }
        }
        if(stage == Stage.NONE) {
            for(int i = 0; i <= 125; i = i + 5) {
                candidateCount = countForAllStages(true, beforeDate, afterDate, profile, i, i + 5);
                setValue(candidateCount, "% of candidates", i + " days");
                if(i == 125) {
                   candidateCount = countForAllStages(false, beforeDate, afterDate, profile, i, i + 5);
                   setValue(candidateCount, "% of candidates", "< " + i + " days");
                }
            }
        }
        else {
            for(int i = 0; i <= 16; i++) {
                candidateCount = countForOneStage(true, beforeDate, afterDate, i, profile);
                if(i == 1) {
                    setValue(candidateCount, "% of candidates", i + " day");
                }
                else {
                    setValue(candidateCount, "% of candidates", i + " days");
                }
                if(i == 16) {
                    candidateCount = countForOneStage(false, beforeDate, afterDate, i, profile);
                    setValue(candidateCount, "% of candidates", "<" + i + " days");
                }
            }
        }
    }

    public void updatePositionData(Profile profile) {
        int positionCount;
        
        clear();
        
        for (int i = 0; i <= 125; i = i + 5) {
            positionCount = service.countPositions(true, i, i + 5, profile);
            setValue(positionCount, "number of positions", i + " days");

            if (i == 125) {
                positionCount = service.countPositions(false, i, i + 5, profile);
                setValue(positionCount, "number of positions", "< " + i + " days");
            }
        }
    }

    private int countForAllStages(boolean isWithinRange, String beforeDate, String afterDate, Profile profile, int low, int high) {
        return service.countCandidates(isWithinRange, beforeDate, afterDate, low, high, profile);
    }
    
    private int countForOneStage(boolean isWithinRange, String beforeDate, String afterDate, int day, Profile profile) {
        return service.countCandidatesPerStage(isWithinRange, beforeDate, afterDate, day, profile);
    }
}