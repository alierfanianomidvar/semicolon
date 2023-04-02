package com.unipd.semicolon.core.entity;

import jakarta.persistence.Entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Entity
public class User {
    private String id;
    private String name;
    private String lastName;
    private String gender;
    private LocalDateTime birthDate;
    private Long phoneNumber;
    private String address;
    private String role;
    private String email;
    private String accountStatus;
    private BufferedImage profilePicture;

    public User(String id, String name, String lastName, String gender, LocalDateTime birthDate, Long phoneNumber, String address, String role, String email, String accountStatus, BufferedImage profilePicture) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.email = email;
        this.accountStatus = accountStatus;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public BufferedImage getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(BufferedImage profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User createUser(String id, String name, String lastName, String gender, LocalDateTime birthDate, Long phoneNumber, String address, String role, String email, String accountStatus, BufferedImage profilePicture) {
        return new User(id, name, lastName, gender, birthDate, phoneNumber, address, role, email, accountStatus, profilePicture);
    }

    public User getUser() {
        // TODO
        return null;
    }

    public void editUser() {
        // TODO
    }

    public void deleteUser() {
        // TODO
    }

}
