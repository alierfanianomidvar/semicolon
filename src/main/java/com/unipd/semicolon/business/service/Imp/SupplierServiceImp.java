package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.dao.SupplierDao;
import com.unipd.semicolon.core.entity.Supplier;

import java.util.List;

public class SupplierServiceImp implements SupplierService {

    private SupplierDao supplierDao;

    public SupplierServiceImp() {
            this.supplierDao = new SupplierDao();
    }
    @Override
    public List<Supplier> getSupplierList() {
        return supplierDao.findAll();
    }

    @Override
    public Supplier create(String name, String address, String email, String telephoneNumber) {
        List<Supplier> suppliers = supplierDao.findByEmail(email);
        if (suppliers.toArray().length > 0) {
            throw new PharmacyExistsException();
        }


        Supplier supplier = new Supplier(name,address,email,telephoneNumber);
        return supplierDao.create(supplier);
    }
}
