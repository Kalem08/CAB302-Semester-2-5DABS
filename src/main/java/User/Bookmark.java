package User;

/**
 * Bookmark is a class that represents a user's bookmarked plant.
 * It contains the user ID and the plant ID associated with the bookmark.
 */
public class Bookmark {

    private int userId;
    private int plantId;

    /**
     * Constructs a new Bookmark object with the specified user ID and plant ID.
     * @param userId The ID of the user who bookmarked the plant
     * @param plantId The ID of the bookmarked plant
     */
    public Bookmark(int userId, int plantId) {
        this.userId = userId;
        this.plantId = plantId;
    }

    /**
     * Returns the ID of the user who bookmarked the plant.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the ID of the bookmarked plant.
     * @return the plant ID
     */
    public int getPlantId() {
        return plantId;
    }
}
