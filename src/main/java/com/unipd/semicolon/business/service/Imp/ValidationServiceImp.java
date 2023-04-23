package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ValidationServiceImp {

    //validate payment method
    public void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        PaymentMethod[] allowedMethods = {PaymentMethod.CASH, PaymentMethod.CREDIT_CARD,
                PaymentMethod.DEBIT_CARD, PaymentMethod.PAYPAL};
        boolean isValid = false;

        for (PaymentMethod allowedMethod : allowedMethods) {
            if (allowedMethod == paymentMethod) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }

    //validate size of an input image
    public void validateImage(byte[] image, int maxSize)
    {
        if (image.length > maxSize) {
            throw new IllegalArgumentException("Image size exceeds maximum size of "+ (float) maxSize/(1024*1024)+" MB");
        }
    }

    public void validateDate(Date date, boolean futureCheck) {
        if (date == null ) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if(futureCheck)
        {
            if(date.after(new Date()))
            {
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.setTime(date);

        if (!cal.getTime().equals(date)) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }


    }

    public void validateTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber == null || telephoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Telephone number cannot be null or empty");
        }

        String digitsRegex = "\\d+";
        if (!telephoneNumber.matches(digitsRegex)) {
            throw new IllegalArgumentException("Telephone number should contain only digits");
        }

        int minLength = 7; // minimum length of a telephone number
        int maxLength = 20; // maximum length of a telephone number
        if (telephoneNumber.length() < minLength || telephoneNumber.length() > maxLength) {
            throw new IllegalArgumentException("Telephone number should be between 7 and 20 digits long");
        }
    }

    public void validatePrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price should be greater than zero");
        }

    }

}
