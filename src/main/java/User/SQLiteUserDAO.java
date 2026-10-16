package User;

import database.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
/**
 * SQLiteUserDAO is a data access object (DAO) that provides methods for interacting with the Users table in the SQLite database.
 */
public class SQLiteUserDAO {
    /**
     * Registers a new user in the database.
     * @param user The user to register.
     * @return true if the user was successfully registered, false if the username already exists.
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO Users (Username, Hash, Role) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.username());
            statement.setString(2, hashPassword(user.password()));
            statement.setString(3, user.role().name());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("UNIQUE")) {
                return false;
            }
            throw new IllegalStateException("Unable to register user", e);
        }
    }

    /**
     * Checks if a user with the given username exists in the database.
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean exists(String username) {
        String sql = "SELECT 1 FROM Users WHERE Username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to check whether user exists", e);
        }
    }

    /**
     * Authenticates a user by checking if the provided username and password match the stored credentials.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        String sql = "SELECT Hash FROM Users WHERE Username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()
                        && MessageDigest.isEqual(
                        resultSet.getString("Hash").getBytes(StandardCharsets.UTF_8),
                        hashPassword(password).getBytes(StandardCharsets.UTF_8));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to authenticate user", e);
        }
    }

    /**
     * Retrieves the user ID for a given username.
     * @param username The username of the user.
     * @return The user ID, or -1 if the user does not exist.
     */
    public int getUserId(String username) {
        String sql = "SELECT UserId FROM Users WHERE Username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("UserId");
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Unable to retrieve user ID", e);
        }

        return -1;
    }

    /**
     * Retrieves the role of a user based on their username.
     * @param username The username of the user.
     * @return The role of the user, or Role.STANDARD if the user does not exist or has no role assigned.
     */
    public Role getUserRole(String username) {
        String sql = "SELECT Role FROM Users WHERE Username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Role.STANDARD;
                }

                String storedRole = resultSet.getString("Role");
                if (storedRole == null) {
                    return Role.STANDARD;
                }

                String normalisedRole = storedRole.trim().toLowerCase(Locale.ROOT);
                if (normalisedRole.equals("administrator") || normalisedRole.equals("admin")) {
                    return Role.ADMINISTRATOR;
                }
                return Role.STANDARD;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to load user role", e);
        }
    }

    /**
     * Hashes a password using SHA-256.
     * @param password The password to hash.
     * @return The hashed password.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder(hash.length * 2);

            for (byte value : hash) {
                result.append(String.format("%02x", value));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is unavailable", e);
        }
    }
}