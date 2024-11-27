package com.applaudo.coffee.app.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Additional {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private BigDecimal cost;

    private BigDecimal combineCost;

    public Additional() {}

    public Additional(UUID id, String description, BigDecimal cost, BigDecimal combineCost) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.combineCost = combineCost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCombineCost() {
        return combineCost;
    }

    public void setCombineCost(BigDecimal combineCost) {
        this.combineCost = combineCost;
    }
}
