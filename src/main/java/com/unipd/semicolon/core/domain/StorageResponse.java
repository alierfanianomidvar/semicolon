package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;

public class StorageResponse {

    private Long id;
    private Pharmacy pharmacy;
    private Drug drug;

    private Material material;
    private int amount;
    private int threshold;

    public StorageResponse(Long id, Pharmacy pharmacy, Drug drug, Material material, int amount, int threshold) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.material = material;
        this.amount = amount;
        this.threshold = threshold;
    }

    public Long getId() {
        return id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Drug getDrug() {
        return drug;
    }

    public Material getMaterial() {return material;}

    public int getAmount() {
        return amount;
    }

    public int getThreshold() {
        return threshold;
    }

    // optional setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public void setMaterial(Material material) {this.material = material;}

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}

