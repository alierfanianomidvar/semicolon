package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    Long pharmacyID;
    String name;
    String owner;           //user
    String address;
    String tellNumber;

    @ManyToOne
    @JoinColumn(name = "dayOfWeek")
    TimeTable timeTable;
    String logoPath;

    public Pharmacy() {
    }

    public Pharmacy( String name, String owner, String address, String tellNumber, TimeTable timeTable, String logoPath) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.tellNumber = tellNumber;
        this.timeTable = timeTable;
        this.logoPath = logoPath;
    }

    public Pharmacy(Long pharmacyID, String name, String owner, String address, String tellNumber, TimeTable timeTable, String logoPath) {
        this.pharmacyID = pharmacyID;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.tellNumber = tellNumber;
        this.timeTable = timeTable;
        this.logoPath = logoPath;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTellNumber() {
        return tellNumber;
    }

    public void setTellNumber(String tellNumber) {
        this.tellNumber = tellNumber;
    }


    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharmacyID=" + pharmacyID +
                ", name='" + name + '\'' +
                ", owner='" + owner.toString() + '\'' +
                ", address='" + address + '\'' +
                ", tellNumber='" + tellNumber + '\'' +
                ", timeTable=" + timeTable.toString() +
                ", logoPath='" + logoPath + '\'' +
                '}';
    }
}
