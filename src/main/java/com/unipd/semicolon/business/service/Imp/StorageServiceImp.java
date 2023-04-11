package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;

import java.util.List;

import com.unipd.semicolon.core.repository.entity.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImp implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage save(Pharmacy pharmacy,
                        Drug drug,
                        int amount,
                        int threshold) {
        if (pharmacy == null || drug == null || amount < 0 || threshold < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
        Storage storage = new Storage(pharmacy,
                drug,
                amount,
                threshold);

        storageRepository.save(storage);
        return null;}
    }

    @Override
    public boolean edit(Long id_storage,
                        Pharmacy pharmacy,
                        Drug drug,
                        int amount,
                        int threshold) {
        if (id_storage == null || id_storage < 0 || pharmacy == null || drug == null || amount < 0 || threshold < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            if (storageRepository.findStorageById(id_storage) != null) {
                Storage storage = storageRepository.findStorageById(id_storage);
                storage.setPharmacy(pharmacy);
                storage.setDrug(drug);
                storage.setAmount(amount);
                storage.setThreshold(threshold);
                storageRepository.save(storage);
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Storage storage) {
        if (storage == null) {
            throw new IllegalArgumentException("Cannot delete null storage!");
        } else {
            storageRepository.delete(storage);
        }
    }
}
