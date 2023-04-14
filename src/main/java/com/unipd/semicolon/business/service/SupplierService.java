package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> getSupplierList();

    Supplier create(String name,
                    String address,
                    String email,
                    String telephoneNumber);
}
