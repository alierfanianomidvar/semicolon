package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDateTime;
import java.util.Date;

public class UserModel {
    private Long userId;

    private String username;

    private String password;

    private String name;

    private String lastName;

    private LocalDateTime birthDate;

    private Role role;

    private Gender gender;

    private Long phoneNumber;

    private String address;

    private String email;

    private String accountStatus;

    private byte[] profilePicture;

    public UserModel(Long userId,
            String username,
            String password,
            String name,
            String lastName,
            LocalDateTime birthDate,
            Role role,
            Gender gender,
            Long phoneNumber,
            String address,
            String email,
            String accountStatus,
            byte[] profilePicture) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.accountStatus = accountStatus;
        this.profilePicture = profilePicture;
    }

    public UserModel(String username,
            String password,
            String name,
            String lastName,
            LocalDateTime birthDate,
            Role role,
            Gender gender,
            Long phoneNumber,
            String address,
            String email,
            String accountStatus,
            byte[] profilePicture) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.accountStatus = accountStatus;
        this.profilePicture = profilePicture;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

}
