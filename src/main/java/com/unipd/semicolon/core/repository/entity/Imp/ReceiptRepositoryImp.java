package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.exception.RepositoryException;
import com.unipd.semicolon.core.repository.entity.ReceiptRepository;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ReceiptRepositoryImp extends CustomRepository implements ReceiptRepository {

    @Override
    public Receipt save(Receipt receipt) { // saving new receipt.
        return save(Receipt.class, receipt);
    }
}
