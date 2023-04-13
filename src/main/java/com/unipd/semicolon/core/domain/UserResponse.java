package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.util.Date;

public class UserResponse {
    private String name;
    private String familyName;
    private Enum<Gender> gender;
    private Date birthDate;
    private String phoneNumber;
    private String address;
    private Role role;
    private String email;

    public UserResponse() {
    }
    public UserResponse(
            String name,
            String familyName,
            Enum<Gender> gender,
            Date birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email) {
        this.name = name;
        this.familyName = familyName;
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

    public Enum<Gender> getGender() {
        return gender;
    }

    public void setGender(Enum<Gender> gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getBirthday() {
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
