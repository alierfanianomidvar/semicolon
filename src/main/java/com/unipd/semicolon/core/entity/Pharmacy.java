package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "owner")
    private User owner;           //user
    @Column(name = "address")
    private String address;
    @Column(name = "telephoneNumber")
    private String telephoneNumber;
    @OneToMany//(mappedBy = "Pharmacy")
    @JoinColumn(name = "day_of_week")
    private List<TimeTable> time_table;

    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(mappedBy = "pharmacy")
    private List<Storage> storage;

    //todo

    @Column(name = "staff")
    @OneToMany(mappedBy = "")
    private List<User> staff;

    public Pharmacy() {
    }

    public Pharmacy( String name,
                     User owner,
                     String address,
                     String telephoneNumber,
                     List<TimeTable> time_table,
                     byte[] logo_path) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.time_table = time_table;
        this.logo = logo_path;
    }

    public Pharmacy(Long id,
                    String name,
                    User owner,
                    String address,
                    String telephoneNumber,
                    List<TimeTable> time_table,
                    byte[] logo_path) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.time_table = time_table;
        this.logo = logo_path;
    }

    public Long getpharmacy_ID() {
        return id;
    }

    public void setpharmacy_ID(Long pharmacy_ID) {
        this.id = pharmacy_ID;
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
        return telephoneNumber;
    }

    public void setTell_number(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    public List<TimeTable> getTimeTable() {
        return time_table;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.time_table = timeTable;
    }

    public byte[] getLogo_path() {
        return logo;
    }

    public void setLogo_path(byte[] logo_path) {
        this.logo = logo_path;
    }
}
