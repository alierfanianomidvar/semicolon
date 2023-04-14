package com.unipd.semicolon.business.service.Imp;

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
}
