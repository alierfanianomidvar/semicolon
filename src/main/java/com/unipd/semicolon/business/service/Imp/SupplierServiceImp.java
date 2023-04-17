package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CreatePharmacyDataNotFound;
import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.dao.SupplierDao;
import com.unipd.semicolon.core.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImp implements SupplierService {

    private SupplierDao supplierDao;

    public SupplierServiceImp() {
        this.supplierDao = new SupplierDao();
    }

    @Override
    public List<Supplier> getSupplierList() throws SQLException {
        return supplierDao.findAll();
    }

    @Override
    public Supplier create(String name, String address, String email, String telephoneNumber) throws SQLException {
        if (name.isBlank() || address.isBlank() || email.isBlank() || telephoneNumber.isBlank()) {
            throw new CreatePharmacyDataNotFound();
        }
        List<Supplier> suppliers = supplierDao.findByEmail(email);
        if (suppliers.toArray().length > 0) {
            throw new PharmacyExistsException();
        }

        Supplier supplier = new Supplier(name, address, email, telephoneNumber);
        return supplierDao.create(supplier);
    }
}