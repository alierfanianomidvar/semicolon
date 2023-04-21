package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Supplier;

public interface SupplierRepository {
    boolean saveSupplier(Supplier s);
    boolean deleteSupplier(Supplier s);
    Supplier findBySupplierId(Long id);

}
