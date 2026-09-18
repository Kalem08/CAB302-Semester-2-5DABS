package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DEFAULT_DATABASE_URL =
            "jdbc:sqlite:PlantsDB.db";

    private static String databaseUrl = DEFAULT_DATABASE_URL;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl);
    }

    public static void setDatabaseUrl(String url) {
        databaseUrl = url;
    }

    public static void resetDatabaseUrl() {
        databaseUrl = DEFAULT_DATABASE_URL;
    }
}
