package com.unipd.semicolon.business.service;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;

import java.util.Date;
import java.util.List;

public interface ReceiptService {

    Receipt save(List<Long> drugId,
                 List<Long> materialId,
                 byte[] image,
                 Date date,
                 PaymentMethod paymentMethod);

    Boolean edit(Long id,
                 List<Long> drugId,
                 List<Long> materialId,
                 byte[] image,
                 Date date,
                 PaymentMethod paymentMethod);

    List<Receipt> getList();
    Receipt getById(Long id);




}
