package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;

public class StorageModel {
    private Long id;
    private Pharmacy pharmacy;
    private Drug drug;
    private int amount;
    private int threshold;

    public StorageModel(Pharmacy pharmacy, Drug drug, int amount, int threshold) {
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.amount = amount;
        this.threshold = threshold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
