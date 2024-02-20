 package entity;

import util.enums.*;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateEntity extends Entity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Country country = Country.NONE;
    private Profile profile = Profile.NONE;
    private String linkToCV;
    private Source source = Source.NONE;
    private Long currentSalary;
    private Long salaryExpectations;
    private ArrayList<LabelEntity> additionalBenefits = new ArrayList<>();
    private PositionEntity position;


    /**SELECTION PROCESS**/
    private LocalDate inMail;
    private LocalDate phoneScreening;
    private LocalDate HRInterview;
    private LocalDate submittedToHM;
    private LocalDate HMInterview;
    private LocalDate test;
    private int suitableForSinclair;
    private LocalDate offerLetter;
    private LocalDate selectionClosed;
    private Result result = Result.NONE;
    private String additionalComments;
    private String selectionProcess;
    private int fromAgency = FALSE;


    public CandidateEntity() {
    }
    
    public CandidateEntity( // Constructor for saving into database (no ID, IMPORTANT, FOR STATISTICS)
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            Country country,
            Profile profile,
            String linkToCV,
            Source source,
            Long currentSalary,
            Long salaryExpectations,
            LocalDate inMail,
            LocalDate phoneScreening,
            LocalDate HRInterview,
            LocalDate submittedToHM,
            LocalDate HMInterview,
            LocalDate test,
            int suitableForSinclair,
            LocalDate offerLetter,
            LocalDate selectionClosed,
            Result result,
            String additionalComments,
            ArrayList<LabelEntity> additionalBenefits
            
    ) {

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setCountry(country);
        this.setProfile(profile);
        this.setLinkToCV(linkToCV);
        this.setSource(source);
        this.setCurrentSalary(currentSalary);
        this.setSalaryExpectations(salaryExpectations);
        this.setInMail(inMail);
        this.setPhoneScreening(phoneScreening);
        this.setHRInterview(HRInterview);
        this.setSubmittedToHM(submittedToHM);
        this.setHMInterview(HMInterview);
        this.setTest(test);
        this.setSuitableForSinclair(suitableForSinclair);
        this.setOfferLetter(offerLetter);
        this.setSelectionClosed(selectionClosed);
        this.setResult(result);
        this.setAdditionalComments(additionalComments);
        this.setAdditionalBenefits(additionalBenefits);
    }
    
    public CandidateEntity( // Constructor for GETTING a candidate from the DB
            int id,
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            Country country,
            Profile profile,
            String linkToCV,
            Source source,
            Long currentSalary,
            Long salaryExpectations,
            LocalDate inMail,
            LocalDate phoneScreening,
            LocalDate HRInterview,
            LocalDate submittedToHM,
            LocalDate HMInterview,
            LocalDate test,
            int suitableForSinclair,
            LocalDate offerLetter,
            LocalDate selectionClosed,
            Result result,
            String additionalComments,
            int forStatistics,
            int important,
            int fromAgency
   ) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setCountry(country);
        this.setProfile(profile);
        this.setLinkToCV(linkToCV);
        this.setSource(source);
        this.setCurrentSalary(currentSalary);
        this.setSalaryExpectations(salaryExpectations);
        this.setInMail(inMail);
        this.setPhoneScreening(phoneScreening);
        this.setHRInterview(HRInterview);
        this.setSubmittedToHM(submittedToHM);
        this.setHMInterview(HMInterview);
        this.setTest(test);
        this.setSuitableForSinclair(suitableForSinclair);
        this.setOfferLetter(offerLetter);
        this.setSelectionClosed(selectionClosed);
        this.setResult(result);
        this.setAdditionalComments(additionalComments);
        this.setSelectionProcess(selectionProcess);
        this.setImportant(important);
        this.setForStatistics(forStatistics);
        this.setFromAgency(fromAgency);
    }
    


    public Stage getCurrentStageFlag(){
        if (getInMail()== null) return Stage.IN_MAIL;
        if(getPhoneScreening() == null) return Stage.PHONE_SCREENING;
        if(getHRInterview() == null) return Stage.HR_INTERVIEW;
        if(getSubmittedToHM() == null) return Stage.SUBMITTED_TO_HM;
        if(getHMInterview() == null) return Stage.HM_INTERVIEW;
        if(getTest() == null) return Stage.TEST;
        if(getOfferLetter() == null) return Stage.OFFER_LETTER;
        if(getSelectionClosed() == null) return Stage.SELECTION_CLOSED;
        return Stage.NONE;
    }
    
    public LocalDate getCurrentStageDate(Stage flag) {
        LocalDate dateToReturn;
        switch(flag){
            case PHONE_SCREENING -> dateToReturn = getInMail();
            case HR_INTERVIEW -> dateToReturn = getPhoneScreening();
            case SUBMITTED_TO_HM -> dateToReturn = getHRInterview();
            case HM_INTERVIEW -> dateToReturn = getSubmittedToHM();
            case TEST -> dateToReturn = getHMInterview();
            case OFFER_LETTER -> dateToReturn = getTest();
            case SELECTION_CLOSED -> dateToReturn = getOfferLetter();
            default -> dateToReturn = LocalDate.now();
        }

    return dateToReturn;
    }
    
    final public void setResult(Result result){
        this.result = result;
        if(result != Result.NONE) {
            setForStatistics(TRUE);
        }
        else {
            setForStatistics(FALSE);
        }
    }
}