package com.unipd.semicolon.business.service.Imp;


import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.domain.MaterialResponse;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.Imp.MaterialRepositoryImp;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Override
    public Material save(
            String name,
            Supplier supplier,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description,
            List<Order> orders,
            List<Receipt> receipts) {
        if (name == null || supplier == null ||
                gender == null || price < 0
                 || countryOfProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            Material material = new Material(
                    name,
                    supplier,
                    countryOfProduction,
                    expirationDate,
                    image,
                    gender,
                    price,
                    ageGroup,
                    lastModifiedDate,
                    description,
                    orders,
                    receipts
            );
            materialRepository.save(material);
            return material;
        }
    }

    @Override
    public boolean edit(
            Long id,
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup ageGroup,
            float price,
            LocalDate lastModifiedDate,
            String description,
            Country countryOfProduction,
            List<Order> orders,
            List<Receipt> receipts) {
                if (
                        id == null || id < 0 || name == null ||
                        supplier == null || gender == null || price < 0
                        || countryOfProduction == null
                ){
                throw new IllegalArgumentException("Invalid input parameter");
                } else {
                    Material material = materialRepository.findMaterialById(id);
                    if (material == null) {
                        throw new NotFoundException();
                    }
                    material.setName(name);
                    material.setSupplier(supplier);
                    material.setCountryOfProduction(countryOfProduction);
                    material.setExpirationDate(expirationDate);
                    material.setImage(image);
                    material.setGender(gender);
                    material.setPrice(price);
                    material.setAgeGroup(ageGroup);
                    material.setLastModifiedDate(lastModifiedDate);
                    material.setDescription(description);
                    return true;

                }
    }

        @Override
        public Material getById(Long id){
            Material material = materialRepository.findMaterialById(id);
            if (material != null) {
                return material;
            } else {
                throw new EntityNotFoundException("Material Not Found with id" + id);
            }
        }
    }
