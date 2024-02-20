package repository.SQLite;

import entity.CandidateEntity;
import entity.PositionEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.JDBCPositionDAO;
import util.Utils;
import static util.Utils.getCountryFromString;
import static util.Utils.getDateFromString;
import static util.Utils.getProfileFromString;
import static util.Utils.getResultFromString;
import static util.Utils.getSourceFromString;
import util.connection.SQLiteConnectionManager;

public class SQLitePositionDAOImpl implements JDBCPositionDAO {
    private final String SELECT_ALL = """
        SELECT position_id, name, country, profile, open_date, close_date, budget, additional_information, for_statistics, important
        FROM position
        ORDER BY name;
        """;

    private final String SELECT_ONE = """
        SELECT position_id, name, country, profile, open_date, close_date, budget, additional_information, for_statistics, important
        FROM position
        WHERE position_id = ?
        ORDER BY name;
        """;

    private final String INSERT = """
        INSERT INTO position (name, country, profile, open_date, close_date, budget, additional_information, for_statistics, important)        
        VALUES (?,?,?,?,?,?,?,?,?)
        RETURNING position_id;
        """;


    private final String DELETE = """
        DELETE FROM position WHERE position_id = ?;
        """;

    private final String UPDATE = """
            UPDATE position
            SET
                name = ?,
                country = ?,  
                profile = ?,  
                open_date = ?,  
                close_date = ?,  
                budget = ?,
                additional_information = ?,
                for_statistics = ?,  
                important = ?
            WHERE position_id = ?;
            """;

    private final String ATTACH_POSITION_TO_CANDIDATE = """
        INSERT OR IGNORE INTO position_candidate (position_id, candidate_id)
        VALUES (?, ?);
        """;

    private final String DETACH_CANDIATES_FROM_POSITION = """
        DELETE FROM position_candidate
        WHERE position_id = ? AND candidate_id = ?;
        """;

    private final String DETACH_POSITION_FROM_CANDIDATE = """
        DELETE FROM position_candidate
        WHERE candidate_id = ?;
        """;

    private final String SELECT_CANDIDATES_BY_POSITION = """
        SELECT candidate_id, first_name, last_name, phone_number, email, country, profile, link_to_cv, source, current_salary, salary_expectations, in_mail, phone_screening, hr_interview, submit_to_hm, hm_interview, test, suitable, offer_letter, selection_closed, result, additional_comments, important, for_statistics, from_agency
        FROM candidate
        WHERE candidate_id IN (
            SELECT candidate_id
            FROM position_candidate
            WHERE position_id = ?);
        """;

   

    public ArrayList<PositionEntity> find() throws SQLException {
        ArrayList<PositionEntity> positions = new ArrayList<>();
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                positions.add(Utils.buildPosition(result));
            }
        }
        return positions;
    }


    public PositionEntity findById(Integer id) throws SQLException {
        try(var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ONE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Utils.buildPosition(result);
            }
            return null;
        }
    }

    public int save(PositionEntity p) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(INSERT);
            statement.setString(1, p.getName());
            statement.setString(2, Utils.getStringFromCountry(p.getCountry()));
            statement.setString(3, Utils.getStringFromProfile(p.getProfile()));
            statement.setString(4, Utils.getDBStringFromDate(p.getOpenDate()));
            statement.setString(5, Utils.getDBStringFromDate(p.getCloseDate()));
            statement.setLong(6, p.getBudget());
            statement.setString(7, p.getAdditionalInformation());
            statement.setInt(8, p.getForStatistics());
            statement.setInt(9, p.getImportant());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("position_id");
            }
            return -1;
        }
    }

    public boolean delete(Integer id) throws SQLException {

        try(var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.execute();
        }
    }

    public boolean update(PositionEntity p) throws SQLException {

        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(UPDATE);
            statement.setString(1, p.getName());
            statement.setString(2, Utils.getStringFromCountry(p.getCountry()));
            statement.setString(3, Utils.getStringFromProfile(p.getProfile()));
            statement.setString(4, Utils.getDBStringFromDate(p.getOpenDate()));
            statement.setString(5, Utils.getDBStringFromDate(p.getCloseDate()));
            statement.setLong(6, p.getBudget());
            statement.setString(7, p.getAdditionalInformation());
            statement.setInt(8, p.getForStatistics());
            statement.setInt(9, p.getImportant());
            statement.setInt(10, p.getId());
            statement.executeUpdate();
            return true;
        }
    }

    public ArrayList<CandidateEntity> findCandidatesByPosition (Integer id) throws SQLException {
        ArrayList<CandidateEntity> candidates = new ArrayList<>();
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_CANDIDATES_BY_POSITION);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                candidates.add(new CandidateEntity (
                result.getInt("candidate_id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("phone_number"),
                result.getString("email"),
                getCountryFromString(result.getString("country")),
                getProfileFromString(result.getString("profile")),
                result.getString("link_to_cv"),
                getSourceFromString(result.getString("source")),
                result.getLong("current_salary"),
                result.getLong("salary_expectations"),
                getDateFromString(result.getString("in_mail")),
                getDateFromString(result.getString("phone_screening")),
                getDateFromString(result.getString("hr_interview")),
                getDateFromString(result.getString("submit_to_hm")),
                getDateFromString(result.getString("hm_interview")),
                getDateFromString(result.getString("test")),
                result.getInt("suitable"),
                getDateFromString(result.getString("offer_letter")),
                getDateFromString(result.getString("selection_closed")),
                getResultFromString(result.getString("result")),
                result.getString("additional_comments"),
                result.getInt("for_statistics"),
                result.getInt("important"),
                result.getInt("from_agency")));
            }
        }
        return candidates;
    }

    @Override
    public void attachPositionToCandidate(PositionEntity position, CandidateEntity candidate) throws SQLException {
        try(Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement;
            statement = connection.prepareStatement(ATTACH_POSITION_TO_CANDIDATE);
            statement.setInt(1, position.getId());
            statement.setInt(2, candidate.getId());
            statement.execute();
        }
    }

    @Override
    public boolean detachCandidateFromPosition (PositionEntity position, CandidateEntity candidate) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(DETACH_CANDIATES_FROM_POSITION);
            statement.setInt(1, position.getId());
            statement.setInt(2, candidate.getId());
            statement.execute();
            return true;
        }
    }
    
    public boolean detachPositionFromCandidate (CandidateEntity candidate) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(DETACH_POSITION_FROM_CANDIDATE);
            statement.setInt(1, candidate.getId());
            statement.execute();
            return true;
        }
    }
}
