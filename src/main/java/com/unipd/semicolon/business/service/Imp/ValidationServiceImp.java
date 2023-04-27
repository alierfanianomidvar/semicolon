package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ValidationServiceImp implements ValidationService {

    // validate payment method
    @Override
    public Boolean validatePaymentMethod(PaymentMethod paymentMethod) {
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
        return true;
    }

    // validate size of an input image
    @Override
    public Boolean validateImage(byte[] image, int maxSize) {
        if (image != null) {
            if (image.length > maxSize) {
                throw new IllegalArgumentException(
                        "Image size exceeds maximum size of " + (float) maxSize / (1024 * 1024) + " MB");
            }
            return true;
        }
        return true;
    }

    @Override
    public Boolean validateDate(Date date, Boolean futureCheck) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (futureCheck) {
            if (date.after(new Date())) {
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.setTime(date);

        if (!cal.getTime().equals(date)) {
            throw new IllegalArgumentException("Invalid date");
        }
        return true;
    }

    @Override
    public Boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return true;
    }

    @Override
    public Boolean validateTelephoneNumber(String telephoneNumber) {
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
        return true;
    }

    @Override
    public Boolean validatePrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price should be greater than zero");
        }
        return true;

    }

    @Override
    public Boolean validateBirthDate(LocalDateTime birthDate) {
        if (birthDate == null) {
            // Birth date is null
            throw new IllegalArgumentException("Date cannot be null.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (birthDate.isAfter(now)) {
            // Birth date is in the future
            throw new IllegalArgumentException("Date cannot be in the future.");
        }

        // Birth date is valid
        return true;
    }

    @Override
    public Boolean validateGender(Gender gender) {
        List<Gender> allowedGenders = Arrays.asList(
                Gender.FEMALE,
                Gender.MALE,
                Gender.NON_BINARY
        );

        if (gender == null || !allowedGenders.contains(gender)) {
            throw new CustomException("Invalid gender");
        }
        return true;
    }

}
