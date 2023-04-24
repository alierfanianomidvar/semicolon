package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface DrugRepository extends JpaRepository<Drug, Long>, JpaSpecificationExecutor<Drug> {

}
