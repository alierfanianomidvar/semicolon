package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    static Drug save(
            Drug drug
    );

    List<Drug> getAll();

    Drug findDrugById(Long id);
}
