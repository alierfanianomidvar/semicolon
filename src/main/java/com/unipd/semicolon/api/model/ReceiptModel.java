package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;

import java.util.Date;
import java.util.List;

public class ReceiptModel {
    private Long id;

    private List<Long> list_drug_id;
    private Long drugs_id;

    private List<Long> list_material_id;
    private Long material_id;
    private byte[] image;
    private Date date;
    private PaymentMethod paymentMethod;

    public ReceiptModel() {
    }

    public ReceiptModel(
            Long drugs_id,
            Long material_id,
            byte[] image,
            Date date,
            PaymentMethod paymentMethod) {
        this.drugs_id = drugs_id;
        this.material_id = material_id;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public ReceiptModel(Long id,
                        List<Long> list_drug_id,
                        List<Long> list_material_id,
                        byte[] image,
                        Date date,
                        PaymentMethod paymentMethod)
    {
        this.id = id;
        this.list_drug_id = list_drug_id;
        this.list_material_id = list_material_id;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getList_drug_id() {
        return list_drug_id;
    }

    public void setList_drug_id(List<Long> list_drug_id) {
        this.list_drug_id = list_drug_id;
    }

    public Long getDrugs_id() {
        return drugs_id;
    }

    public void setDrugs_id(Long drugs_id) {
        this.drugs_id = drugs_id;
    }

    public List<Long> getList_material_id() {
        return list_material_id;
    }

    public void setList_material_id(List<Long> list_material_id) {
        this.list_material_id = list_material_id;
    }

    public Long getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Long material_id) {
        this.material_id = material_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
