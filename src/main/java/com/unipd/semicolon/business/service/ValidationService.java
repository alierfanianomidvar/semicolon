package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.enums.PaymentMethod;

public interface ValidationService {
    void validatePaymentMethod(PaymentMethod paymentMethod);
}
