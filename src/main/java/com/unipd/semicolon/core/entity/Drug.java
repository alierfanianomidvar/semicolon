package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "image")
    private byte[] image;

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
    @Column(name = "limitation")
    private int limitation;
    @Column(name = "price")
    private float price;
    @Column(name = "country_of_production")
    private Country countryOFProduction;
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderDrugs")
    private List<Order> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "receiptDrugs")
    private List<Receipt> receipts;


    public Drug() {

    }

    public Drug(
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] photo,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limit,
            float price,
            Country countryOFOrigin) {
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.image = photo;
        this.shape = shape;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.isSensitive = isSensitive;
        this.needPrescription = needPrescription;
        this.description = description;
        this.limitation = limit;
        this.price = price;
        this.countryOFProduction = countryOFOrigin;
    }

//    public Drug(
//            String name,
//            Supplier supplier,
//            LocalDate expirationDate,
//            String category,
//            int limit,
//            float price) {
//        this.name = name;
//        this.supplier = supplier;
//        this.expirationDate = expirationDate;
//        this.category = category;
//        this.limit = limit;
//        this.price = price;
//    }


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
        return image;
    }

    public void setPhoto(byte[] photo) {
        this.image = photo;
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
        return limitation;
    }

    public void setLimit(int limit) {
        this.limitation = limit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Country getCountryOFOrigin() {
        return countryOFProduction;
    }

    public void setCountryOFOrigin(Country countryOFOrigin) {
        this.countryOFProduction = countryOFOrigin;
    }

    public LocalDateTime getLastModified() {
        return lastModifiedDate;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModifiedDate = lastModified;
    }

}
