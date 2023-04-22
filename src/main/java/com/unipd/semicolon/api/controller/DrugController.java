package com.unipd.semicolon.api.controller;


import com.unipd.semicolon.api.model.DrugModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.DrugService;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "/drug" ,method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody DrugModel model) {
        try {
            return ResponseHelper.response(drugService.save(
                    model.getName(),
                    model.getSupplier(),
                    model.getExpirationDate(),
                    model.getImage(),
                    model.getShape(),
                    model.getGender(),
                    model.getAgeGroup(),
                    model.getIsSensitive(),
                    model.getNeedPrescription(),
                    model.getDescription(),
                    model.getLimitation(),
                    model.getPrice(),
                    model.getCountryOFProduction()
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/drug/{id}" , method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id){
        return ResponseHelper.response(drugService.getById(id));
    }

    @RequestMapping(value = "/drug/{id}" , method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id , @RequestBody DrugModel model){
        try {
            return ResponseHelper.response(drugService.edit(
                    id,
                    model.getName(),
                    model.getSupplier(),
                    model.getExpirationDate(),
                    model.getImage(),
                    model.getShape(),
                    model.getGender(),
                    model.getAgeGroup(),
                    model.getIsSensitive(),
                    model.getNeedPrescription(),
                    model.getDescription(),
                    model.getLimitation(),
                    model.getPrice(),
                    model.getCountryOFProduction()
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/drug", method = RequestMethod.GET)
    public ResponseEntity getAll(
            @Nullable @RequestParam Country countryOFProduction,
            @Nullable @RequestParam Integer isSensitive,
            @Nullable @RequestParam Long supplierId,
            @Nullable @RequestParam String shape,
            @Nullable @RequestParam Gender gender) {
        try {
            return ResponseHelper.response(drugService.getAll(supplierId, isSensitive, countryOFProduction, shape, gender));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
