package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.PharmacyModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.PharmacyService;
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
                              @RequestParam PharmacyModel pharmacyModel){
        ResponseHelper.response(pharmacyService.activation(pharmacyId, pharmacyModel.getStatus()));
    }
}
