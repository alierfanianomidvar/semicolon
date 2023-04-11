package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.unipd.semicolon.base.resource.AbstractResource;
import jakarta.persistence.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier extends AbstractResource {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id_supplier;
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
            String name,
            String address,
            String email,
            String telephoneNumber,
            String previousOrders,
            String arrivingOrders,
            List<Drug> drugList) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.previousOrders = previousOrders;
        this.arrivingOrders = arrivingOrders;
        this.drugList=drugList;
    }

    public Supplier(
            String name,
            String address,
            String email,
            String telephoneNumber,
            String previousOrders,
            String arrivingOrders) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.previousOrders = previousOrders;
        this.arrivingOrders = arrivingOrders;
    }

    public Supplier() {
    }

    public Long getId() {
        return id_supplier;
    }

    public void setId(Long id) {
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

    @Override
    protected void writeJSON(OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("supplier");

        jg.writeStartObject();

        jg.writeNumberField("id", id_supplier);

        jg.writeStringField("name", name);

        jg.writeStringField("address", address);

        jg.writeStringField("email", email);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }
}

