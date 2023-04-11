package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;

import java.util.List;

public interface StorageService {
    Storage save(
            Pharmacy pharmacy,
            Drug drug,
            int amount,
            int threshold
    );
    boolean edit(
            Long id_storage,
            Pharmacy pharmacy,
            Drug drug,
            int amount,
            int threshold
    );
    void delete(
            Storage storage
    );
    List<Storage> getAll();
}
