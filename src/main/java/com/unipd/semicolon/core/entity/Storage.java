package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToMany
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @OneToMany
    @JoinColumn(name = "drug_id")
    private Drug drug;
    @Column(name = "amount")
    private int amount;
    @Column(name = "threshold")
    private int threshold;

    public Storage (Pharmacy pharmacy,
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

    public void setId (long id ) {
        this.id = id;
    }
    public long getId () {
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
