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
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @OneToMany(mappedBy = "supplier")
    private List<Drug> drugs;

    @OneToMany(mappedBy = "supplier")
    private List<Material> materials;

    public Supplier(
            String name,
            String address,
            String email,
            String telephoneNumber,
            List<Drug> drugList) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.drugs=drugList;
    }

    public Supplier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Drug> getDrugList() {
        return drugs;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugs = drugList;
    }
}

