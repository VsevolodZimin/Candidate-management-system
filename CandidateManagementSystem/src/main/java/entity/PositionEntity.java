package entity;

import util.enums.Country;
import util.enums.Profile;
import java.time.LocalDate;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PositionEntity extends Entity {

    public static final int TRUE = 1;
    public static final int FALSE = 0;
    
    private String name;
    private Country country;
    private Profile profile;
    private LocalDate openDate;
    private LocalDate closeDate;
    private long budget;
    private String additionalInformation;
    private ArrayList<CandidateEntity> candidates = new ArrayList<>();
   
    
    public PositionEntity(
            String name, 
            Country country, 
            Profile profile, 
            LocalDate openDate, 
            LocalDate closeDate, 
            long budget,
            String info,
            int forStatistics, 
            int important){
        setName(name);
        setCountry(country);
        setProfile(profile);
        setOpenDate(openDate);
        setCloseDate(closeDate);
        setBudget(budget);
        setAdditionalInformation(info);
        setForStatistics(forStatistics);
        setImportant(important); 
    }
    
        public PositionEntity(
            int id,
            String name, 
            Country country, 
            Profile profile, 
            LocalDate openDate, 
            LocalDate closeDate, 
            long budget,
            String info,
            int forStatistics, 
            int important){
        setId(id);
        setName(name);
        setCountry(country);
        setProfile(profile);
        setOpenDate(openDate);
        setCloseDate(closeDate);
        setBudget(budget);
        setAdditionalInformation(info);
        setForStatistics(forStatistics);
        setImportant(important); 
    }

    public void setCloseDate(LocalDate closeDate){
        this.closeDate = closeDate;
        if(closeDate != null) {
            setForStatistics(TRUE);
        }
        else {
            setForStatistics(FALSE);
        }
    }
}
