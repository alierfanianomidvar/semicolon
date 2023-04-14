package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderModel {
    private Long id;
    private LocalDate orderDate;
    private List<Drug> orderDrugs;
    private List<Material> orderMaterials;
    private OrderStatus status;
    private float price;
    private boolean isActive;

    public OrderModel(
            Long id,
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderDrugs = orderDrugs;
        this.orderMaterials = orderMaterials;
        this.status = status;
        this.price = price;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Drug> getOrderDrugs() {
        return orderDrugs;
    }

    public void setOrderDrugs(List<Drug> orderDrugs) {
        this.orderDrugs = orderDrugs;
    }

    public List<Material> getOrderMaterials() {
        return orderMaterials;
    }

    public void setOrderMaterials(List<Material> orderMaterials) {
        this.orderMaterials = orderMaterials;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
