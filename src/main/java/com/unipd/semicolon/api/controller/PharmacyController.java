package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @PatchMapping(path = "{pharmacyId}")
    public void pharmacyActivation(@PathVariable("pharmacyId") Long pharmacyId,
                              @RequestParam(required = false) PharmacyStatus status){
        pharmacyService.activation(pharmacyId, status);
    }
}
