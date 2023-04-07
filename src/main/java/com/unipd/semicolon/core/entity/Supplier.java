package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id_supplier;
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

    @OneToMany
    @JoinColumn(name = "id")
    List<Drug> drugList;

    public Supplier(
            String id_supplier,
            String name,
            String address,
            String email,
            String telephoneNumber,
            String previousOrders,
            String arrivingOrders,
            List<Drug> drugList) {
        this.id_supplier = id_supplier;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.previousOrders = previousOrders;
        this.arrivingOrders = arrivingOrders;
        this.drugList=drugList;
    }

    public Supplier() {
    }

    public String getId() {
        return id_supplier;
    }

    public void setId(String id) {
        this.id_supplier = id_supplier;
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


    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }
}

