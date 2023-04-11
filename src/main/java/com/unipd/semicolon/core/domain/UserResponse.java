package com.unipd.semicolon.core.domain;

import java.util.Date;

public class UserResponse {
    private String name;
    private String familyName;
    private Date birthday;
    private String role;

    public UserResponse() {
    }
    public UserResponse(
            String name,
            String familyName,
            Date birthday,
            String role) {
        this.name = name;
        this.familyName = familyName;
        this.birthday = birthday;
        this.role = role;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
