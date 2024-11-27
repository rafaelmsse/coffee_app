package com.applaudo.coffee.app.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PriceHistory {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Coffee coffee;

    private BigDecimal price;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

    public PriceHistory() {}

    public PriceHistory(UUID id, Coffee coffee, BigDecimal price, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.id = id;
        this.coffee = coffee;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }
}
