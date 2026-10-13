package User;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
