
import User.SQLiteBookmarkDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteBookmarkDAOTest {

    @Test
    void addBookmarkShouldStoreBookmark() {
        SQLiteBookmarkDAO bookmarkDAO = new SQLiteBookmarkDAO();

        int userId = 1;
        int plantId = 1;

        // Remove first in case this bookmark already exists
        bookmarkDAO.removeBookmark(userId, plantId);

        // Add the bookmark
        bookmarkDAO.addBookmark(userId, plantId);

        // Check that it was actually stored
        assertTrue(bookmarkDAO.isBookmarked(userId, plantId));

        // Clean up after the test
        bookmarkDAO.removeBookmark(userId, plantId);
    }

    @Test
    void removeBookmarkShouldDeleteBookmark() {
        SQLiteBookmarkDAO bookmarkDAO = new SQLiteBookmarkDAO();

        int userId = 1;
        int plantId = 1;

        // Start from a known state
        bookmarkDAO.removeBookmark(userId, plantId);

        // Add a bookmark
        bookmarkDAO.addBookmark(userId, plantId);

        // Confirm it exists before removing it
        assertTrue(bookmarkDAO.isBookmarked(userId, plantId));

        // Remove the bookmark
        bookmarkDAO.removeBookmark(userId, plantId);

        // Confirm it no longer exists
        assertFalse(bookmarkDAO.isBookmarked(userId, plantId));
    }

    @Test
    void isBookmarkedShouldReturnCorrectState() {
        SQLiteBookmarkDAO bookmarkDAO = new SQLiteBookmarkDAO();

        int userId = 1;
        int plantId = 1;

        // Start from a known state
        bookmarkDAO.removeBookmark(userId, plantId);

        // It should initially NOT be bookmarked
        assertFalse(bookmarkDAO.isBookmarked(userId, plantId));

        // Add the bookmark
        bookmarkDAO.addBookmark(userId, plantId);

        // It should now be bookmarked
        assertTrue(bookmarkDAO.isBookmarked(userId, plantId));

        // Clean up
        bookmarkDAO.removeBookmark(userId, plantId);
    }
}
