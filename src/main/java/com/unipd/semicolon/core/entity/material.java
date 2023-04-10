package com.unipd.semicolon.core.entity;

import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "Material")
public class material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id
    private Long id;

    @Column(name = "material_name")
    private String materialName;

    @JoinColumn(name = "supplier_id")
    private String supplier;


    @Column(name = "country_of_production")
    private Country countryOfProduction;

    //@Column(name = "expiration")
    private LocalDate expirationDate;

    @Column(name = "image")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public Gender gender;

    @Column(name = "sell_price")
    private double sellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "age")
    private AgeGroup age;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;


    @Column(name = "description")
    private String description;


    public material() {

    }

    public material(
            String materialName,
            String supplierId,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup age,
            float sellPrice,
            String description,
            Country countryOfProduction) {
        this.materialName = materialName;
        this.supplier = supplierId;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.sellPrice = sellPrice;
        this.countryOfProduction = countryOfProduction;

    }


    // Getter methods
    public Long getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getSupllierId() {
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

    public double getSellPrice() {
        return sellPrice;
    }

    public AgeGroup getAge() {
        return age;
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
        this.materialName = materialName;
    }

    public void setSupplierId(String supplierId) {
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

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setAge(AgeGroup age) {
        this.age = age;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

