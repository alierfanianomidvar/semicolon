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
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DrugServiceImp implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ValidationServiceImp validationServiceImp;


    @Override
    public Drug save(
            String name,
            Long supplierId,
            Date expirationDate,
            byte[] image,
            String shape,
            Gender gender,
            AgeGroup ageGroup,
            boolean isSensitive,
            boolean needPrescription,
            String description,
            int limitation,
            float price,
            Country countryOFProduction) throws Exception {
            Objects.requireNonNull(name, "Name is null");
            Objects.requireNonNull(supplierId, "Supplier is null");
            if (image != null) {
                validationServiceImp.validateImage(image, 10 * 1024 * 1024);
            }
            validationServiceImp.validateDate(expirationDate, false);
            validationServiceImp.validatePrice(price);
            if (limitation <= 0) {
                throw new IllegalArgumentException("Limitation amount can not be negative");
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

            if (drugRepository.searchDrug(drug.getName(), drug.getSupplier(), drug.getExpirationDate(), drug.getShape(), drug.getAgeGroup(), drug.getCountryOFProduction())==null) {
                drugRepository.save(drug);
            }
            else
            {
                throw new Exception("item already exist in drug list");
            }
            return drug;


    }

    @Override
    public Drug edit(
            Long drugId,
            String name,
            Long supplierId,
            Date expirationDate,
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
        )
        {
            throw new IllegalArgumentException("Invalid input parameter");
        }
        else
        {
            if(image != null) {
                validationServiceImp.validateImage(image, 10 * 1024 * 1024);
            }
            if(expirationDate != null) {
                validationServiceImp.validateDate(expirationDate,false);
            }
            if(price != 0) {
                validationServiceImp.validatePrice(price);
            }
            if (limitation < 0 ) {
                throw new IllegalArgumentException("Limitation amount can not be negative");
            }


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
