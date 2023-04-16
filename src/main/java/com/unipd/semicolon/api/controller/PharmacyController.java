package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.PharmacyModel;
import com.unipd.semicolon.api.model.UserModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PharmacyModel model) {
        return ResponseHelper
                .response(pharmacyService.save(
                        model.getName(),
                        model.getAddress(),
                        model.getTellNumber(),
                        model.getTimeTable(),
                        model.getLogoPath(),
                        model.getStorage(),
                        model.getStaff()));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody PharmacyModel model) {
        return ResponseHelper
                .response(pharmacyService.edit(
                        id,
                        model.getName(),
                        model.getAddress(),
                        model.getTellNumber(),
                        model.getTimeTable(),
                        model.getLogoPath(),
                        model.getStorage(),
                        model.getStaff()));
    }

    @PutMapping("/add-staff/{id}")
    public ResponseEntity addStaff(
            @PathVariable("id") Long id,
            @RequestBody UserModel[] model) {
        List<User> users = new ArrayList<>();
        for (UserModel userModel : model) {
            User user = new User();
            if (userModel.getUserId() != null)
                user.setId(userModel.getUserId());
            user.setName(userModel.getName());
            user.setLastName(userModel.getLastName());
            user.setBirthDate(userModel.getBirthDate());
            user.setGender(userModel.getGender());
            user.setPhoneNumber(userModel.getPhoneNumber());
            user.setAddress(userModel.getAddress());
            user.setEmail(userModel.getEmail());
            user.setAccountStatus(userModel.getAccountStatus());
            user.setProfilePicture(userModel.getProfilePicture());
            user.setRole(userModel.getRole());

            // Add the user to the list of staff members
            users.add(user);
        }

        return ResponseHelper.response(pharmacyService.addStaff(users, id));
    }

    @DeleteMapping("/delete-staff")
    public ResponseEntity deleteStaff(@RequestBody List<User> staffList) {
        return ResponseHelper.response(pharmacyService.deleteStaff(staffList));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(pharmacyService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(pharmacyService.delete(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseHelper.response(pharmacyService.getAll());

    }

    @RequestMapping(value = {
            "{pharmacyId}" }, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pharmacyActivation(@PathVariable("pharmacyId") Long pharmacyId,
            @RequestBody PharmacyModel status) {
        return ResponseHelper.response(pharmacyService.activation(pharmacyId, status.getStatus()));
    }

}
