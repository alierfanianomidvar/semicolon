package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.UserModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST) //@PostMapping("/add") we can write like this too.
    // Here we get the data on the body and not on the url.
    public ResponseEntity save(@RequestBody UserModel model){ //always use model.
        return ResponseHelper
                .response(userService.save(
                        model.getUsername(),
                        model.getPassword(),
                        model.getName(),
                        model.getFamilyName(),
                        model.getBirthday(),
                        model.getRole()));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    //Here we want to update the data, so we use PUT and not POSt.
    public ResponseEntity edit(@RequestBody UserModel model) {
        return ResponseHelper
                .response(userService.edit(
                        model.getId(),
                        model.getName(),
                        model.getFamilyName(),
                        model.getBirthday(),
                        model.getRole()));
    }

    //findAllByFamilyName
    @RequestMapping(value = "/get-family-name/{familyName}", method = RequestMethod.GET)
    // Here we get the family name in the url.
    public ResponseEntity findAllByFamilyName(@PathVariable("familyName") String familyName) {
        return ResponseHelper
                .response(userService.findAllByFamilyName(familyName));
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(userService.getAll());
    }
}
