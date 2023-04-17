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


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ReceiptModel receipt){
        return ResponseHelper
                .response(receiptService.save(
                        receipt.getList_drug_id(),
                        receipt.getList_material_id(),
                        receipt.getImage(),
                        receipt.getDate(),
                        receipt.getPaymentMethod()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable Long id) {
        Receipt receipt = receiptService.getReceiptById(id);
        return ResponseHelper.response(receipt);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity edit(@RequestBody ReceiptModel receiptModel)
    {
        Long id=receiptModel.getId();
        List<Long> drugIds = receiptModel.getList_drug_id();
        List<Long> materialIds = receiptModel.getList_material_id();
        byte[] image = receiptModel.getImage();
        Date date = receiptModel.getDate();
        PaymentMethod paymentMethod = receiptModel.getPaymentMethod();
        return ResponseHelper
                .response(receiptService.edit(id,
                        drugIds,
                        materialIds,
                        image,
                        date,
                        paymentMethod));

    }

    @GetMapping("")
    public ResponseEntity<List<Receipt>> getAll() {
        List<Receipt> receipts = receiptService.getList();
        return ResponseHelper.response(receipts);
    }
}
