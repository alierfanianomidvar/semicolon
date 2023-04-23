package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;

import java.util.Date;
import java.util.List;

public interface DrugRepository {

    Drug save(
            Drug drug
    );

    List<Drug> getAll();

    Drug findById(Long id);

    List<Drug> searchDrug(String name, Supplier supplier, Date expirationDate, String shape, AgeGroup ageGroup, Country countryOFProduction);
}
