package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.ReceiptService;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import com.unipd.semicolon.core.repository.entity.ReceiptRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ReceiptServiceImp implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ValidationServiceImp validationServiceImp;

    @Override
    public Receipt save(List<Long> drugId,
                        List<Long> materialId,
                        byte[] image,
                        Date date,
                        PaymentMethod paymentMethod)
    {
        try {
            if (date == null || date.after(new Date())) {
                throw new IllegalArgumentException("Receipt date cannot be null or in the future.");
            }
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.setTime(date);

            if (!cal.getTime().equals(date)) {
                throw new IllegalArgumentException("Invalid date");
            }

            validationServiceImp.validatePaymentMethod(paymentMethod);


            for (Long id : drugId) {
                if (drugRepository.findById(id) == null) {
                    throw new EntityNotFoundException("Drug with ID " + id + " not found.");
                }
            }
            for (Long id : materialId) {
                if (materialRepository.findById(id) == null) {
                    throw new EntityNotFoundException("Material with ID " + id + " not found.");
                }
            }
            int maxSize = 10 * 1024 * 1024; // maximum size of 10 MB
            if (image.length > maxSize) {
                throw new IllegalArgumentException("Image size exceeds maximum size of 10 MB");
            }
            List<Drug> drugList = new ArrayList<>();
            List<Material> materialList = new ArrayList<>();
            for (Long id : drugId){
                drugList.add(drugRepository.findById(id));
            }
            for (Long id : materialId){
                materialList.add(materialRepository.findById(id));
            }

            if (drugList == null && materialList==null) {
                throw new EntityNotFoundException("Drug list and material list are empty");
            }

            Receipt receipt = new Receipt();
            receipt.setReceiptDrugs(drugList);
            receipt.setReceiptMaterials(null);
            receipt.setImage(image);
            receipt.setDate(date);
            receipt.setPaymentMethod(paymentMethod);

            return receiptRepository.save(receipt);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("An error occurred while saving the receipt: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("An error occurred while saving the receipt: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while saving the receipt.", e);
        }
    }


    @Override
    public Boolean edit(Long id,
                        List<Long> drug_id,
                        List<Long> material_id,
                        byte[] image,
                        Date date,
                        PaymentMethod paymentMethod)
    {
        try {
            Receipt receipt = receiptRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Receipt with ID " + id + " not found."));

            if (receipt != null) {
                //List<Drug> drugList = drugRepository.findById(drug_id).stream().toList();
                //List<Material> materialList=materialRepository.findById(material_id).stream().toList();
                List<Drug> drugList = new ArrayList<>();
                for (Long drugId : drug_id){
                    drugList.add(drugRepository.findById(drugId));
                }
                material_id=null;//after getting repository of material it will edit
    //          receipt.setReceiptDrugs(drugList);
                receipt.setReceiptDrugs(null);
                receipt.setReceiptMaterials(null);
                receipt.setImage(image);
                receipt.setDate(date);
                receipt.setPaymentMethod(paymentMethod);

                receiptRepository.save(receipt);
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("An error occurred while editing the receipt: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("An error occurred while editing the receipt: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while editing the receipt.", e);
        }
    }

    @Override
    public List<Receipt> getList() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id)
    {
        return receiptRepository.findById(id).get();
    }



}
