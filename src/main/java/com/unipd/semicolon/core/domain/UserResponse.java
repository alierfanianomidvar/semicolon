package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResponse {
    private String name;
    private String lastName;
    private Gender gender;
    private LocalDateTime birthDate;
    private long phoneNumber;
    private String address;
    private Role role;
    private String email;


    public UserResponse() {
    }
    public UserResponse(
            String name,
            String lastName,
            Gender gender,
            LocalDateTime birthDate,
            long phoneNumber,
            String address,
            Role role,
            String email) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthday() {
        return birthDate;
    }

    public void setBirthday(Date birthday) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}