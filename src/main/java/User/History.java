package User;

public class History {

    private int historyId;
    private int userId;
    private int plantId;
    private String viewedAt;

    public History(int historyId, int userId, int plantId, String viewedAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.plantId = plantId;
        this.viewedAt = viewedAt;
    }

    public int getHistoryId() {
        return historyId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPlantId() {
        return plantId;
    }

    public String getViewedAt() {
        return viewedAt;
    }

}
