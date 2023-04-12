package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;

public class MaterialModel {
    private Long id;
    private String name;
    private Supplier supplier;
    private LocalDate expirationDate;
    private byte[] image;
    private Gender gender;
    private AgeGroup age;
    private float sellPrice;
    private float buyPrice;
    private int amount;
    private boolean active;
    private String description;
    private Country countryOfProduction;

    public MaterialModel() {
    }

    public MaterialModel(
            Long id,
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup ageGroup,
            double sellPrice,
            double buyPrice,
            int amount,
            boolean active,
            String description,
            Country countryOfProduction) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.image = image;
        this.gender = gender;
        this.age = ageGroup;
        this.sellPrice = (float) sellPrice;
        this.buyPrice = (float) buyPrice;
        this.amount = amount;
        this.active = active;
        this.description = description;
        this.countryOfProduction = countryOfProduction;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Gender getGender() {
        return gender;
    }

    public AgeGroup getAgeGroup() {
        return age;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public Country getCountryOfProduction() {
        return countryOfProduction;
    }
}