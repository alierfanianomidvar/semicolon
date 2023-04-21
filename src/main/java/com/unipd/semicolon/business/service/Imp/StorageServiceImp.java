package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.mapper.StorageMapper;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.domain.StorageResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.PharmacyRepository;
import com.unipd.semicolon.core.repository.entity.StorageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class StorageServiceImp implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

//    @Autowired
//    private MaterialRepository materialRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public Storage save(Pharmacy pharmacy,
                        Drug drug,
                        Material material,
                        int amount,
                        int threshold,
                        double discount) {
        Objects.requireNonNull(pharmacy, "Pharmacy is null"); // Check that pharmacy is not null
        if (amount <= 0 || threshold <= 0 || discount < 0.0 || discount > 100.0) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else if (drug == null && material == null) {
            throw new IllegalArgumentException("Either drug or material must be specified");
        } else if (drug != null && material != null) {
            throw new IllegalArgumentException("Both drug and material cannot be specified at the same time. Please specify only one.");
        } else
        {
            // Declare variables to store the found entities
            Pharmacy pharmacyRepositoryById = null;
            Material materialRepositoryById = null;
            Drug drugRepositoryById = null;
            try
            {
                if (pharmacyRepository.findById(pharmacy.getId()).isPresent()) {
                    pharmacyRepositoryById = pharmacyRepository.findById(pharmacy.getId()).get();
                }
//                if (materialRepository.findById(material.getId()).isPresent()) {
//                    materialRepositoryById = materialRepository.findById(material.getId()).get();
//                    Storage storage = new Storage(pharmacyRepositoryById,
//                            null,
//                            materialRepositoryById,
//                            amount,
//                            threshold,
//                            discount);
//
//                    Storage savedStorage = storageRepository.save(storage);
//                    if (savedStorage == null) {
//                        throw new RuntimeException("Failed to save Storage");
//                    }
//                    return savedStorage;

//                } else
                    if (drugRepository.findById(drug.getId()) != null) {
                    drugRepositoryById = drugRepository.findById(drug.getId());
                Storage storage = new Storage(pharmacyRepositoryById,
                        drugRepositoryById,
                        null,
                        amount,
                        threshold,
                        discount);
                Storage savedStorage = storageRepository.save(storage);
                    if (savedStorage == null) {
                        throw new RuntimeException("Failed to save Storage");
                    }
                return savedStorage;
                }
            }
            catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Invalid input parameter: " + e.getMessage());
            } catch (DataAccessException e) {
                throw new RuntimeException("Failed to access data: " + e.getMessage());
            }
        }
        return null;
    }


    @Override
    public boolean edit(Long id_storage,
                        Pharmacy pharmacy,
                        Drug drug,
                        Material material,
                        int amount,
                        int threshold,
                        double discount) {
        if (id_storage == null || id_storage < 0) {
            throw new IllegalArgumentException("id_storage is null");
        } else {
            Storage storage = storageRepository.findStorageById(id_storage);
            if (storage == null) {
                throw new NotFoundException();
            }
            if (pharmacy != null)
                storage.setPharmacy(pharmacy);
            if (drug != null)
                storage.setDrug(drug);
            if (material != null)
                storage.setMaterial(material);
            if (amount > 0)
                storage.setAmount(amount);
            if (threshold > 0)
                storage.setThreshold(threshold);
            if (discount >= 0 && discount <= 100)
                storage.setDiscount(discount);
            storageRepository.save(storage);
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot delete null storage!");
        } else {
            try {
                Storage storage = storageRepository.findStorageById(id);
                storageRepository.delete(storage);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public StorageResponse getById(Long id) {
        Storage storage = storageRepository.findStorageById(id);
        if (storage == null)
            throw new EntityNotFoundException("Storage not found with id: " + id);
        {
            return new StorageResponse(
                    storage.getId(),
                    storage.getPharmacy(),
                    storage.getDrug(),
                    storage.getMaterial(),
                    storage.getAmount(),
                    storage.getThreshold(),
                    storage.getDiscount()
            );
        }
    }

    @Override
    public List<StorageResponse> getAll() {
        List<StorageResponse> storageList = new ArrayList<>();
        for (Storage storage : storageRepository.getAll()) {
            storageList.add(StorageMapper.storageResponse(storage));
        }
        return storageList;
    }
}