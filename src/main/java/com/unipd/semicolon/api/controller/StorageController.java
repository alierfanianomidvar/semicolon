package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.StorageModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/storage", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody StorageModel model) {
        return ResponseHelper
                .response(storageService.save(
                        model.getPharmacy(),
                        model.getDrug(),
                        model.getAmount(),
                        model.getThreshold()
                ));

    }
}
