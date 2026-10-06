package User;

public class Bookmark {

    private int userId;
    private int plantId;

    public Bookmark(int userId, int plantId) {
        this.userId = userId;
        this.plantId = plantId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPlantId() {
        return plantId;
    }
}
