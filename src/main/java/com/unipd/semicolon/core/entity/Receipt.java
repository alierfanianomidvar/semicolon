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
    private List<Material> receiptMaterials; //temp code just to check if it runs or not
    //upcoming code: private List<Drug> drug_list;
    //Drug to be connected later
    //

    @Column(name = "image")
    private byte[] image;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    public Receipt() {}

    public Receipt(List<Drug> drug_list, // temp code. upcoming code:List<> drug_list
                   byte[] image,
                   Date date,
                   PaymentMethod paymentMethod) {
        this.receiptDrugs = drug_list;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    // getters and setters for all attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Drug> getDrug_list() { //temp code. upcoming code: List<Drug> getDrug_list()
        return receiptDrugs;
    }

    public void setDrug_list(List<Drug> drug_list) {//temp code. upcoming code: setDrug_list(List<Drug> drug_list)
        this.receiptDrugs = drug_list;
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
