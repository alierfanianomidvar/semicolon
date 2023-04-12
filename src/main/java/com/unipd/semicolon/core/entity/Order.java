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
    public Order(LocalDate orderDate,
                 OrderStatus status,
                 float totalPrice,
                 List<Drug> drugs,
                 List<Material> materials)
    {
        this.orderDate = orderDate;
        this.status = status;
        this.price = totalPrice;
        this.orderDrugs = drugs;
        this.orderMaterials = materials;
    }

    public Order() {
        // No-argument constructor
        //TODO CHANGE TYPE FOR DRUG
    }

    /*-------------------------------------------
    ----------------Getters-----------------
    -------------------------------------------*/
    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus isStatusDelivered() {
        return status;
    }

    public double getTotalPrice() {
        return price;
    }

    public List<Drug> getDrugs() {
        return orderDrugs;
    }

    public List<Material> getMaterials() {
        return orderMaterials;
    }

    public OrderStatus isStatus() {
        return status;
    }

    public float getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

/*-------------------------------------------
    -------------------Setters------------------
    -------------------------------------------*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalPrice(float totalPrice) {
        this.price = totalPrice;
    }

    public void setDrugs(List<Drug> drugs) {
        this.orderDrugs = drugs;
    }

    public void setMaterials(List<Material> materials) {
        this.orderMaterials = materials;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}


