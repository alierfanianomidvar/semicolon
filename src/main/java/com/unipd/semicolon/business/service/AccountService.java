package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.exception.UserNameOrPasswordNotExitsException;
import com.unipd.semicolon.core.domain.AccountResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.User;

public interface AccountService {

    AccountResponse Login (
            String username,
            String password
    );

    Boolean LogOut(
            String token
    );


    Login save(
            String username,
            String password,
            User userId
    );


}
