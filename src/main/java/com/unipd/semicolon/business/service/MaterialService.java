package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;

public interface MaterialService {
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
            Country countryOfProduction
    );

    boolean edit(
            Long id,
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
            Country countryOfProduction
    );

    boolean isActive(
            Material material
    );

    Object getById(Long id);
}
