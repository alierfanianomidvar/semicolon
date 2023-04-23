package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierRepositoryImp extends CustomRepository implements SupplierRepository {
    public boolean saveSupplier(Supplier s) {
        save(Supplier.class, s);
        return true;
    }

    public boolean deleteSupplier(Supplier s) {
        delete(Supplier.class, s);
        return true;
    }

    @Override
    public Supplier findBySupplierId(Long id) {
        return findById(Supplier.class, id);
    }
}
