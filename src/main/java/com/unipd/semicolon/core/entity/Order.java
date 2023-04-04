package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private boolean status;

    @Column(name = "totalPrice")
    private double totalPrice;

    /*-------------------------------------------
    ----------------Constructors-----------------
    -------------------------------------------*/
    public Order(LocalDate orderDate,
                 boolean status,
                 double totalPrice)
    {
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
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

    public boolean isStatusDelivered() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}


