package app.model;

/**
 * Plant is a custom type that contains all of the details of a plant.
 */
public class Plant {
    private int id;
    private String generalisedInfo;
    private String growthInfo;
    private String images;
    private String stateProvince;
    private String scientificName;
    private String kingdom;
    private String phylum;
    private String pclass;
    private String order;
    private String family;
    private String genus;
    private String taxonRank;
    private String vernacularName;
    private String species;
    private String edible;
    private String hazardous;
    private String subspecies;

    //MARK: Constructor
    public Plant (String generalisedInfo,
                  String growthInfo,
                  String images,
                  String stateProvince,
                  String scientificName,
                  String kingdom,
                  String phylum,
                  String pclass,
                  String order,
                  String family,
                  String genus,
                  String taxonRank,
                  String vernacularName,
                  String species,
                  String edible,
                  String hazardous,
                  String subspecies)
    {
        this.generalisedInfo = generalisedInfo;
        this.growthInfo = growthInfo;
        this.images = images;
        this.stateProvince = stateProvince;
        this.scientificName = scientificName;
        this.kingdom = kingdom;
        this.phylum = phylum;
        this.pclass = pclass;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.taxonRank = taxonRank;
        this.vernacularName = vernacularName;
        this.species = species;
        this.edible = edible;
        this.hazardous = hazardous;
        this.subspecies = subspecies;
    }

    /**
     * Gets the ID value of this plant.
     * @return ID integer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID value of this plant.
     * @param id The new ID
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Returns the description of this plant.
     * @return The description
     */
    public String getGeneralisedInfo() {
        return generalisedInfo;
    } 

    /**
     * Sets the description of this plant.
     * @param generalisedInfo The new description
     */
    public void setGeneralisedInfo(String generalisedInfo) {
        this.generalisedInfo = generalisedInfo;
    }

    /**
     * Returns the growth information of this plant.
     * @return The growth information
     */
    public String getGrowthInfo() {
        return growthInfo;
    }

    /**
     * Sets the growth information of this plant.
     * @param growthInfo The new growth information
     */
    public void setGrowthInfo(String growthInfo) {
        this.growthInfo = growthInfo;
    }

    /**
     * Returns the image information of this plant.
     * @return The image information
     */
    public String getImage() {
        return images;
    }

    /**
     * Sets the image information of this plant.
     * @param image The new image information
     */
    public void setImage(String image) {
        this.images = image;
    }

    /**
     * Returns the location of this plant.
     * @return The location
     */
    public String getLocation() {
        return stateProvince;
    }

    /**
     * Sets the location of this plant.
     * @param location The new location
     */
    public void setLocation(String location) {
        this.stateProvince = location;
    }

    /**
     * Returns the scientific name of this plant.
     * @return The scientific name
     */
    public String getSciName() {
        return scientificName;
    }

    /**
     * Sets the scientific name of this plant.
     * @param sciName The new scientific name
     */
    public void setSciName(String sciName) {
        this.scientificName = sciName;
    }

    /**
     * Returns the kingdom of this plant.
     * @return The kingdom
     */
    public String getKingdom() {
        return kingdom;
    }

    /**
     * Sets the kingdom of this plant.
     * @param kingdom The new kingdom
     */
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    /**
     * Returns the phylum of this plant.
     * @return The phylum
     */
    public String getPhylum() {
        return phylum;
    }

    /**
     * Sets the phylum of this plant.
     * @param phylum The new phylum
     */
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    /**
     * Returns the class of this plant.
     * @return The class
     */
    public String getPclass() {
        return pclass;
    }

    /**
     * Sets the class of this plant.
     * @param pclass The new class
     */
    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    /**
     * Returns the order of this plant.
     * @return The order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the order of this plant.
     * @param order The new order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Returns the family of this plant.
     * @return The family
     */
    public String getFamily() {
        return family;
    }

    /**
     * Sets the family of this plant.
     * @param family The new family
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * Returns the genus of this plant.
     * @return The genus
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Sets the genus of this plant.
     * @param genus The new genus
     */
    public void setGenus(String genus) {
        this.genus = genus;
    }

    /**
     * Returns the taxon rank of this plant.
     * @return The taxon rank
     */
    public String getTaxonRank() {
        return taxonRank;
    }

    /**
     * Sets the taxon rank of this plant.
     * @param taxonRank The new taxon rank
     */
    public void setTaxonRank(String taxonRank) {
        this.taxonRank = taxonRank;
    }

    /**
     * Returns the vernacular name of this plant.
     * @return The vernacular name
     */
    public String getVernacularName() {
        return vernacularName;
    }

    /**
     * Sets the vernacular name of this plant.
     * @param vernacularName The new vernacular name
     */
    public void setVernacularName(String vernacularName) {
        this.vernacularName = vernacularName;
    }

    /**
     * Returns the species of this plant.
     * @return The species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of this plant.
     * @param species The new species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Returns the edibility status of this plant.
     * @return The edibility status
     */
    public String getEdible() {
        return edible;
    }

    /**
     * Sets the edibility status of this plant.
     * @param edible The new edibility status
     */
    public void setEdible(String edible) {
        this.edible = edible;
    }

    /**
     * Returns the hazard status of this plant.
     * @return The hazard status
     */
    public String getHazardous() {
        return hazardous;
    }

    /**
     * Sets the hazard status of this plant.
     * @param hazardous The new hazard status
     */
    public void setHazardous(String hazardous) {
        this.hazardous = hazardous;
    }

    /**
     * Returns the subspecies of this plant.
     * @return The subspecies
     */
    public String getSubspecies() {
        return subspecies;
    }

    /**
     * Sets the subspecies of this plant.
     * @param subspecies The new subspecies
     */
    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }
}
