package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface SupplierRepository {
    boolean editSupplier(Supplier s) throws SQLException;
    boolean addSupplier(Supplier s) throws SQLException;
    boolean removingSuppliers(Supplier s) throws SQLException;
    Supplier findBySupplierId(Long id);

}
