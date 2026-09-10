package model;

public class Plant {
    private int id;
    private String generalisedInfo;
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

    public Plant (String generalisedInfo,
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
                  String species)
    {
        this.generalisedInfo = generalisedInfo;
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
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getImage() {
        return images;
    }

    public void setImage(String image) {
        this.images = image;
    }

    public String getLocation() {
        return stateProvince;
    }

    public void setLocation(String location) {
        this.stateProvince = location;
    }

    public String getSciName() {
        return scientificName;
    }

    public void setSciName(String sciName) {
        this.scientificName = sciName;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getTaxonRank() {
        return taxonRank;
    }

    public void setTaxonRank(String taxonRank) {
        this.taxonRank = taxonRank;
    }

    public String getVernacularName() {
        return vernacularName;
    }

    public void setVernacularName(String vernacularName) {
        this.vernacularName = vernacularName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
