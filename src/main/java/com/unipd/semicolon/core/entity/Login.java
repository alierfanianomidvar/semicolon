package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "session")
    private boolean session;
    @Column(name = "lastLoginDate")
    private LocalDateTime lastLoginDate;
    @Column(name = "activationDate")
    private LocalDateTime activationDate;
    @Column(name = "terminationDate")
    private LocalDateTime terminationDate;



    //to get the list of login information
    public Login(String userId, String userName, String password, boolean session, LocalDateTime lastLoginDate, LocalDateTime activationDate, LocalDateTime terminationDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.session = session;
        this.lastLoginDate = lastLoginDate;
        this.activationDate = activationDate;
        this.terminationDate = terminationDate;
    }

    // to use in login
    public Login(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Login() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDateTime getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

}