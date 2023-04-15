package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.PharmacyModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PharmacyModel model){
        return ResponseHelper
                .response(pharmacyService.save(
                        model.getName(),
                        model.getAddress(),
                        model.getTellNumber(),
                        model.getTimeTable(),
                        model.getLogoPath(),
                        model.getStorage(),
                        model.getStaff()
                        ));
    }

//    @PutMapping("/edit/{id}")
//    public ResponseEntity edit(
//            @PathVariable("id") Long id,
//            @RequestBody PharmacyModel model) {
//        return ResponseHelper
//                .response(pharmacyService.edit(
//                        id,
//                        model.getName(),
//                        model.getAddress(),
//                        model.getTellNumber(),
//                        model.getTimeTable(),
//                        model.getLogoPath(),
//                        model.getStorage(),
//                        model.getStaff()
//                        ));
//    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity get(@PathVariable("id") Long id) {
//        return ResponseHelper
//                .response(pharmacyService.get(id));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable("id") Long id) {
//        return ResponseHelper
//                .response(pharmacyService.delete(id));
//    }
//
//    @GetMapping("/get-all")
//    public ResponseEntity getAll() {
//        return ResponseHelper.response(pharmacyService.getAll());
//    }

}