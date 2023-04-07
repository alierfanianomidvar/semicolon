package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository
        extends JpaRepository<Pharmacy, Long> {

    // Find Pharmacy by id
//    Optional<Pharmacy> findById(Long id);
//
//    // Save an Pharmacy to the database
//    Pharmacy save(Pharmacy pharmacy);
//
//    // Delete an Pharmacy from the database
//    void delete(Pharmacy pharmacy);
//
//    // Get all Pharmacies
//    List<Pharmacy> findAll();
//
//    // Get Pharmacies by name
//    List<Pharmacy> findByName(String name);
//
//    // Get Pharmacies by owner
//    List<Pharmacy> findByOwner(String owner);           // Its entity should be changed to User
//
//    // Get Pharmacies by address
//    List<Pharmacy> findByAddress(String address);
//
//    // Get Pharmacies by tellNumber
//    List<Pharmacy> findByTellNumber(String tellNumber);
//
//    // Get Pharmacies by timeTable (hours)
//    List<Pharmacy> findByTimeTable(String from, String to);
//
//    // Get Pharmacies by timeTable (day)
//    List<Pharmacy> findByTimeTable(int day);
}
