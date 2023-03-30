package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrugRepository
        extends JpaRepository<Drug, Long> {

    //SELECT * FROM student WHERE email = ?
    @Query("SELECT d FROM Drug d where 1=?1")
    Optional<Drug> findDrugByEmail(String email);
}
