package sevices.serviceImpl;

import java.sql.SQLException;

import controller.Context;
import repository.JDBCStatisticsDAO;
import sevices.JDBCStatisticsService;
import util.enums.Profile;
import view.components.frames.CustomErrorDialogue;


public class SQLiteStatisticsServiceImpl implements JDBCStatisticsService {

    private final JDBCStatisticsDAO statisticsDAO;
    
    public SQLiteStatisticsServiceImpl(JDBCStatisticsDAO statisticsDAO) {
        this.statisticsDAO = statisticsDAO;
    }
    
    @Override
    public float[] getCentralTendencies(boolean isMean, Profile profile) {
        if(isMean){
            return profile == Profile.NONE ? getMeanAll() : getMeanByProfile(profile);
        }
        else {
            return profile == Profile.NONE ? getMedianAll() : getMedianByProfile(profile);
        }
    }

    @Override
    public float[] getMeanAll() {
        try {
            return statisticsDAO.getMeanAll();
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to calculate average value ", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public float[] getMeanByProfile(Profile profile) {
        try {
            return statisticsDAO.getMeanByProfile(profile);
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to calculate average value ", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public float[] getMedianAll() {
        try {
            return statisticsDAO.getMedianAll();
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to calculate median value ", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public float[] getMedianByProfile(Profile profile) {
        try {
            return statisticsDAO.getMedianByProfile(profile);
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to calculate median value", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }

    @Override
    public int countPositions(boolean isWithinRange, int low, int high, Profile profile) {
        try {
            return statisticsDAO.countPositions(isWithinRange, low, high, profile);
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to count positions", Context.getParentFrame(), true).showDialogue();
        }
        return 0;
    }

    @Override
    public int countCandidatesPerStage(boolean isWithinRange, String beforeDate, String afterDate, int numberOfDays, Profile profile) {
        try {
            return statisticsDAO.countCandidatesPerStage(isWithinRange, beforeDate, afterDate, numberOfDays, profile);
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to count candidates", Context.getParentFrame(), true).showDialogue();
        }
        return 0;
    }

    @Override
    public int countCandidates(boolean isWithinRange, String beforeDate, String afterDate, int low, int high, Profile profile) {
        try {
            return statisticsDAO.countCandidates(isWithinRange, beforeDate, afterDate, low, high, profile);
        }
        catch(SQLException e) {
            new CustomErrorDialogue(true, "Failed to count candidates", Context.getParentFrame(), true).showDialogue();
        }
        return 0;
    }
}