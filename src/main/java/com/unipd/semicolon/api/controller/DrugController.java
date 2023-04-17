package com.unipd.semicolon.api.controller;


import com.unipd.semicolon.api.model.DrugModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.business.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/Drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody DrugModel model) {
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
    }

    @RequestMapping(value = "/getById/{id}" , method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id){
        return ResponseHelper.response(drugService.getById(id));
    }

    @RequestMapping(value = "/edit/{id}" , method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id , @RequestBody DrugModel model){
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
    }

    @RequestMapping(value = "/isActive/{id}" , method = RequestMethod.GET)
    public ResponseEntity isActive(@PathVariable("id") Long id){
        Drug drug = (Drug) drugService.getById(id);
        return ResponseHelper.response(drugService.isActive(drug));
    }

}
