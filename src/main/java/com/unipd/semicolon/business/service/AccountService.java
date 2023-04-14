package com.unipd.semicolon.business.service;

public interface AccountService {

    String Login(
            String username,
            String password
    );

    void logOut(
            Long id
    );
}
