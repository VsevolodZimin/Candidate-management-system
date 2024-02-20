package repository.SQLite;

import entity.LabelEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.JDBCLabelDAO;
import util.connection.SQLiteConnectionManager;

public class SQLiteLabelDAOImpl implements JDBCLabelDAO
{
    
    private final String SELECT_ALL = """
        SELECT label_id, name
        FROM label
        ORDER BY name;
        """;

    private final String SELECT_ONE = """
        SELECT label_id, name
        FROM label
        WHERE label_id = ?
        ORDER BY name;
        """;

    private final String INSERT = """
        INSERT INTO label (name)
        VALUES (?)
        RETURNING label_id                
        """;

    private final String DELETE = """
        DELETE FROM label 
        WHERE label_id = ?;
        """;

    private final String UPDATE = """
        UPDATE label
        SET name = ?
        WHERE label_id = ?;
        """;
    
    
    public ArrayList<LabelEntity> find() throws SQLException {
        ArrayList<LabelEntity> labels = new ArrayList<>();
        try (Connection connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                labels.add(new LabelEntity(
                result.getInt("label_id"), 
               result.getString("name")));
            }
            return labels;
        }
    }
    
    public LabelEntity findById(Integer id) throws SQLException {
        try(var connection = SQLiteConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ONE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new LabelEntity(
                result.getInt("label_id"), 
               result.getString("name"));
            }
            return null;
        }
    }
    
    
    public int save(LabelEntity l) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(INSERT);
            statement.setString(1, l.getName());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("label_id");
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

    public boolean update(LabelEntity l) throws SQLException {
        try (var connection = SQLiteConnectionManager.open()) {
            var statement = connection.prepareStatement(UPDATE);
            statement.setString(1, l.getName());
            statement.setInt(2, l.getId());
            statement.executeUpdate();
            return true;
        }
    }
}