package com.unipd.semicolon.business.service.Imp;


import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DrugServiceImp implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public Drug save(
            String name,
            Supplier supplier,
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
            Country countryOFProduction) {
        if (name == null || supplier == null || expirationDate == null ||
                image == null || shape == null || gender == null || ageGroup == null ||
                isSensitive == null || needPrescription == null || description == null ||
                limitation == null || price < 0 || countryOFProduction == null) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
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
            DrugRepository.save(drug);
            return drug;
        }
    }

    @Override
    public boolean edit(Long id , String name , Supplier supplier , LocalDate expirationDate ,
                        byte[] image , String shape, Gender gender,
                        AgeGroup ageGroup, boolean isSensitive,
                        boolean needPrescription, String description,
                        int limitation, float price,
                        Country countryOFProduction ){
        return false;
    }

    @Override
    public boolean isActive(Drug drug){
        return false;
    }

    @Override
    public Drug grtById(Long id){
        Drug drug = drugRepository.findDrugById(id);
        if (drug != null){
            return drug;
        } else {
            throw new EntityNotFoundException("Drug Not Found with id" + id);
        }
    }


}
