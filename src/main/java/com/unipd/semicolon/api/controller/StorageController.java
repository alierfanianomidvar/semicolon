package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.StorageModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.domain.StorageReportResponse;
import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    //new
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private MaterialRepository materialRepository;
    //

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody StorageModel model) {
        return ResponseHelper
                .response(storageService.save(
                        model.getPharmacy(),
                        model.getDrug(),
                        model.getMaterial(),
                        model.getAmount(),
                        model.getThreshold(),
                        model.getDiscount()
                ));
    }

    @RequestMapping(value = "/getByID/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(storageService.getById(id));
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody StorageModel model) {
        return ResponseHelper
                .response(storageService.edit(
                        id,
                        model.getPharmacy(),
                        model.getDrug(),
                        model.getMaterial(),
                        model.getAmount(),
                        model.getThreshold(),
                        model.getDiscount()
                ));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        storageService.delete(id);
        return ResponseHelper.response(true);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(storageService.getAll());
    }


    @RequestMapping(value = "/report/{pharmacyId}", method = RequestMethod.GET)
    public ResponseEntity<String> reportStorageByPharmacyId(@PathVariable("pharmacyId") Long pharmacyId) {

        pharmacyRepository.findById(pharmacyId).orElseThrow(
                () -> new IllegalStateException("Pharmacy is not found by id = " + pharmacyId
                ));

        StorageReportResponse response = storageService.getStorageReportResponse(pharmacyId);

        return ResponseHelper.okJson(response);
    }

    @RequestMapping(value = "/report/getAll", method = RequestMethod.GET)
    public ResponseEntity<String> reportStorageAllPharmacies() {

        List<StorageReportResponse> responseEntities = storageService.getAllStorageReports();

        return ResponseHelper.okJsonList(responseEntities);
    }

}
