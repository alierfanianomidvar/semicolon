package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserNameOrPasswordNotExitsException extends CustomException {

    private String username;
    private String password;

    public UserNameOrPasswordNotExitsException(
            String username,
            String password) {
        super("user_name_or_password_not_exists", HttpStatus.NOT_FOUND);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
