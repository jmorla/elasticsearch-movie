package com.jmorla.searchdemo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "movies", type = "movie", createIndex = false)
public class Movie {

    @Id
    private String id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("US_Gross")
    private String usGross;

    @JsonProperty("Worldwide_Gross")
    private String worldwideGross;

    @JsonProperty("US_DVD_Sales")
    private String uSDVDSales;

    @JsonProperty("Production_Budget")
    private String productionBudget;

    @JsonProperty("Release_Date")
    private String releaseDate;

    @JsonProperty("MPAA_Rating")
    private String mPAARating;

    @JsonProperty("Running_Time_min")
    private String runningTimeMin;

    @JsonProperty("Distributor")
    private String distributor;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Major_Genre")
    private String majorGenre;

    @JsonProperty("Creative_Type")
    private String creativeType;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Rotten_Tomatoes_Rating")
    private String rottenTomatoesRating;

    @JsonProperty("IMDB_Rating")
    private String iMDBRating;

    @JsonProperty("IMDB_Votes")
    private String iMDBVotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsGross() {
        return usGross;
    }

    public void setUsGross(String usGross) {
        this.usGross = usGross;
    }

    public String getWorldwideGross() {
        return worldwideGross;
    }

    public void setWorldwideGross(String worldwideGross) {
        this.worldwideGross = worldwideGross;
    }

    public String getuSDVDSales() {
        return uSDVDSales;
    }

    public void setuSDVDSales(String uSDVDSales) {
        this.uSDVDSales = uSDVDSales;
    }

    public String getProductionBudget() {
        return productionBudget;
    }

    public void setProductionBudget(String productionBudget) {
        this.productionBudget = productionBudget;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getmPAARating() {
        return mPAARating;
    }

    public void setmPAARating(String mPAARating) {
        this.mPAARating = mPAARating;
    }

    public String getRunningTimeMin() {
        return runningTimeMin;
    }

    public void setRunningTimeMin(String runningTimeMin) {
        this.runningTimeMin = runningTimeMin;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMajorGenre() {
        return majorGenre;
    }

    public void setMajorGenre(String majorGenre) {
        this.majorGenre = majorGenre;
    }

    public String getCreativeType() {
        return creativeType;
    }

    public void setCreativeType(String creativeType) {
        this.creativeType = creativeType;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }

    public void setRottenTomatoesRating(String rottenTomatoesRating) {
        this.rottenTomatoesRating = rottenTomatoesRating;
    }

    public String getiMDBRating() {
        return iMDBRating;
    }

    public void setiMDBRating(String iMDBRating) {
        this.iMDBRating = iMDBRating;
    }

    public String getiMDBVotes() {
        return iMDBVotes;
    }

    public void setiMDBVotes(String iMDBVotes) {
        this.iMDBVotes = iMDBVotes;
    }
}
