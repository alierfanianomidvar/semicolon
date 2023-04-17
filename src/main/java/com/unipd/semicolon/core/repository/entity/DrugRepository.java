package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;

import java.util.List;

public interface DrugRepository {

    Drug save(
            Drug drug
    );

    List<Drug> getAll();

    Drug findDrugById(Long id);
}
