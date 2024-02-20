package sevices;

import java.sql.SQLException;
import util.enums.Profile;

public interface JDBCStatisticsService {
    
        
    float[] getCentralTendencies(boolean isMean, Profile profile);
    
    float[] getMeanAll();
    
    float[] getMeanByProfile(Profile profile);
    
    float[] getMedianAll();
    
    float[] getMedianByProfile(Profile profile);

    int countPositions(boolean isWithinRange, int low, int high, Profile profile);

    int countCandidatesPerStage(boolean isWithinRange, String beforeDate, String afterDate, int numberOfDays, Profile profile);

    int countCandidates(boolean isWithinRange, String beforeDate, String afterDate, int low, int high, Profile profile);
}