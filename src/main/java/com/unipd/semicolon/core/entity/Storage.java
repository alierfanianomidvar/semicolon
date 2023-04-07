package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_storage;
    
    @OneToMany
    @JoinColumn(name = "pharmacy_id")
    private List<Pharmacy> pharmacy;

    @OneToMany
    @JoinColumn(name = "id_drug")
    List<Drug> drugList;
    @Column(name = "amount")
    private int amount;
    @Column(name = "threshold")
    private int threshold;

    public Storage (List<Pharmacy> pharmacy,
                    List<Drug> drugList,
                    int amount,
                    int threshold) {

        this.pharmacy = pharmacy;
        this.drugList = drugList;
        this.amount = amount;
        this.threshold = threshold;
    }

    public Storage() {

    }

    public void setId (long id_storage ) {
        this.id_storage = id_storage;
    }
    public long getId () {
        return id_storage;
    }
    public void setPharmacy(List<Pharmacy> pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<Pharmacy> getPharmacy() {
        return pharmacy;
    }

    public void setDrug(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public List<Drug> getDrug() {
        return drugList;
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
