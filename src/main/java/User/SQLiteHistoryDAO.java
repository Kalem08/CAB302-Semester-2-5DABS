package User;

import java.util.List;
import java.util.ArrayList;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteHistoryDAO implements IHistoryDAO {
    @Override
    public void recordView(int userId, int plantId) {
        String sql = "INSERT INTO History (UserId, PlantId, ViewedAt) " +
                "VALUES (?, ?, datetime('now'))";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, plantId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Unable to record viewing history", e);
        }
    }

    @Override
    public List<History> getHistory(int userId) {
        List<History> historyList = new ArrayList<>();

        String sql = "SELECT HistoryId, UserId, PlantId, ViewedAt " +
                "FROM History WHERE UserId = ? " +
                "ORDER BY ViewedAt DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    History history = new History(
                            resultSet.getInt("HistoryId"),
                            resultSet.getInt("UserId"),
                            resultSet.getInt("PlantId"),
                            resultSet.getString("ViewedAt")
                    );

                    historyList.add(history);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Unable to retrieve viewing history", e);
        }

        return historyList;
    }

    @Override
    public void clearHistory(int userId) {
        String sql = "DELETE FROM History WHERE UserId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Unable to clear viewing history", e);
        }
    }
}
