package User;

import java.util.List;

/**
 * IBookmarkDAO is an interface that defines the methods for interacting with the Bookmarks table in the database.
 */
public interface IBookmarkDAO {

    /**
     * Adds a bookmark for a user and plant.
     * @param userId The ID of the user.
     * @param plantId The ID of the plant.
     */
    void addBookmark(int userId, int plantId);

    /**
     * Removes a bookmark for a user and plant.
     * @param userId The ID of the user.
     * @param plantId The ID of the plant.
     */
    void removeBookmark(int userId, int plantId);

    /**
     * Checks if a plant is bookmarked by a user.
     * @param userId The ID of the user.
     * @param plantId The ID of the plant.
     * @return true if the plant is bookmarked by the user, false otherwise.
     */
    boolean isBookmarked(int userId, int plantId);

    List<Integer> getBookmarks(int userId);
}

