package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.LoginRequest;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequest model) throws Exception {
        return ResponseHelper.response(accountService.Login(
                        model.getUsername(),
                        model.getPassword()));
    }


}
