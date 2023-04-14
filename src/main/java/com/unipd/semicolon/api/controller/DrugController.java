package com.unipd.semicolon.api.controller;


import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.core.entity.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/Drug")
public class DrugController {

    @Autowired
    private DrugService DrugService;

    @RequestMapping(value = "/" , method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody DrugModel model) {
        return ResponseHelper.response(DrugService.save(
                model.getName(),
                model.getSupplier(),
                model.getExpirationDate(),
                model.getImage(),
                model.getShape(),
                model.getGender(),
                model.getAgeGroup(),
                model.getIsSensitive();
                model.getNeedPrescription(),
                model.getDescription(),
                model.getLimitation(),
                model.getPrice(),
                model.getCountryOFProduction(),
                model.getLastModifiedDate()
        ));
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity getById(@pathVariable("id") Long id){
        return ResponseHelper.response(DrugService.getById(id));
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity edit(@pathVariable("id") Long id , @RequestBody DrugModel model){
        return ResponseHelper.response(DrugService.edit(
                id,
                model.getName(),
                model.getSupplier(),
                model.getExpirationDate(),
                model.getImage(),
                model.getShape(),
                model.getGender(),
                model.getAgeGroup(),
                model.getIsSensitive();
                model.getNeedPrescription(),
                model.getDescription(),
                model.getLimitation(),
                model.getPrice(),
                model.getCountryOFProduction(),
                model.getLastModifiedDate()
        ));
    }

    @RequestMapping(value = "/{id}/isActive" , method = RequestMethod.GET)
    public ResponseEntity isActive(@pathVariable("id") Long id){
        Drug drug = (Drug) drugService.getById(id);
        return ResponseHelper.response(DrugService.isActive(drug));
    }

}
