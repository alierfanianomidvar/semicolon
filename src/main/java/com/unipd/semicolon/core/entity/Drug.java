package com.unipd.semicolon.core.entity;

import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;

    @Column(name = "name",length=50, nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private LocalDate expirationDate;

    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "category")
    private String category;
    @Column(name = "shape")
    private String shape;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;
    @Column(name = "is_sensitive")
    private boolean isSensitive;
    @Column(name = "need_prescription")
    private boolean needPrescription;
    @Column(name = "description")
    private String description;

    //num of drugs that patient can buy
    @Column(name = "limit")
    private int limit;
    @Column(name = "price")
    private float price;
    @Column(name = "country_of_origin")
    private Country countryOFOrigin;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

//- buy price
//- sell price //these are related to each pharmacy

//- quantity //the number of availability in pharmacy
//- isActive //is active in the pharmacy

    public Drug(
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] photo,
            String category,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limit,
            float price,
            Country countryOFOrigin)
    {
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.photo = photo;
        this.category = category;
        this.shape = shape;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.isSensitive = isSensitive;
        this.needPrescription = needPrescription;
        this.description = description;
        this.limit = limit;
        this.price = price;
        this.countryOFOrigin = countryOFOrigin;
    }

    public Drug(
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            String category,
            int limit,
            float price) {
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.category = category;
        this.limit = limit;
        this.price = price;
    }

    public Drug() {

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Country getCountryOFOrigin() {
        return countryOFOrigin;
    }

    public void setCountryOFOrigin(Country countryOFOrigin) {
        this.countryOFOrigin = countryOFOrigin;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

}
