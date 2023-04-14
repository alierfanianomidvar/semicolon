package com.unipd.semicolon.business.service;

public interface LoginService {

    void Login(
            String username,
            String password
    );

    void logOut(
            Long id
    );
}
