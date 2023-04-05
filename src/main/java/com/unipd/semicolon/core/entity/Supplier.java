package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "previous_orders")
    private String previousOrders;
    @Column(name = "arriving_orders")
    private String arrivingOrders;

    public Supplier(
            String id,
            String name,
            String address,
            String email,
            String telephoneNumber,
            String previousOrders,
            String arrivingOrders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.previousOrders = previousOrders;
        this.arrivingOrders = arrivingOrders;
    }

    public Supplier() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPreviousOrders() {
        return previousOrders;
    }

    public void setPreviousOrders(String previousOrders) {
        this.previousOrders = previousOrders;
    }

    public String getArrivingOrders() {
        return arrivingOrders;
    }

    public void setArrivingOrders(String arrivingOrders) {
        this.arrivingOrders = arrivingOrders;
    }
    
}

