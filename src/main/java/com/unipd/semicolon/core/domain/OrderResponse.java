package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {
    private Long id;
    private LocalDate orderDate;
    private List<Drug> orderDrugs;
    private List<Material> orderMaterials;
    private OrderStatus status;
    private float price;
    private boolean isActive;

    public OrderResponse(
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Drug> getOrderDrugs() {
        return orderDrugs;
    }

    public List<Material> getOrderMaterials() {
        return orderMaterials;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public float getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDrugs(List<Drug> orderDrugs) {
        this.orderDrugs = orderDrugs;
    }

    public void setOrderMaterials(List<Material> orderMaterials) {
        this.orderMaterials = orderMaterials;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
