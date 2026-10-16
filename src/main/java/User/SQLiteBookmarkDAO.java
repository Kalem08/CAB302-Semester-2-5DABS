package User;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * SQLiteBookmarkDAO is a data access object (DAO) that provides methods for interacting with the Bookmarks table in the SQLite database.
 */
public class SQLiteBookmarkDAO implements IBookmarkDAO {

    @Override
    public void addBookmark(int userId, int plantId) {
        String sql = "INSERT INTO Bookmarks (UserId, PlantId) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, plantId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add bookmark", e);
        }
    }

    @Override
    public void removeBookmark(int userId, int plantId) {
        String sql = "DELETE FROM Bookmarks WHERE UserId = ? AND PlantId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, plantId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove bookmark", e);
        }
    }

    @Override
    public boolean isBookmarked(int userId, int plantId) {
        String sql = "SELECT 1 FROM Bookmarks WHERE UserId = ? AND PlantId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, plantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check bookmark", e);
        }
    }

    @Override
    public List<Integer> getBookmarks(int userId) {
        List<Integer> bookmarks = new ArrayList<>();

        String sql = "SELECT PlantId FROM Bookmarks WHERE UserId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    bookmarks.add(resultSet.getInt("PlantId"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve bookmarks", e);
        }

        return bookmarks;
    }
}
