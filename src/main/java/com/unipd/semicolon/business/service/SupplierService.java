package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService {

        List<Supplier> getSupplierList() throws SQLException;

        Supplier create(String name,
                        String address,
                        String email,
                        String telephoneNumber) throws SQLException;

        Object findBySupplierId(Long id);

        public Supplier save(
                        String name,
                        String address,
                        String email,
                        String telephoneNumber,
                        List<Drug> drugs,
                        List<Material> materials);

        boolean edit(
                        Long id,
                        String name,
                        String address,
                        String email,
                        String telephoneNumber,
                        List<Drug> drugs,
                        List<Material> materials);

        boolean remove(
                        Long id);

}