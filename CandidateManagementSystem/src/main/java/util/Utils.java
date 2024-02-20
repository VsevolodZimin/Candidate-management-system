package util;

import entity.CandidateEntity;
import entity.PositionEntity;
import util.enums.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import view.components.panels.DateField;

public final class Utils {

    private Utils() {
    }

    public static Country getCountryFromString(String str) {
        Country c;

        if(str != null) {

            switch (str) {
                case "Italy" -> c = Country.ITALY;
                case "Spain" -> c = Country.SPAIN;
                case "UK" -> c = Country.UK;
                case "Bulgaria" -> c = Country.BULGARIA;
                case "Colombia" -> c = Country.COLOMBIA;
                case "Mexico" -> c = Country.MEXICO;
                case "APAC" -> c = Country.APAC;
                case "France" -> c = Country.FRANCE;
                case "Other" -> c = Country.OTHER;
                default -> c = Country.NONE;
            }
            return c;
        }
        else {
            return Country.NONE;
        }
    }

    public static String getStringFromCountry(Country country) {
        String c = "";
        switch (country) {
            case ITALY -> c = "Italy";
            case SPAIN -> c = "Spain";
            case UK -> c = "UK";
            case BULGARIA -> c = "Bulgaria";
            case COLOMBIA -> c = "Colombia";
            case MEXICO -> c = "Mexico";
            case APAC -> c = "APAC";
            case FRANCE -> c = "France";
            case OTHER -> c = "Other";
            case NONE -> c = "";
        }
        return c;
    }

    public static Profile getProfileFromString(String str) {
        Profile p;
        if(str != null) {
            switch (str) {
                case "Junior" -> p = Profile.JUNIOR;
                case "Middle" -> p = Profile.MIDDLE;
                case "Senior" -> p = Profile.SENIOR;
                case "Manager" -> p = Profile.MANAGER;
                case "Director" -> p = Profile.DIRECTOR;
                default -> p = Profile.NONE;
            }
            return p;
        }
        else return Profile.NONE;
    }

    public static String getStringFromProfile(Profile profile) {
        String p = "";
        switch (profile) {
            case JUNIOR -> p = "Junior";
            case MIDDLE -> p = "Middle";
            case SENIOR -> p = "Senior";
            case MANAGER -> p = "Manager";
            case DIRECTOR -> p = "Director";
            case NONE -> p = "";
        }
        return p;
    }

    public static String getStringFromSource(Source source) {
        String s = "";
        switch (source) {
            case DATABASE -> s = "Database";
            case REFERRAL -> s = "Referral";
            case HEADHUNTER -> s = "Headhunter";
            case EXTERNAL_AGENCY -> s = "External Agency";
            case LINKEDIN -> s = "LinkedIn";
            case OTHERS -> s = "Other";
            case NONE -> s = "";
        }
        return s;
    }

    public static Source getSourceFromString(String str) {
        if(str != null) {
            Source s;
            switch (str) {
                case "Database" -> s = Source.DATABASE;
                case "Referral" -> s = Source.REFERRAL;
                case "Headhunter" -> s = Source.HEADHUNTER;
                case "External Agency" -> s = Source.EXTERNAL_AGENCY;
                case "LinkedIn" -> s = Source.LINKEDIN;
                case "Other" -> s = Source.OTHERS;
                default -> s = Source.NONE;
            }
            return s;
        }
        return Source.NONE;
    }


    public static Result getResultFromString(String str) {
        Result r;
        if(str != null) {
            switch (str) {
                case "Hired" -> r = Result.HIRED;
                case "Offer Declined" -> r = Result.OFFER_DECLINED;
                case "HR Discards" -> r = Result.HR_DISCARDS;
                case "HM Discards" -> r = Result.HM_DISCARDS;
                default -> r = Result.NONE;
            }
            return r;
        }
        return Result.NONE;
    }

    public static String getStringFromResult(Result result) {
        String r = "";
        switch (result) {
            case HIRED -> r = "Hired";
            case OFFER_DECLINED -> r = "Offer Declined";
            case HR_DISCARDS -> r = "HR Discards";
            case HM_DISCARDS -> r = "HM Discards";
            case NONE -> r = "";
        }
        return r;
    }

    public static String getStringFromStage (Stage stage) {
        String r = "";
        switch (stage) {
            case PHONE_SCREENING -> r = "Phone screening";
            case HR_INTERVIEW -> r = "HR interview";
            case SUBMITTED_TO_HM -> r = "Submitted to HM";
            case HM_INTERVIEW -> r = "HM interview";
            case TEST -> r = "Test";
            case OFFER_LETTER -> r = "Offer letter";
            case SELECTION_CLOSED -> r = "Selection closed";
            case NONE -> r = "Whole process";
        }
        return r;
    }

    
    public static String formatToEU(String dbString){
        StringBuilder euString = new StringBuilder();
        if(dbString != null) {
            euString.append(dbString.substring(8));
            euString.append(".");
            euString.append(dbString, 5, 7);
            euString.append(".");
            euString.append(dbString, 0, 4);
            return euString.toString();
        }
        return null;
    }
    
    public static String getDBStringFromDate(LocalDate date) {
        if (date == null) {
            return null;
        } 
        else {
            StringBuilder dateStr = new StringBuilder();
            dateStr.append(date.getYear());
            dateStr.append("-");
            dateStr.append(addPlaceHolder(String.valueOf(date.getMonthValue())));
            dateStr.append("-");
            dateStr.append(addPlaceHolder(String.valueOf(date.getDayOfMonth())));
            
            return dateStr.toString();
        }
    }

    public static LocalDate getDateFromString (String dateString){

        if(dateString != null) {
            int day = -1;
            int month = -1;
            int year = -1;
            int maxDays;
            
            Pattern euPattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[0-2]).(19|20)\\d\\d$");
            Pattern usPattern = Pattern.compile("^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");            
            
            Matcher euMatcher = euPattern.matcher(dateString);
            Matcher usMatcher = usPattern.matcher(dateString);
            
            boolean isEU = euMatcher.find();
            boolean isUS = usMatcher.find();
            
            if (isEU) {
                day = Integer.parseInt(dateString.substring(0, 2));
                month = Integer.parseInt(dateString.substring(3, 5));
                year = Integer.parseInt(dateString.substring(6));
            }
            else if (isUS) {
                day = Integer.parseInt(dateString.substring( 8));
                month = Integer.parseInt(dateString.substring(5, 7));
                year = Integer.parseInt(dateString.substring(0,4));
            }
            
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> maxDays = 31;
                case 2 -> maxDays = year % 4 == 0 ? 29 : 28;
                case 4, 6, 9, 11 -> maxDays = 30;
                default -> maxDays = -1;
            }
            if (maxDays != -1 && day <= maxDays) {
                return LocalDate.of(year, month, day);
            }
        }
        return null;
    }

    public static boolean checkDateForValidity(DateField field, LocalDate thisDate, Map<Stage, LocalDate> mapOfDates){
        if(thisDate == null) {
            return false;
        }
        else {
            LocalDate dateFromMap;
            boolean isValid = true;
            int thisStage;
            int stageFromMap;

            for(Map.Entry<Stage,LocalDate> entry: mapOfDates.entrySet()){
                if(entry.getValue() != null){
                    thisStage = field.getStage().ordinal();
                    stageFromMap = entry.getKey().ordinal();
                    dateFromMap = entry.getValue();

                    if(thisStage > stageFromMap && thisDate.isBefore(dateFromMap)) { // for example, PHONE SCREENING always happens after HR INTERVIEW, so PHONE SCREENING date can't be earlier than HR INTERVIEW date
                        isValid = false;
                    }
                    else if(thisStage < stageFromMap && thisDate.isAfter(dateFromMap)){ // for example, HR INTERVIEW always happens after PHONE SCREENING, so HR INTERVIEW date can't be earlier than PHONE SCREENING date
                        isValid = false;
                    }
                }
            }
            return isValid;
        }
    }

    public static <E> String formatToString(E e) {
        if (e != null) {
            String str = e.toString();
            Pattern pattern = Pattern.compile("[áéíóú]");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                char[] charArray = str.toCharArray();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < charArray.length; i++) {
                    switch (charArray[i]) {
                        case 'é' -> charArray[i] = 'e';
                        case 'í' -> charArray[i] = 'i';
                        case 'ó' -> charArray[i] = 'o';
                        case 'ú' -> charArray[i] = 'u';
                        case 'á' -> charArray[i] = 'a';
                    }
                    sb.append(charArray[i]);
                }
                str = sb.toString().toLowerCase();
            }
            return str.toLowerCase();
        } else return "";
    }




    public static CandidateEntity buildCandidate(ResultSet data) throws SQLException {

        return new CandidateEntity(
                data.getInt("candidate_id"),
                data.getString("first_name"),
                data.getString("last_name"),
                data.getString("phone_number"),
                data.getString("email"),
                getCountryFromString(data.getString("country")),
                getProfileFromString(data.getString("profile")),
                data.getString("link_to_cv"),
                getSourceFromString(data.getString("source")),
                data.getLong("current_salary"),
                data.getLong("salary_expectations"),
                getDateFromString(data.getString("in_mail")),
                getDateFromString(data.getString("phone_screening")),
                getDateFromString(data.getString("hr_interview")),
                getDateFromString(data.getString("submit_to_hm")),
                getDateFromString(data.getString("hm_interview")),
                getDateFromString(data.getString("test")),
                data.getInt("suitable"),
                getDateFromString(data.getString("offer_letter")),
                getDateFromString(data.getString("selection_closed")),
                getResultFromString(data.getString("result")),
                data.getString("additional_comments"),
                data.getInt("for_statistics"),
                data.getInt("important"),
                data.getInt("from_agency"));
    }
    
    
    public static PositionEntity buildPosition (ResultSet data) throws SQLException {

        int x1 = data.getInt("position_id");
        String x2 = data.getString("name");
        Country x3 = Utils.getCountryFromString(data.getString("country"));
        Profile x4 = Utils.getProfileFromString(data.getString("profile"));
        LocalDate x5 = Utils.getDateFromString(data.getString("open_date"));
        LocalDate x6 = Utils.getDateFromString(data.getString("close_date"));
        int x7 = data.getInt("budget");
        String x8 = data.getString("additional_information");
        int x9 = data.getInt("for_statistics");
        int x10 = data.getInt("important");

        return new PositionEntity (x1,x2,x3,x4,x5,x6,x7,x8,x9,x10);
    }

    public static int getCurrentCandidateInterval(CandidateEntity candidate) {
        int interval;
        Stage flag = candidate.getCurrentStageFlag();
        switch (flag) {
            case PHONE_SCREENING -> interval = calculateDays(candidate.getInMail(), LocalDate.now());
            case HR_INTERVIEW -> interval = calculateDays(candidate.getPhoneScreening(), LocalDate.now());
            case SUBMITTED_TO_HM -> interval = calculateDays(candidate.getHRInterview(), LocalDate.now());
            case HM_INTERVIEW -> interval = calculateDays(candidate.getSubmittedToHM(), LocalDate.now());
            case TEST -> interval = calculateDays(candidate.getHMInterview(), LocalDate.now());
            case OFFER_LETTER -> interval = calculateDays(candidate.getTest(), LocalDate.now());
            case SELECTION_CLOSED -> interval = calculateDays(candidate.getOfferLetter(), LocalDate.now());
            default -> interval = -1;
        }
        return interval;
    }

    /**
     * Counts the number of days between two dates.
     * <p>
     * </p>
     *
     * @param startDate starting point
     * @param endDate   endpoint
     * @return number of days
     */
    public static int calculateDays(LocalDate startDate, LocalDate endDate) {
        if(startDate != null && endDate != null) {
            int startDay = startDate.getDayOfMonth();
            int startYear = startDate.getYear();
            int startMonth = startDate.getMonthValue();
            int endDay = endDate.getDayOfMonth();
            int endYear = endDate.getYear();
            int endMonth = endDate.getMonthValue();
            int numberOfDays;
            int total = 0;
            
            while (startYear < endYear) {
                while (startMonth <= 12) {
                    numberOfDays = getNumberOfDays(startMonth, startYear);
                    while (startDay <= numberOfDays) {
                        total++;
                        startDay++;
                    }
                    startDay = 1;
                    startMonth++;
                }
                startMonth = 1;
                startYear++;
            }
            while (startMonth < endMonth) {
                numberOfDays = getNumberOfDays(startMonth, startYear);
                while (startDay <= numberOfDays) {
                    total++;
                    startDay++;
                }
                startDay = 1;
                startMonth++;
            }
            while (startDay < endDay) {
                total++;
                startDay++;
            }
            return total;
        }
        return -1;
    }

    private static int getNumberOfDays(int month, int year) {
        int numberOfDays;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> numberOfDays = 31;
            case 2 -> numberOfDays = Year.of(year).isLeap() ? 29 : 28;
            default -> numberOfDays = 30;
        }
        return numberOfDays;
    }

    public static String addPlaceHolder(String string) {
        switch (string) {
            case "1" -> string = "01";
            case "2" -> string = "02";
            case "3" -> string = "03";
            case "4" -> string = "04";
            case "5" -> string = "05";
            case "6" -> string = "06";
            case "7" -> string = "07";
            case "8" -> string = "08";
            case "9" -> string = "09";
        }
        return string;
    }
}