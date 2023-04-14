package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;

import java.util.List;

public interface StorageService {
    Storage save(
            Pharmacy pharmacy,
            Drug drug,
            Material material,
            int amount,
            int threshold
    );
    boolean edit(
            Long id_storage,
            Pharmacy pharmacy,
            Drug drug,
            Material material,
            int amount,
            int threshold
    );
    void delete(
            Storage storage
    );
    Storage getById(
            Long id
    );
    List<StorageResponse> getAll();
}
