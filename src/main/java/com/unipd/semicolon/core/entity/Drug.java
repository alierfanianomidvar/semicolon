package com.unipd.semicolon.core.entity;

import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;

    @Column(length=50, nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private LocalDate expirationDate;

    private byte[] photo;
    private String category;
    private String shape;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isSensitive;

    private boolean needPrescription;

    private String description;

    //num of drugs that patient can buy
    private int limit;

    private float price;

    private String countryOFOrigin;

    private LocalDateTime lastModified;


//- buy price
//- sell price //these are related to each pharmacy

//- quantity //the number of availability in pharmacy
//- isActive //is active in the pharmacy
//- age //do we really need it?

    public Drug(String name, Supplier supplier, LocalDate expirationDate, byte[] photo, String category,
                String shape, Gender gender, boolean isSensitive, boolean needPrescription, String description,
                int limit, float price, String countryOFOrigin)
    {
        this.name = name;
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.photo = photo;
        this.category = category;
        this.shape = shape;
        this.gender = gender;
        this.isSensitive = isSensitive;
        this.needPrescription = needPrescription;
        this.description = description;
        this.limit = limit;
        this.price = price;
        this.countryOFOrigin = countryOFOrigin;
    }

    public Drug(String name, Supplier supplier, LocalDate expirationDate, String category, int limit, float price) {
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

    public String getCountryOFOrigin() {
        return countryOFOrigin;
    }

    public void setCountryOFOrigin(String countryOFOrigin) {
        this.countryOFOrigin = countryOFOrigin;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier=" + supplier +
                ", expirationDate=" + expirationDate +
                ", photo=" + Arrays.toString(photo) +
                ", category='" + category + '\'' +
                ", shape='" + shape + '\'' +
                ", gender=" + gender +
                ", isSensitive=" + isSensitive +
                ", needPrescription=" + needPrescription +
                ", description='" + description + '\'' +
                ", limit=" + limit +
                ", price=" + price +
                ", countryOFOrigin='" + countryOFOrigin + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}
