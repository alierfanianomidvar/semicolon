package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long>, JpaSpecificationExecutor<Drug> {

    Drug save(
            Drug drug
    );

    List<Drug> getAll();

    Drug findDrugById(Long id);
}
