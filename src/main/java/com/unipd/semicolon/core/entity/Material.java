package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    @Column(name = "country_of_production")
    private Country countryOfProduction;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "image")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public Gender gender;

    @Column(name = "price")
    private float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;


    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderMaterials")
    private List<Order> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "receiptMaterials")
    private List<Receipt> receipts;


    public Material() {

    }

    public Material(
            String materialName,
            Supplier supplierId,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup age,
            float price,
            String description,
            Country countryOfProduction) {
        this.name = materialName;
        this.supplier = supplierId;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.ageGroup = age;
        this.description = description;
        this.price = price;
        this.countryOfProduction = countryOfProduction;

    }


    // Getter methods
    public Long getId() {
        return id;
    }

    public String getMaterialName() {
        return name;
    }

    public Supplier getSupllierId() {
        return supplier;
    }

    public Country getcountryOfProduction() {
        return countryOfProduction;
    }

    public LocalDate getExpriationDate() {
        return expirationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public Gender getGender() {
        return gender;
    }

    public float getSellPrice() {
        return price;
    }

    public AgeGroup getAge() {
        return ageGroup;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    // Setter methods
    public void setId(Long id) {
        this.id = id;
    }

    public void setMaterialName(String materialName) {
        this.name = materialName;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplier = supplierId;
    }

    public void setCountryOfProduction(Country countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public void setExpriationDate(LocalDate expriationDate) {
        this.expirationDate = expriationDate;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setSellPrice(float sellPrice) {
        this.price = sellPrice;
    }

    public void setAge(AgeGroup age) {
        this.ageGroup = age;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

