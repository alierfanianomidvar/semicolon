package com.unipd.semicolon.core.entity;

import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id
    private Long id;

    @Column(name = "material_name")
    private String materialName;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "country_of_production")
    private Country countryOfProduction;

    @Column(name = "expiration")
    private LocalDate expirationDate;

    @Column(name = "image")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public Gender gender;

    @Column(name = "sell_price")
    private float sellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "age")
    private AgeGroup age;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;


    @Column(name = "description")
    private String description;

    @Column(name = "buy_price")
    private float buyPrice;

    @Column(name = "amount")
    private int amount;

    @Column(name = "isActive")
    private boolean isActive;


    /*------------------------------
    ---------- Constructor ---------
    ------------------------------*/
    public Material(
            String materialName,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup age,
            float sellPrice,
            float buyPrice,
            int amount,
            boolean isActive,
            String description,
            Country countryOfProduction) {
        this.materialName = materialName;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.amount = amount;
        this.isActive = isActive;
        this.countryOfProduction = countryOfProduction;
    }


    public Material() {

    }


    /*--------------------------------------
    ------------- Getter methods------------
    --------------------------------------*/
    public Long getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public Supplier getSupllier() {
        return supplier;
    }

    public Country getcountryOfProduction() {
        return countryOfProduction;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public Gender getGender() {
        return gender;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public float getBuyPrice() {return buyPrice;}

    public AgeGroup getAge() {
        return age;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getAmount() {
        return amount;
    }


    /*--------------------------------------
    ------------- Setter methods------------
    --------------------------------------*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCountryOfProduction(Country countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = sellPrice;
    }
    public void setAge(AgeGroup age) {
        this.age = age;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

