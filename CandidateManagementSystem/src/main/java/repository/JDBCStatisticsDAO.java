package repository;

import java.sql.SQLException;
import util.enums.Profile;

public interface JDBCStatisticsDAO {

    int countPositions(boolean isWithinRange, int low, int high, Profile profile) throws SQLException;

    float[] getMeanAll() throws SQLException;

    float[] getMeanByProfile(Profile profile) throws SQLException;
    
    float[] getMedianAll() throws SQLException;
    
    float[] getMedianByProfile(Profile profile) throws SQLException;
    int countCandidatesPerStage(boolean isWithinRange, String beforeDate, String afterDate, int numberOfDays, Profile profile) throws SQLException;
    int countCandidates(boolean isWithinRange, String beforeDate, String afterDate, int low, int high, Profile profile) throws SQLException;
}
