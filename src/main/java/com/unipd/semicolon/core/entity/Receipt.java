package com.unipd.semicolon.core.entity;

import java.util.Date;
import java.util.List;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import jakarta.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Drug> receiptDrugs;

    @ManyToMany
    private List<Material> receiptMaterials;

    @Column(name = "string_image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    public Receipt() {
    }

    public Receipt(
            List<Drug> receiptDrugs,
            List<Material> receiptMaterials,
            String image,
            Date date,
            PaymentMethod paymentMethod) {
        this.receiptDrugs = receiptDrugs;
        this.receiptMaterials = receiptMaterials;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    // getters and setters for all attributes


    public Long getId() {
        return id;
    }

    public List<Drug> getReceiptDrugs() {
        return receiptDrugs;
    }

    public void setReceiptDrugs(List<Drug> receiptDrugs) {
        this.receiptDrugs = receiptDrugs;
    }

    public List<Material> getReceiptMaterials() {
        return receiptMaterials;
    }

    public void setReceiptMaterials(List<Material> receiptMaterials) {
        this.receiptMaterials = receiptMaterials;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
