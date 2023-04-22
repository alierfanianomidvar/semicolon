package com.unipd.semicolon.business.service.Imp;


import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.mapper.MaterialMapper;
import com.unipd.semicolon.business.service.MaterialService;
import com.unipd.semicolon.core.domain.MaterialResponse;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    private Material material;

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
            String description) {
        if (name == null ||
                gender == null || price < 0
                 || countryOfProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            if (supplier != null) {
                material.setSupplier(supplier);
            } else {
                throw new IllegalArgumentException("Invalid input parameter, Supplier has not been set");
            }
            // Supplier supplierNew == {id}
            Material material = new Material(
                    name,
                    supplier, // supplierNew
                    countryOfProduction,
                    expirationDate,
                    image,
                    gender,
                    price,
                    ageGroup,
                    lastModifiedDate,
                    description
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
            Country countryOfProduction) {
                if (
                        id == null || id < 0 || name == null ||
                        supplier == null || gender == null || price < 0
                        || countryOfProduction == null
                ){
                throw new IllegalArgumentException("Invalid input parameter");
                } else {
                    Material material = materialRepository.findById(id);
                    if (material == null) {
                        throw new NotFoundException();
                    }
                    if(name != null) {
                        material.setName(name);
                    }
                    if(supplier != null) {
                        material.setSupplier(supplier);
                    }
                    if(countryOfProduction != null) {
                        material.setCountryOfProduction(countryOfProduction);
                    }
                    if (expirationDate != null) {
                        material.setExpirationDate(expirationDate);
                    }
                    if (image != null) {
                        material.setImage(image);
                    }
                    if (gender != null) {
                        material.setGender(gender);
                    }
                    if (price < 0) {
                        material.setPrice(price);
                    }
                    if (ageGroup != null) {
                        material.setAgeGroup(ageGroup);
                    }
                    if (lastModifiedDate != null) {
                        material.setLastModifiedDate(lastModifiedDate);
                    }
                    if (description != null) {
                        material.setDescription(description);
                    }
                    return true;
                }
    }

        @Override
        public Material getById(Long id){
            Material material = materialRepository.findById(id);
            if (material != null) {
                return material;
            } else {
                throw new EntityNotFoundException("Material Not Found with id" + id);
            }
        }

        @Override
        public List<MaterialResponse> getAll(){
            List<MaterialResponse> materialList = new ArrayList<>();
            for (Material material : materialRepository.getAll()) {
                materialList.add(MaterialMapper.materialResponse(material));
            }
            return materialList;
        }
    }
