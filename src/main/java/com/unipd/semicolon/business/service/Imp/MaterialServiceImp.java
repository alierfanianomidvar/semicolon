package com.unipd.semicolon.business.service.Imp;


import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Material save(
            String materialName,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup age,
            float sellPrice,
            float buyPrice,
            int amount,
            boolean isActive,
            String description,
            Country countryOfProduction) {
        if (materialName == null || supplier == null || expirationDate == null ||
                image == null || gender == null || age == null || sellPrice < 0 ||
                buyPrice < 0 || amount < 0 || description == null || countryOfProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            Material material = new Material(
                    materialName,
                    supplier,
                    expirationDate,
                    image,
                    gender,
                    age,
                    sellPrice,
                    buyPrice,
                    amount,
                    isActive,
                    description,
                    countryOfProduction);

            materialRepository.save(material);
            return material;
        }

    }

    @Override
    public boolean edit(Long id, String materialName, Supplier supplier, LocalDate expirationDate, byte[] image, Gender gender, AgeGroup age, float sellPrice, float buyPrice, int amount, boolean isActive, String description, Country countryOfProduction) {
        return false;
    }

    @Override
    public boolean isActive(Material material) {
        return false;
    }

    @Override
    public Material getById(Long id) {
        Material material = materialRepository.findMaterialById(id);
        if (material != null) {
            return material;
        } else {
            throw new EntityNotFoundException("Material Not Found with id" + id);
        }
    }
}
