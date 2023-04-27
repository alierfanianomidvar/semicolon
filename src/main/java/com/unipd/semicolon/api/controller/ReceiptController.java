package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.ReceiptModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.ReceiptService;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ReceiptModel receipt) {
        return ResponseHelper
                .response(receiptService.save(
                        receipt.getList_drug_id(),
                        receipt.getList_material_id(),
                        receipt.getImage(),
                        receipt.getDate(),
                        receipt.getPaymentMethod()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable("id") Long id) {
        return ResponseHelper.response(receiptService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(
            @PathVariable("id") Long id,
            @RequestBody ReceiptModel receiptModel) {
        return ResponseHelper
                .response(receiptService.edit(
                        id,
                        receiptModel.getList_drug_id(),
                        receiptModel.getList_material_id(),
                        receiptModel.getImage(),
                        receiptModel.getDate(),
                        receiptModel.getPaymentMethod()));

    }
//
//    @GetMapping("/getRecieptList/{id}")
//    public ResponseEntity<List<Receipt>> getAll(@PathVariable("id") Long id) {
//        List<Receipt> receipts = receiptService.get;
//        return ResponseHelper.response(receipts);
//    }
}
