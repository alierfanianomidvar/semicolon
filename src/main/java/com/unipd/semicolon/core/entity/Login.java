package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "session")
    private boolean session;
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
    @Column(name = "activation_date")
    private LocalDateTime activationDate;
    @Column(name = "termination_date")
    private LocalDateTime terminationDate;


    //to get the list of login information
    public Login(
            String userName,
            String password,
            boolean session,
            LocalDateTime lastLoginDate,
            LocalDateTime activationDate,
            LocalDateTime terminationDate) {
        this.userId = userId;
        this.username = userName;
        this.password = password;
        this.session = session;
        this.lastLoginDate = lastLoginDate;
        this.activationDate = activationDate;
        this.terminationDate = terminationDate;
    }

    // to use in login
    public Login(
            Long userId,
            String userName,
            String password) {
        this.userId = userId;
        this.username = userName;
        this.password = password;
    }

    public Login() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
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