package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pharmacy")
    private Pharmacy pharmacy;

    @OneToOne
    private Drug drug;

    @OneToOne
    private Material material;

    @Column(name = "amount")
    private int amount;
    @Column(name = "threshold")
    private int threshold;

    public Storage(Pharmacy pharmacy,
                   Drug drug,
                   int amount,
                   int threshold) {

        this.pharmacy = pharmacy;
        this.drug = drug;
        this.amount = amount;
        this.threshold = threshold;
    }

    public Storage() {

    }

    public void setId(Long id_storage) {
        this.id = id_storage;
    }

    public Long getId() {
        return id;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }


}
