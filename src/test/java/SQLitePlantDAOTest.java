import java.util.List;
import app.model.Plant;
import app.model.SQLitePlantDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SQLitePlantDAOTest {

    @Test
    public void getPlantShouldReturnPlantFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);
        assertEquals(1, plant.getId());
        assertEquals("Gungurru", plant.getVernacularName());
        assertEquals("Eucalyptus caesia", plant.getSciName());
    }

    @Test
    public void getAllPlantsShouldReturnAllPlantsFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        List<Plant> plants = dao.getAllPlants();

        assertNotNull(plants);
        assertEquals(313, plants.size());
    }

    @Test
    public void searchShouldSupportTwentyPlantPages() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        List<Plant> firstPage = dao.search("", 20, 0);
        List<Plant> secondPage = dao.search("", 20, 20);

        assertEquals(313, dao.count(""));
        assertEquals(20, firstPage.size());
        assertEquals(20, secondPage.size());
        assertEquals(1, firstPage.get(0).getId());
        assertEquals(21, secondPage.get(0).getId());
    }
}