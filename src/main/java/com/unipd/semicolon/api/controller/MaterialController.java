package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.MaterialModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MaterialModel model) {
        return ResponseHelper
                .response(materialService.save(
                        model.getName(),
                        model.getSupplier(),
                        model.getExpirationDate(),
                        model.getImage(),
                        model.getGender(),
                        model.getAgeGroup(),
                        model.getSellPrice(),
                        model.getBuyPrice(),
                        model.getAmount(),
                        model.getIsActive(),
                        model.getDescription(),
                        model.getCountryOfProduction()
                ));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(materialService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody MaterialModel model) {
        return ResponseHelper
                .response(materialService.edit(
                        id,
                        model.getName(),
                        model.getSupplier(),
                        model.getExpirationDate(),
                        model.getImage(),
                        model.getGender(),
                        model.getAgeGroup(),
                        model.getSellPrice(),
                        model.getBuyPrice(),
                        model.getAmount(),
                        model.getIsActive(),
                        model.getDescription(),
                        model.getCountryOfProduction()
                ));
    }

    @RequestMapping(value = "/{id}/isActive", method = RequestMethod.GET)
    public ResponseEntity isActive(@PathVariable("id") Long id) {
        Material material = (Material) materialService.getById(id);
        return ResponseHelper.response(materialService.isActive(material));
    }
}