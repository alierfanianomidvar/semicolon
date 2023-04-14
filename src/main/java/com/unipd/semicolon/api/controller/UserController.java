package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.UserModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.UserService;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/save", method = RequestMethod.POST) //@PostMapping("/add") we can write like this too.
//    // Here we get the data on the body and not on the url.
//    public ResponseEntity save(@RequestBody UserModel model){ //always use model.
//        return ResponseHelper
//                .response(userService.save(
//                        model.getUsername(),
//                        model.getPassword(),
//                        model.getName(),
//                        model.getFamilyName(),
//                        model.getBirthday(),
//                        model.getRole()));
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    //Here we want to update the data, so we use PUT and not POSt.
    public ResponseEntity edit(@RequestBody UserModel model) {
        return ResponseHelper
                .response(userService.edit(
                        model.getUserId(),
                        model.getName(),
                        model.getLastName(),
                        model.getGender(),
                        model.getBirthDate(),
                        model.getPhoneNumber(),
                        model.getAddress(),
                        model.getRole(),
                        model.getEmail(),
                        model.getAccountStatus(),
                        model.getProfilePicture()
                        )
                );
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(userService.getAll());
    }

    @RequestMapping(value="/getById",method=RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id")Long id){
        return ResponseHelper
                .response(userService.getById(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return ResponseHelper.response(true);
    }
}
