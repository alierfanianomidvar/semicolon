package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;

import java.util.List;

import com.unipd.semicolon.core.repository.entity.StorageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImp implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage save(Pharmacy pharmacy,
                        Drug drug,
                        Material material,
                        int amount,
                        int threshold) {
        if (pharmacy == null || (drug == null && material == null) || amount < 0 || threshold < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
        Storage storage = new Storage(pharmacy,
                drug,
                material,
                amount,
                threshold);

        storageRepository.save(storage);
        return storage;}
    }

    @Override
    public boolean edit(Long id_storage,
                        Pharmacy pharmacy,
                        Drug drug,
                        Material material,
                        int amount,
                        int threshold) {
        if (id_storage == null || id_storage < 0 || pharmacy == null || (drug == null && material == null) || amount < 0 || threshold < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            Storage storage = storageRepository.findStorageById(id_storage);
            if (storage == null) {
                throw new NotFoundException();
            }
                storage.setPharmacy(pharmacy);
                storage.setDrug(drug);
                storage.setMaterial(material);
                storage.setAmount(amount);
                storage.setThreshold(threshold);
                storageRepository.save(storage);
                return true;
            }
        }

    @Override
    public void delete(Storage storage) {
        if (storage == null) {
            throw new IllegalArgumentException("Cannot delete null storage!");
        } else {
            storageRepository.delete(storage);
        }
    }

    @Override
    public Storage getById(Long id) {
        Storage storage = storageRepository.findStorageById(id);
        if (storage != null) {
            return storage;
        }
            throw new EntityNotFoundException("Storage not found with id: " + id);
    }
}
