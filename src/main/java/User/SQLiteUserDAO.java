package User;

import database.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteUserDAO {
    public boolean registerUser(User user) {
        String sql = "INSERT INTO Users (Username, Hash) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, hashPassword(user.getPassword()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("UNIQUE")) {
                return false;
            }
            throw new IllegalStateException("Unable to register user", e);
        }
    }

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