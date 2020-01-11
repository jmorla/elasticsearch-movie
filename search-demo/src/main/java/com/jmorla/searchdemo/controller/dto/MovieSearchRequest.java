package com.jmorla.searchdemo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSearchRequest {

    private String title;
    private String director;
    private String distributor;
    private String type;
    private String productionBudgetOp;
    private double productionBudgetMin;
    private double productionBudgetMax;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductionBudgetOp() {
        return productionBudgetOp;
    }

    public void setProductionBudgetOp(String productionBudgetOp) {
        this.productionBudgetOp = productionBudgetOp;
    }

    public Double getProductionBudgetMin() {
        return productionBudgetMin;
    }

    public void setProductionBudgetMin(Double productionBudgetMin) {
        this.productionBudgetMin = productionBudgetMin;
    }

    public Double getProductionBudgetMax() {
        return productionBudgetMax;
    }

    public void setProductionBudgetMax(Double productionBudgetMax) {
        this.productionBudgetMax = productionBudgetMax;
    }
}
