package com.unipd.semicolon.core.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "drug_list")
    private String drug_list; //temp code just to check if it runs or not
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

    public Receipt(String drug_list, // temp code. upcoming code:List<> drug_list
                   byte[] image,
                   Date date,
                   PaymentMethod paymentMethod) {
        this.drug_list = drug_list;
        this.image = image;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    // getters and setters for all attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrug_list() { //temp code. upcoming code: List<Drug> getDrug_list()
        return drug_list;
    }

    public void setDrug_list(String drug_list) {//temp code. upcoming code: setDrug_list(List<Drug> drug_list)
        this.drug_list = drug_list;
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

    // enum to represent the payment methods available
    public enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL
    }
}
