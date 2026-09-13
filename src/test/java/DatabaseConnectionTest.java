import database.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    public void connectionShouldOpenExistingPlantsDatabase() throws Exception {

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet =
                        statement.executeQuery("SELECT COUNT(*) FROM PlantsData")
        ) {
            assertNotNull(connection);

            resultSet.next();

            int numberOfPlants = resultSet.getInt(1);

            assertEquals(313, numberOfPlants);
        }
    }
}
