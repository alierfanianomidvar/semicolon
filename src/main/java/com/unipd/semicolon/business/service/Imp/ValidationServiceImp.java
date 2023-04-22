package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.stereotype.Service;

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
}
