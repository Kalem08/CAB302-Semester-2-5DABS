package User;

import java.util.List;
/**
 * IHistoryDAO is an interface that defines the methods for interacting with the History table in the database.
 */
public interface IHistoryDAO {

    /**
     * Records a view of a plant by a user in the history.
     * @param userId The ID of the user.
     * @param plantId The ID of the plant.
     */
    void recordView(int userId, int plantId);

    /**
     * Retrieves the viewing history of a user.
     * @param userId The ID of the user.
     * @return A list of History objects representing the user's viewing history.
     */
    List<History> getHistory(int userId);

    /**
     * Clears the viewing history of a user.
     * @param userId The ID of the user.
     */
    void clearHistory(int userId);
}
