package User;

public interface IBookmarkDAO {

    void addBookmark(int userId, int plantId);

    void removeBookmark(int userId, int plantId);

    boolean isBookmarked(int userId, int plantId);
}
