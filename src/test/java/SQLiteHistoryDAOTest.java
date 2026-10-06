import User.History;
import User.SQLiteHistoryDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteHistoryDAOTest {

    @Test
    void testRecordAndRetrieveHistory() {
        SQLiteHistoryDAO historyDAO = new SQLiteHistoryDAO();

        int userId = 1;
        int plantId = 1;

        historyDAO.clearHistory(userId);
        historyDAO.recordView(userId, plantId);

        List<History> history = historyDAO.getHistory(userId);

        assertFalse(history.isEmpty());
        assertEquals(userId, history.get(0).getUserId());
        assertEquals(plantId, history.get(0).getPlantId());
    }
}
