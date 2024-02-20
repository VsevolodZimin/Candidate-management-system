package repository.SQLite;

import entity.CandidateEntity;
import entity.LabelEntity;
import entity.PositionEntity;
import util.Utils;
import util.connection.SQLiteConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.JDBCCandidateDAO;

public class SQLiteCandidateDAOImpl implements JDBCCandidateDAO {
    private final String SELECT_ALL = """
        SELECT candidate_id, first_name, last_name, phone_number, email, country, profile, link_to_cv, source, current_salary, salary_expectations, in_mail, phone_screening, hr_interview, submit_to_hm, hm_interview, test, suitable, offer_letter, selection_closed, result, additional_comments, important, for_statistics, from_agency
        FROM candidate
        ORDER BY first_name;
        """;

    private final String SELECT_ONE = """
        SELECT candidate_id, first_name, last_name, phone_number, email, country, profile, link_to_cv, source, current_salary, salary_expectations, in_mail, phone_screening, hr_interview, submit_to_hm, hm_interview, test, suitable, offer_letter, selection_closed, result, additional_comments, important, for_statistics, from_agency
        FROM candidate
        WHERE candidate_id = ?
        ORDER BY first_name;
        """;

    private final String INSERT = """
        INSERT INTO candidate (first_name, last_name, phone_number, email, country, profile, link_to_cv, source, current_salary, salary_expectations, in_mail, phone_screening, hr_interview, submit_to_hm, hm_interview, test, suitable, offer_letter, selection_closed, result, additional_comments, important, for_statistics, from_agency)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        RETURNING candidate_id;
        """;


    private final String DELETE = """
        DELETE FROM candidate WHERE candidate_id = ?;
        """;

    private final String UPDATE = """
            UPDATE candidate
            SET
                first_name = ?,
                last_name = ?,
                phone_number = ?,
                email = ?,
                country = ?,
                profile = ?,
                link_to_cv = ?,
                source = ?,
                current_salary = ?,
                salary_expectations = ?,
                in_mail = ?,
                phone_screening = ?,
                hr_interview = ?,
                submit_to_hm = ?,
                hm_interview = ?,
                test = ?,
                suitable = ?,
                offer_letter = ?,
                selection_closed = ?,
                result = ?,
                additional_comments = ?,
                important = ?,
                for_statistics = ?,
                from_agency = ?
            WHERE candidate_id = ?;
            """;

    private final String ATTACH_LABEL_TO_CANDIDATE = """
        INSERT OR IGNORE INTO candidate_label (candidate_id, label_id)
        VALUES (?, ?);
        """;

    private final String DETACH_LABEL_FROM_CANDIDATE = """
        DELETE FROM candidate_label
        WHERE candidate_id = ? AND label_id = ?;
        """;

    private final String SELECT_LABELS_BY_CANDIDATE = """
        SELECT label_id, name
        FROM label
        WHERE label_id IN (
            SELECT label_id
            FROM candidate_label
            WHERE candidate_id = ?);
        """;

    private final String SELECT_POSITION_BY_CANDIDATE = """
        SELECT position_id, name, country, profile, budget, additional_information, open_date, close_date, for_statistics, important
        FROM position
        WHERE position_id = (
            SELECT position_id
            FROM position_candidate
            WHERE candidate_id = ?)
        """;

    
    
    

    @Override
    public ArrayList<CandidateEntity> find() throws SQLException {
        ArrayList<CandidateEntity> candidates = new ArrayList<>();
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                candidates.add(Utils.buildCandidate(result));
            }
        }
        return candidates;
    }


    @Override
    public CandidateEntity findById(Integer id) throws SQLException {
        try(var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ONE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Utils.buildCandidate(result);
            }
            return null;
        }
    }

    @Override
    public int save(CandidateEntity c) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(INSERT);
            statement.setString(1, c.getFirstName());
            statement.setString(2, c.getLastName());
            statement.setString(3, c.getPhoneNumber());
            statement.setString(4, c.getEmail());
            statement.setString(5, Utils.getStringFromCountry(c.getCountry()));
            statement.setString(6, Utils.getStringFromProfile(c.getProfile()));
            statement.setString(7, c.getLinkToCV());
            statement.setString(8, Utils.getStringFromSource(c.getSource()));
            statement.setLong(9, c.getCurrentSalary() == null ? 0 : c.getCurrentSalary());
            statement.setLong(10, c.getSalaryExpectations() == null ? 0 : c.getSalaryExpectations());
            statement.setString(11, Utils.getDBStringFromDate(c.getInMail()));
            statement.setString(12, Utils.getDBStringFromDate(c.getPhoneScreening()));
            statement.setString(13, Utils.getDBStringFromDate(c.getHRInterview()));
            statement.setString(14, Utils.getDBStringFromDate(c.getSubmittedToHM()));
            statement.setString(15, Utils.getDBStringFromDate(c.getHMInterview()));
            statement.setString(16, Utils.getDBStringFromDate(c.getTest()));
            statement.setInt(17, c.getSuitableForSinclair());
            statement.setString(18,Utils.getDBStringFromDate(c.getOfferLetter()));
            statement.setString(19, Utils.getDBStringFromDate(c.getSelectionClosed()));
            statement.setString(20, Utils.getStringFromResult(c.getResult()));
            statement.setString(21, c.getAdditionalComments());
            statement.setInt(22, c.getImportant());
            statement.setInt(23, c.getForStatistics());
            statement.setInt(24, c.getFromAgency());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("candidate_id");
            }
            return -1;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {

        try(var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.execute();
        }
    }

    @Override
    public boolean update(CandidateEntity c) throws SQLException {

        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(UPDATE);
            statement.setString(1, c.getFirstName());
            statement.setString(2, c.getLastName());
            statement.setString(3, c.getPhoneNumber());
            statement.setString(4, c.getEmail());
            statement.setString(5, Utils.getStringFromCountry(c.getCountry()));
            statement.setString(6, Utils.getStringFromProfile(c.getProfile()));
            statement.setString(7, c.getLinkToCV());
            statement.setString(8, Utils.getStringFromSource(c.getSource()));
            statement.setLong(9, c.getCurrentSalary() == null ? 0 : c.getCurrentSalary());
            statement.setLong(10, c.getSalaryExpectations() == null ? 0 : c.getSalaryExpectations());
            statement.setString(11, Utils.getDBStringFromDate(c.getInMail()));
            statement.setString(12, Utils.getDBStringFromDate(c.getPhoneScreening()));
            statement.setString(13, Utils.getDBStringFromDate(c.getHRInterview()));
            statement.setString(14, Utils.getDBStringFromDate(c.getSubmittedToHM()));
            statement.setString(15, Utils.getDBStringFromDate(c.getHMInterview()));
            statement.setString(16, Utils.getDBStringFromDate(c.getTest()));
            statement.setInt(17, c.getSuitableForSinclair());
            statement.setString(18, Utils.getDBStringFromDate(c.getOfferLetter()));
            statement.setString(19, Utils.getDBStringFromDate(c.getSelectionClosed()));
            statement.setString(20, Utils.getStringFromResult(c.getResult()));
            statement.setString(21, c.getAdditionalComments());
            statement.setInt(22, c.getImportant());
            statement.setInt(23, c.getForStatistics());
            statement.setInt(24, c.getFromAgency());
            statement.setInt(25,c.getId());

            statement.executeUpdate();
            return true;
        }
    }
    @Override

    public ArrayList<LabelEntity> findLabelsByCandidate(Integer id) throws SQLException {
        ArrayList<LabelEntity> labels = new ArrayList<>();
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_LABELS_BY_CANDIDATE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                labels.add(new LabelEntity(
                        result.getInt("label_id"),
                        result.getString("name")));
            }
        }
        return labels;
    }

    @Override
    public PositionEntity findPositionByCandidate(Integer id) throws SQLException {
        PositionEntity position = null;
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_POSITION_BY_CANDIDATE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                position = new PositionEntity(
                        result.getInt("position_id"),
                        result.getString("name"),
                        Utils.getCountryFromString(result.getString("country")),
                        Utils.getProfileFromString(result.getString("profile")),
                        Utils.getDateFromString(result.getString("open_date")),
                        Utils.getDateFromString(result.getString("close_date")),
                        result.getInt("budget"),
                        result.getString("additional_information"),
                        result.getInt("for_statistics"),
                        result.getInt("important"));
            }
        }
        return position;
    }

    @Override
    public void attachLabelToCandidate (CandidateEntity candidate, LabelEntity label) throws SQLException {
        try(Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement;
            statement = connection.prepareStatement(ATTACH_LABEL_TO_CANDIDATE);
            statement.setInt(1, candidate.getId());
            statement.setInt(2, label.getId());
            statement.execute();
        }
    }

    @Override
    public boolean detachLabelFromCandidate (CandidateEntity candidate, LabelEntity label) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(DETACH_LABEL_FROM_CANDIDATE);
            statement.setInt(1, candidate.getId());
            statement.setInt(2, label.getId());
            statement.execute();
            return true;
        }
    }
}
