package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.LoginRequest;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @RequestMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequest model) throws Exception {
        return ResponseHelper.response(
                userService.login(
                        request.getUsername(),
                        request.getPassword()));
    }


}
