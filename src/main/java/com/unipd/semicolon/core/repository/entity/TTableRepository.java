package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TTableRepository extends JpaRepository<TimeTable, Long> {

    TimeTable save(TimeTable timeTable);

    Boolean deleteByPharmacyId(Long id);

    Optional<TimeTable> findById(Long id);
}
