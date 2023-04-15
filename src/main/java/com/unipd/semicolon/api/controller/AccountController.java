package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.LoginRequest;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.exception.UserNameOrPasswordNotExitsException;
import com.unipd.semicolon.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest model) {
        return ResponseHelper.response(accountService.Login(
                    model.getUsername(),
                    model.getPassword()));

    }

    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST)
    public ResponseEntity login(@PathVariable String token) {
        return ResponseHelper.response(accountService.LogOut(token));

    }


}
