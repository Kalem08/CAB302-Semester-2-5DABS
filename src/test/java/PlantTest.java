import app.model.Plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlantTest {
    private Plant plant;
    @BeforeEach
    public void setUp() {
        plant = new Plant(
                "Test",
                "0428",
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
                "spec"
        );
    }

    @Test
    public void testGetId() {
        plant.setID(1);
        assertEquals(1, plant.getId());
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
    public void testGetImage() {
        assertEquals("0428", plant.getImage());
    }

    @Test
    public void testGetImageUrl() {
        assertEquals(
                "https://images-assets.ala.org.au/store/4/8/9/f/0428/original",
                plant.getImageUrl()
        );
    }

    @Test
    public void testGetImageUrlReturnsNullForBlankCode() {
        plant.setImage(" ");
        assertNull(plant.getImageUrl());
    }

    @Test
    public void testSetImage() {
        plant.setImage("ImageTest");
        assertEquals("ImageTest", plant.getImage());
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
}
