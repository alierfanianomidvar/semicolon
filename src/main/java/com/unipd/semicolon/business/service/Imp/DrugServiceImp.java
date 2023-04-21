package com.unipd.semicolon.business.service.Imp;


import com.unipd.semicolon.business.exception.NotFoundException;
import com.unipd.semicolon.business.mapper.DrugMapper;
import com.unipd.semicolon.business.service.DrugService;
import com.unipd.semicolon.core.domain.DrugResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DrugServiceImp implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public Drug save(
            String name,
            Long supplierId,
            LocalDate expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOFProduction) throws SQLException {
        Objects.requireNonNull(name, "Name is null");
        Objects.requireNonNull(supplierId, "Supplier is null");
        Objects.requireNonNull(expirationDate, "Expiration date is null");
        Objects.requireNonNull(shape, "Shape is null");
        Objects.requireNonNull(gender, "Gender is null");
        Objects.requireNonNull(ageGroup, "Age group is null");
        Objects.requireNonNull(countryOFProduction, "Country of production is null");
        if (limitation <= 0 || price < 0.0 || price > Float.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        Supplier supplier = supplierRepository.findBySupplierId(supplierId);
        Drug drug = new Drug(
                name,
                supplier,
                expirationDate,
                image,
                shape,
                gender,
                ageGroup,
                isSensitive,
                needPrescription,
                description,
                limitation,
                price,
                countryOFProduction);
        drugRepository.save(drug);
        return drug;
    }

    @Override
    public Drug edit(
            Long drugId,
            String name,
            Long supplierId,
            LocalDate expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOFProduction) throws SQLException {
        if (
                drugId == null || drugId < 0
        ) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {

            Drug drug = drugRepository.findById(drugId);
            if (drug == null) {
                throw new NotFoundException();
            }
            if (name != null) {
                drug.setName(name);
            }
            if (supplierId != null && supplierId > 0 ) {
                Supplier supplier = supplierRepository.findBySupplierId(supplierId);
                if (supplier != null)
                    drug.setSupplier(supplier);
            }
            if (countryOFProduction != null) {
                drug.setCountryOFProduction(countryOFProduction);
            }
            if (expirationDate != null) {
                drug.setExpirationDate(expirationDate);
            }
            if (image != null) {
                drug.setImage(image);
            }
            if (gender != null) {
                drug.setGender(gender);
            }
            if (price > 0.0) {
                drug.setPrice(price);
            }
            if (ageGroup != null) {
                drug.setAgeGroup(ageGroup);
            }
            if (limitation != 0) {
                drug.setLimitation(limitation);
            }
            if (description != null) {
                drug.setDescription(description);
            }
            drugRepository.save(drug);
            return drug;

        }
    }

    @Override
    public DrugResponse getById(Long id) {
        Drug drug = drugRepository.findById(id);

        if (drug == null)
            throw new EntityNotFoundException("Drug Not Found with id" + id);
        {
            return new DrugResponse(
                    drug.getName(),
                    drug.getSupplier(),
                    drug.getExpirationDate(),
                    drug.getImage(),
                    drug.getShape(),
                    drug.getGender(),
                    drug.getAgeGroup(),
                    drug.isSensitive(),
                    drug.isNeedPrescription(),
                    drug.getDescription(),
                    drug.getLimitation(),
                    drug.getPrice(),
                    drug.getCountryOFProduction()
            );
        }
    }


    @Override
    public List<DrugResponse> getAll() {
        List<DrugResponse> drugList = new ArrayList<>();
        try {
            List<Drug> drugs = drugRepository.getAll();
            if (drugs == null || drugs.isEmpty()) {
                throw new NotFoundException();
            }
            for (Drug drug : drugs) {
                drugList.add(DrugMapper.drugResponse(drug));
            }
        } catch (DataAccessException ex) {
            throw new ServiceException("Failed to retrieve drugs.", ex);
        }
        return drugList;
    }
}
