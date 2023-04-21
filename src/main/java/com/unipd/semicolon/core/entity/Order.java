package com.unipd.semicolon.core.entity;

import com.unipd.semicolon.core.entity.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToMany
    private List<Drug> orderDrugs;

    @ManyToMany
    private List<Material> orderMaterials;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "price")
    private float price;

    //just for soft deleting
    @Column(name = "is_active")
    private boolean isActive;


    /*-------------------------------------------
    ----------------Constructors-----------------
    -------------------------------------------*/

    public Order() {

    }

    public Order(
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive) {
        this.orderDate = orderDate;
        this.orderDrugs = orderDrugs;
        this.orderMaterials = orderMaterials;
        this.status = status;
        this.price = price;
        this.isActive = isActive;
    }


    /*-------------------------------------------
        ----------------Getters and Setters-----------------
        -------------------------------------------*/

    public Long getId() {
        return id;
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


