package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DatabaseConnection is a utility class that provides methods to establish a connection to the database.
 * It allows setting a custom database URL and resetting it to the default value.
 */
public class DatabaseConnection {

    private static final String DEFAULT_DATABASE_URL =
            "jdbc:sqlite:PlantsDB.db";

    private static String databaseUrl = DEFAULT_DATABASE_URL;

    /**
     * Establishes a connection to the database using the current database URL.
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl);
    }

    /**
     * Sets the database URL to a custom value.
     * @param url The custom database URL
     */
    public static void setDatabaseUrl(String url) {
        databaseUrl = url;
    }

    /**
     * Resets the database URL to the default value.
     */
    public static void resetDatabaseUrl() {
        databaseUrl = DEFAULT_DATABASE_URL;
    }
}
