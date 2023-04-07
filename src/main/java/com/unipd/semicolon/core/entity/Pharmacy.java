package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    Long pharmacy_ID;
    @Column(name = "name")
    String name;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "owner")
    User owner;           //user
    @Column(name = "address")
    String address;
    @Column(name = "tell_number")
    String tell_number;
    @OneToMany//(mappedBy = "Pharmacy")
    @JoinColumn(name = "day_of_week")
    List<TimeTable> time_table;
    @Column(name = "logo_path")
    String logo_path;

    @ManyToOne
    @JoinColumn(name = "id_storage")
    Storage storage;

    public Pharmacy() {
    }

    public Pharmacy( String name,
                     User owner,
                     String address,
                     String tell_number,
                     List<TimeTable> time_table,
                     String logo_path) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.tell_number = tell_number;
        this.time_table = time_table;
        this.logo_path = logo_path;
    }

    public Pharmacy(Long pharmacy_ID,
                    String name,
                    User owner,
                    String address,
                    String tell_number,
                    List<TimeTable> time_table,
                    String logo_path) {
        this.pharmacy_ID = pharmacy_ID;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.tell_number = tell_number;
        this.time_table = time_table;
        this.logo_path = logo_path;
    }

    public Long getpharmacy_ID() {
        return pharmacy_ID;
    }

    public void setpharmacy_ID(Long pharmacy_ID) {
        this.pharmacy_ID = pharmacy_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTell_number() {
        return tell_number;
    }

    public void setTell_number(String tell_number) {
        this.tell_number = tell_number;
    }


    public List<TimeTable> getTimeTable() {
        return time_table;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.time_table = timeTable;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }
}
