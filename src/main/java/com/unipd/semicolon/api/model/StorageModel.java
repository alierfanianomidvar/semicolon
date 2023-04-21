package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;

public class StorageModel {
    private Long id;
    private Pharmacy pharmacy;
    private Material material;
    private Drug drug;
    private int amount;
    private int threshold;

    private double discount;

    public StorageModel(Pharmacy pharmacy,
                        Material material,
                        Drug drug,
                        int amount,
                        int threshold,
                        double discount) {
        this.pharmacy = pharmacy;
        this.drug = drug;
        this.material = material;
        this.amount = amount;
        this.threshold = threshold;
        this.discount = discount;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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

    public double getDiscount() {return discount;}

    public void setDiscount(double discount) {this.discount = discount;}
}