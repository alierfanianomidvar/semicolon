package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.StorageModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.exception.EntityNotFoundException;
import com.unipd.semicolon.business.exception.InvalidParameterException;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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


    //new
    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity reportStorage(@PathVariable("id") Long id, @RequestBody StorageModel model) {

        Pharmacy pharmacy = model.getPharmacy();
        Drug drug = model.getDrug();
        Material material = model.getMaterial();

        Pharmacy pharmacyRepositoryById = null;
        Material materialRepositoryById = null;
        Drug drugRepositoryById = null;

        float drugPrice = 0f;
        float materialPrice = 0f;


        if (storageService.getById(id) != null && pharmacyRepository.findById(pharmacy.getId()).isPresent()) {
            //pharmacyRepositoryById = pharmacyRepository.findById(pharmacy.getId()).get();
            try {
                if (drug.getId() != null) {
                    drugRepositoryById = drugRepository.findById(drug.getId())
                            .orElseThrow(() -> new IllegalStateException("Drug not found - " + drug.getId()));

                    // Calculating the price of the drug
                    drugPrice = model.getAmount() * drugRepositoryById.getPrice();
                } else if (material.getId() != null) {
                    materialRepositoryById = materialRepository.findById(material.getId())
                            .orElseThrow(() -> new IllegalStateException("Material not found - " + material.getId()));

                    // Calculating the price of the material
                    materialPrice = model.getAmount() * materialRepositoryById.getPrice();
                } else {
                    throw new IllegalArgumentException("Neither drug nor material was provided.");
                }
            } catch (EntityNotFoundException e) {
                return ResponseHelper.response(
                        "DRUG ID :" + model.getDrug().getId(),
                        e.getMsg(),
                        e.getStatus()
                //      ", MATERIAL ID : " +model.getMaterial().getId(),
                //        e.getMsg(),
                //        e.getStatus()
                );
            } catch (DataAccessException e) {
                throw new RuntimeException("Failed to access data: " + e.getMessage());
            }
        }
        return ResponseEntity.ok("Drug price: " + drugPrice + ", Material price: " + materialPrice);
    }
    //
}
