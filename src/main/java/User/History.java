package User;
/**
 * History is a class that represents a record of a user's viewing history of plants.
 */
public class History {

    private int historyId;
    private int userId;
    private int plantId;
    private String viewedAt;

    /**
     * Constructs a new History object with the specified details.
     * @param historyId The ID of the history record
     * @param userId The ID of the user associated with this history record
     * @param plantId The ID of the plant associated with this history record
     * @param viewedAt The timestamp of when the plant was viewed
     */
    public History(int historyId, int userId, int plantId, String viewedAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.plantId = plantId;
        this.viewedAt = viewedAt;
    }

    /**
     * Returns the ID of the history record.
     * @return the history ID
     */
    public int getHistoryId() {
        return historyId;
    }

    /**
     * Returns the ID of the user associated with this history record.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the ID of the plant associated with this history record.
     * @return the plant ID
     */
    public int getPlantId() {
        return plantId;
    }

    /**
     * Returns the timestamp of when the plant was viewed.
     * @return the viewed at timestamp
     */
    public String getViewedAt() {
        return viewedAt;
    }

}
