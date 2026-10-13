import app.model.Plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlantTest {
    private Plant plant;
    @BeforeEach
    public void setUp() {
        plant = new Plant(
                "Test",
                "Test",
                "https://images.ala.org.au/image/proxyImage?imageId=0428",
                "QLD",
                "TestSci",
                "King",
                "phy",
                "class",
                "order",
                "fam",
                "gen",
                "tax",
                "vern",
                "spec",
                null,
                null,
                null
        );
    }

    @Test
    public void testGetId() {
        plant.setID(1);
        assertEquals(1, plant.getId());
    }

    @Test
    public void testGetGrowthInfo() {
        assertEquals("Test", plant.getGrowthInfo());
    }
    @Test
    public void testSetGrowthInfo() {
        plant.setGrowthInfo("GrowthTest");
        assertEquals("GrowthTest", plant.getGrowthInfo());
    }

    @Test
    public void testAdditionalInfoFields() {
        plant.setEdible("yes");
        plant.setHazardous("no");
        plant.setSubspecies("yes");

        assertEquals("yes", plant.getEdible());
        assertEquals("no", plant.getHazardous());
        assertEquals("yes", plant.getSubspecies());
    }

    @Test
    public void testGetGeneralisedInfo() {
        assertEquals("Test", plant.getGeneralisedInfo());
    }
    @Test
    public void testSetGeneralisedInfo() {
        plant.setGeneralisedInfo("GenTest");
        assertEquals("GenTest", plant.getGeneralisedInfo());
    }

    @Test
    public void testGetImageReturnsStoredUrl() {
        assertEquals(
                "https://images.ala.org.au/image/proxyImage?imageId=0428",
                plant.getImage()
        );
    }

    @Test
    public void testSetImage() {
        plant.setImage("https://images.ala.org.au/image/proxyImage?imageId=test");
        assertEquals(
            "https://images.ala.org.au/image/proxyImage?imageId=test",
            plant.getImage()
        );
    }

    @Test
    public void testGetLocation() {
        assertEquals("QLD", plant.getLocation());
    }
    @Test
    public void testSetLocation() {
        plant.setLocation("NSW");
        assertEquals("NSW", plant.getLocation());
    }

    @Test
    public void testGetSciName() {
        assertEquals("TestSci", plant.getSciName());
    }
    @Test
    public void testSetSciName() {
        plant.setSciName("SciTest");
        assertEquals("SciTest", plant.getSciName());
    }

    @Test
    public void testGetKingdom() {
        assertEquals("King", plant.getKingdom());
    }
    @Test
    public void testSetKingdom() {
        plant.setKingdom("KingTest");
        assertEquals("KingTest", plant.getKingdom());
    }

    @Test
    public void testGetPhylum() {
        assertEquals("phy", plant.getPhylum());
    }
    @Test
    public void testSetPhylum() {
        plant.setPhylum("PhylumTest");
        assertEquals("PhylumTest", plant.getPhylum());
    }

    @Test
    public void testGetPclass() {
        assertEquals("class", plant.getPclass());
    }
    @Test
    public void testSetPclass() {
        plant.setPclass("ClassTest");
        assertEquals("ClassTest", plant.getPclass());
    }

    @Test
    public void testGetOrder() {
        assertEquals("order", plant.getOrder());
    }
    @Test
    public void testSetOrder() {
        plant.setOrder("OrderTest");
        assertEquals("OrderTest", plant.getOrder());
    }

    @Test
    public void testGetFamily() {
        assertEquals("fam", plant.getFamily());
    }
    @Test
    public void testSetFamily() {
        plant.setFamily("FamilyTest");
        assertEquals("FamilyTest", plant.getFamily());
    }

    @Test
    public void testGetGenus() {
        assertEquals("gen", plant.getGenus());
    }
    @Test
    public void testSetGenus() {
        plant.setGenus("GenusTest");
        assertEquals("GenusTest", plant.getGenus());
    }

    @Test
    public void testGetTaxonRank() {
        assertEquals("tax", plant.getTaxonRank());
    }
    @Test
    public void testSetTaxonRank() {
        plant.setTaxonRank("TaxonTest");
        assertEquals("TaxonTest", plant.getTaxonRank());
    }

    @Test
    public void testGetVernacularName() {
        assertEquals("vern", plant.getVernacularName());
    }
    @Test
    public void testSetVernacularName() {
        plant.setVernacularName("VernacularTest");
        assertEquals("VernacularTest", plant.getVernacularName());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("spec", plant.getSpecies());
    }
    @Test
    public void testSetSpecies() {
        plant.setSpecies("SpeciesTest");
        assertEquals("SpeciesTest", plant.getSpecies());
    }
    // Unit test edge case - Checks that a Plant object correctly stores and returns an empty image value
    @Test
    public void testEmptyImageIsStoredCorrectly() {
        // Arrange
        plant.setImage("");

        // Act
        String image = plant.getImage();

        // Assert
        assertEquals("", image);
    }
    // Unit test Object State - Checks that changing a Plant's vernacular name
    // does not change its scientific name
    @Test
    public void changingVernacularNameShouldNotChangeScientificName() {
        // Arrange
        String originalScientificName = plant.getSciName();

        // Act
        plant.setVernacularName("Updated Common Name");

        // Assert
        assertEquals("Updated Common Name", plant.getVernacularName());
        assertEquals(originalScientificName, plant.getSciName());
    }
    // Unit test Object State - Checks that multiple updates to a Plant's vernacular name
    // correctly retain the most recently assigned value
    @Test
    public void multipleVernacularNameUpdatesShouldRetainLatestValue() {

        // Arrange
        plant.setVernacularName("First Common Name");

        // Act
        plant.setVernacularName("Second Common Name");
        plant.setVernacularName("Final Common Name");

        // Assert
        assertEquals("Final Common Name", plant.getVernacularName());
    }
    // Unit test Object State - Checks that changing a Plant's location
    // does not modify its existing taxonomy information
    @Test
    public void changingLocationShouldNotChangeTaxonomyInformation() {

        // Arrange
        String originalKingdom = plant.getKingdom();
        String originalFamily = plant.getFamily();
        String originalGenus = plant.getGenus();
        String originalSpecies = plant.getSpecies();

        // Act
        plant.setLocation("NSW");

        // Assert
        assertEquals("NSW", plant.getLocation());
        assertEquals(originalKingdom, plant.getKingdom());
        assertEquals(originalFamily, plant.getFamily());
        assertEquals(originalGenus, plant.getGenus());
        assertEquals(originalSpecies, plant.getSpecies());
    }
    // Unit test Edge Case - Checks that a Plant object can store and return
    // an empty vernacular name without affecting the object
    @Test
    public void emptyVernacularNameShouldBeStoredCorrectly() {

        // Arrange
        plant.setVernacularName("");

        // Act
        String vernacularName = plant.getVernacularName();

        // Assert
        assertNotNull(vernacularName);
        assertEquals("", vernacularName);
    }
    // Unit test Edge Case - Checks that a Plant object can store and return
    // a null image value without affecting the object
    @Test
    public void nullImageShouldBeStoredCorrectly() {

        // Arrange
        plant.setImage(null);

        // Act
        String image = plant.getImage();

        // Assert
        assertNull(image);
    }
    // Unit test Boundary Case - Checks that a Plant object correctly stores
    // and returns an ID value of zero
    @Test
    public void zeroPlantIDShouldBeStoredCorrectly() {

        // Arrange
        plant.setID(0);

        // Act
        int plantID = plant.getId();

        // Assert
        assertEquals(0, plantID);
    }
    // Unit test String Handling - Checks that a Plant object correctly stores
    // and returns a vernacular name containing multiple words and spaces
    @Test
    public void multiWordVernacularNameShouldBeStoredCorrectly() {

        // Arrange
        plant.setVernacularName("Moreton Bay Fig");

        // Act
        String vernacularName = plant.getVernacularName();

        // Assert
        assertEquals("Moreton Bay Fig", vernacularName);
    }
    // Unit test String Handling - Checks that a Plant object correctly stores
    // and returns a scientific name containing multiple words
    @Test
    public void multiWordScientificNameShouldBeStoredCorrectly() {

        // Arrange
        plant.setSciName("Eucalyptus tereticornis");

        // Act
        String scientificName = plant.getSciName();

        // Assert
        assertEquals("Eucalyptus tereticornis", scientificName);
    }
    // Unit test Object State - Checks that multiple taxonomy fields can be updated
    // while an unrelated Plant field retains its original value
    @Test
    public void multipleTaxonomyUpdatesShouldNotChangeVernacularName() {

        // Arrange
        String originalVernacularName = plant.getVernacularName();

        // Act
        plant.setFamily("Myrtaceae");
        plant.setGenus("Eucalyptus");
        plant.setSpecies("Eucalyptus tereticornis");

        // Assert
        assertEquals("Myrtaceae", plant.getFamily());
        assertEquals("Eucalyptus", plant.getGenus());
        assertEquals("Eucalyptus tereticornis", plant.getSpecies());
        assertEquals(originalVernacularName, plant.getVernacularName());
    }
}
