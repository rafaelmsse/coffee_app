package com.applaudo.coffee.app.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Coffee coffee;

    private BigDecimal calculatedPrice;


    public OrderItem() {}

    public OrderItem(UUID id, Order order, Coffee coffee, BigDecimal calculatedPrice) {
        this.id = id;
        this.order = order;
        this.coffee = coffee;
        this.calculatedPrice = calculatedPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public BigDecimal getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }
}
