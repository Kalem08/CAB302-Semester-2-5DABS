package User;

import java.util.List;

public interface IHistoryDAO {

    void recordView(int userId, int plantId);

    List<History> getHistory(int userId);

    void clearHistory(int userId);
}
