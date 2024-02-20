package repository.SQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import repository.JDBCStatisticsDAO;
import util.Utils;
import util.connection.SQLiteConnectionManager;
import util.enums.Profile;

public class SQLiteStatisticsDAOImpl implements JDBCStatisticsDAO {
    
    private final String GET_MEDIAN_BY_PROFILE = """          
                                                               
SELECT                                       
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)	
)
) as phone_screening,

                                                                            
(
SELECT AVG(days)
FROM (
    SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
    FROM candidate
    WHERE days IS NOT NULL AND profile = ?
    ORDER by days
    LIMIT 2 - (
        SELECT COUNT(*) % 2
        FROM (
            SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
            FROM candidate
            WHERE days IS NOT NULL AND profile = ?
            ORDER BY days
        ) 
    )
    OFFSET (
        SELECT (COUNT(*) - 1) /2
        FROM (
            SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
            FROM candidate
            WHERE days IS NOT NULL AND profile = ?
            ORDER BY days 
        )
    )	
)
) as hr_interview,       


(
SELECT AVG(days)
FROM (SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
    FROM candidate
    WHERE days IS NOT NULL AND profile = ?
    ORDER by days
    LIMIT 2 - (
        SELECT COUNT(*) % 2
        FROM (
            SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
            FROM candidate
            WHERE days IS NOT NULL AND profile = ?
            ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)	
)
) as submit_to_hm,                                                                                                                                            
    
                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)
)
) as hm_interview,                                      

                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)
)
) as test,                                        
                
                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)
)
) as offer_letter,           
                                      
                                      
(                                      
SELECT AVG(days)
FROM (SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
)	
)
) as selection_closed,
                                      
                                      
(                                      
SELECT AVG(days)
FROM (SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL AND profile = ?
ORDER BY days 
)
) 
)
) as whole_process                                                             
""";
    
    
    private final String GET_MEDIAN = """
SELECT                                       
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (SELECT JULIANDAY(phone_screening) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)	
)
) as phone_screening,

                                                                            
(
SELECT AVG(days)
FROM (
    SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
    FROM candidate
    WHERE days IS NOT NULL
    ORDER by days
    LIMIT 2 - (
        SELECT COUNT(*) % 2
        FROM (
            SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
            FROM candidate
            WHERE days IS NOT NULL
            ORDER BY days
        ) 
    )
    OFFSET (
        SELECT (COUNT(*) - 1) /2
        FROM (
            SELECT JULIANDAY(hr_interview) - JULIANDAY(phone_screening) as days
            FROM candidate
            WHERE days IS NOT NULL
            ORDER BY days 
        )
    )	
)
) as hr_interview,       


(
SELECT AVG(days)
FROM (SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)	
)
) as submit_to_hm,                                                                                                                                            
    
                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)
)
) as hm_interview,                                      

                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(test) - JULIANDAY(hm_interview) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)
)
) as test,                                        
                
                                      
(
SELECT AVG(days)
FROM (SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(offer_letter) - JULIANDAY(test) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)
)
) as offer_letter,           
                                      
                                      
(                                      
SELECT AVG(days)
FROM (SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(offer_letter) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
)	
)
) as selection_closed,
                                      
                                      
(                                      
SELECT AVG(days)
FROM (SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER by days
LIMIT 2 - (
SELECT COUNT(*) % 2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days
) 
)
OFFSET (
SELECT (COUNT(*) - 1) /2
FROM (
SELECT JULIANDAY(selection_closed) - JULIANDAY(in_mail) as days
FROM candidate
WHERE days IS NOT NULL
ORDER BY days 
)
) 
)
) as whole_process 
            """;
    
    
    private final String GET_MEAN = """
            SELECT
                AVG(JULIANDAY(phone_screening) - JULIANDAY(in_mail)) as phone_screening,
                AVG(JULIANDAY(hr_interview) - JULIANDAY(phone_screening)) as hr_interview,
                AVG(JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview)) as submit_to_hm,
                AVG(JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm)) as hm_interview,
                AVG(JULIANDAY(test) - JULIANDAY(hm_interview)) as test,
                AVG(JULIANDAY(offer_letter) - JULIANDAY(test)) as offer_letter,
                AVG(JULIANDAY(selection_closed) - JULIANDAY(offer_letter)) as selection_closed,
                AVG(JULIANDAY(selection_closed) - JULIANDAY(in_mail)) as whole_process
            FROM candidate
            """;  
    
        private final String GET_MEAN_BY_PROFILE = """
            SELECT
                AVG(JULIANDAY(phone_screening) - JULIANDAY(in_mail)) as phone_screening,
                AVG(JULIANDAY(hr_interview) - JULIANDAY(phone_screening)) as hr_interview,
                AVG(JULIANDAY(submit_to_hm) - JULIANDAY(hr_interview)) as submit_to_hm,
                AVG(JULIANDAY(hm_interview) - JULIANDAY(submit_to_hm)) as hm_interview,
                AVG(JULIANDAY(test) - JULIANDAY(hm_interview)) as test,
                AVG(JULIANDAY(offer_letter) - JULIANDAY(test)) as offer_letter,
                AVG(JULIANDAY(selection_closed) - JULIANDAY(offer_letter)) as selection_closed,
                AVG(JULIANDAY(selection_closed) - JULIANDAY(in_mail)) as whole_process
            FROM candidate
            WHERE profile = ?
            """;  
        
        private String queryCandidatesPerStage (boolean isWithinRange, String before, String after, int number, Profile profile) {
            String query = "SELECT COUNT(*) FROM candidate ";
            if(isWithinRange) {
                query += "WHERE JULIANDAY(" + after + ") - JULIANDAY(" + before + ") = " + number;
            }
            else {
                query += "WHERE JULIANDAY(" + after + ") - JULIANDAY(" + before + ") > " + number;
            }
            if(profile != Profile.NONE) {
                query += " AND profile = '" + Utils.getStringFromProfile(profile) + "'";
            }
            return query + ";";
        }
        
        
        private String queryCandidates (boolean isWithinRange, String before, String after, int low, int high, Profile profile) {
            String query = "SELECT COUNT(*) FROM candidate ";
            if(isWithinRange) {
                query += "WHERE JULIANDAY(" + after + ") - JULIANDAY(" + before + ") >= " + low + " AND JULIANDAY(" + after + ") - JULIANDAY(" + before + ") <= " + high;
            }
            else {
                query += "WHERE JULIANDAY(" + after + ") - JULIANDAY(" + before + ") >= " + low;
            }
            if(profile != Profile.NONE) {
                query += " AND profile = '" + Utils.getStringFromProfile(profile) + "'";
            }
            return query;
        }
        
        
        private String queryPositions (boolean isWithinRange, int low, int high, Profile profile) {
            String query = "SELECT COUNT(*) FROM position ";
            if(isWithinRange) {
                query += " WHERE JULIANDAY(close_date) - JULIANDAY(open_date) >= "+low+" AND JULIANDAY(close_date) - JULIANDAY(open_date) <= "+high;
            }
            else {
                query += " WHERE JULIANDAY(close_date) - JULIANDAY(open_date) > "+low;
            }
            if(profile != Profile.NONE) {
                query += " AND profile = '" + Utils.getStringFromProfile(profile) + "'";
            }
            return query;
        }
        
        @Override
    public float[] getMedianAll() throws SQLException {

        float[] data = new float[8];
        try (var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(GET_MEDIAN);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                data[0] = result.getFloat("phone_screening");
                data[1] = result.getFloat("hr_interview");
                data[2] = result.getFloat("submit_to_hm");
                data[3] = result.getFloat("hm_interview");
                data[4] = result.getFloat("test");
                data[5] = result.getFloat("offer_letter");
                data[6] = result.getFloat("selection_closed");
                data[7] = result.getFloat("whole_process");
            }
        }
        return data;
    }
    
    @Override
    public float[] getMedianByProfile(Profile profile) throws SQLException {
        
        float[] data = new float[8];
        String profileStr = Utils.getStringFromProfile(profile);
        
        try (var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(GET_MEDIAN_BY_PROFILE);
            for(int i = 1; i < 25; i++) {
                statement.setString(i, profileStr);
            }
            ResultSet result = statement.executeQuery();
            
            if(result.next()){
                data[0] = result.getFloat("phone_screening");
                data[1] = result.getFloat("hr_interview");
                data[2] = result.getFloat("submit_to_hm");
                data[3] = result.getFloat("hm_interview");
                data[4] = result.getFloat("test");
                data[5] = result.getFloat("offer_letter");
                data[6] = result.getFloat("selection_closed");
                data[7] = result.getFloat("whole_process");
            }
        }
        return data;
    }
    
    
    @Override
    public float[] getMeanAll() throws SQLException {
    float[] data = new float[8];
        try (var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(GET_MEAN);
            ResultSet result = statement.executeQuery();
            if(result.next()){

                data[0] = result.getFloat("phone_screening");
                data[1] = result.getFloat("hr_interview");
                data[2] = result.getFloat("submit_to_hm");
                data[3] = result.getFloat("hm_interview");
                data[4] = result.getFloat("test");
                data[5] = result.getFloat("offer_letter");
                data[6] = result.getFloat("selection_closed");
                data[7] = result.getFloat("whole_process");
            }
        }
        return data;
    }


    @Override
    public float[] getMeanByProfile(Profile profile) throws SQLException {
    float[] data = new float[8];
        String profileStr = Utils.getStringFromProfile(profile);
        
        try (var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(GET_MEAN_BY_PROFILE);
            statement.setString(1, profileStr);
      
            ResultSet result = statement.executeQuery();
            
            if(result.next()){
                data[0] = result.getFloat("phone_screening");
                data[1] = result.getFloat("hr_interview");
                data[2] = result.getFloat("submit_to_hm");
                data[3] = result.getFloat("hm_interview");
                data[4] = result.getFloat("test");
                data[5] = result.getFloat("offer_letter");
                data[6] = result.getFloat("selection_closed");
                data[7] = result.getFloat("whole_process");
            }
        }
        return data;    
    }
        
        
    @Override
    public int countCandidatesPerStage (boolean isWithinRange,
                                       String beforeDate,
                                       String afterDate,
                                       int numberOfDays,
                                       Profile profile) throws SQLException {

        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(queryCandidatesPerStage(isWithinRange, beforeDate, afterDate, numberOfDays, profile));
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return result.getInt(1);
            }
        }
        return -1;
    }
    
    
    @Override
    public int countCandidates (boolean isWithinRange,
                               String beforeDate,
                               String afterDate,
                               int low,
                               int high,
                               Profile profile) throws SQLException {

        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(queryCandidates(isWithinRange, beforeDate, afterDate, low, high, profile));
            ResultSet result = statement.executeQuery();
            if(result.next()){
                int x = result.getInt(1);
                return x;
            }
        }
        return -1;
    }
    
    
    @Override
    public int countPositions(boolean isWithinRange, int low, int high, Profile profile) throws SQLException{
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(queryPositions(isWithinRange, low, high, profile));
            statement.execute();
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                int x = result.getInt(1);
                return x;
            }
        }
        return -1;
    }
}
