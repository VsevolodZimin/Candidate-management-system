package util.connection;

import controller.Context;
import lombok.Getter;
import view.components.frames.CustomErrorDialogue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLiteConnectionManager {

    @Getter
    public static final String dbPath = PathsPropertiesManager.absolutePath +  PathsPropertiesManager.dataBaseDir + "sinclairDB.sqlite";
    private static final String url = "jdbc:sqlite:" + dbPath;
    public static Connection open() {
        try {
            return DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            new CustomErrorDialogue(true, "Failed to connect to the database", Context.getParentFrame(), true).showDialogue();
        }
        return null;
    }
}