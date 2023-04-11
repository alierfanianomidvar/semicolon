package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository {
    Storage save(
            Storage storage
    );
    void delete(
            Storage storage
    );

    Storage findStorageById(Long id);

    List<Storage> getAll();
}