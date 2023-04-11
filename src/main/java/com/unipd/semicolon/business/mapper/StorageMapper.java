package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.Storage;

public class StorageMapper {
    public static StorageResponse storageResponse(Storage storage) {
        return new StorageResponse(
                storage.getId(),
                storage.getPharmacy(),
                storage.getDrug(),
                storage.getAmount(),
                storage.getThreshold()
        ) {
        };
    }
}
