package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;

import java.util.Date;

public interface ValidationService {
    void validatePaymentMethod(PaymentMethod paymentMethod);
    void validateImage(byte[] image);

    void validateDate(Date date);

    void validateEmail(String email);

    void validateTelephoneNumber(String telephoneNumber);

    void validatePrice(float price);
}
