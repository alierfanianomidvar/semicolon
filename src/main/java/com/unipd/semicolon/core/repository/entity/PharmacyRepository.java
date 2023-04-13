package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository
        extends JpaRepository<Pharmacy, Long> {

}
