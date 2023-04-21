package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CreatePharmacyDataNotFound;
import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.dao.SupplierDao;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.repository.entity.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class SupplierServiceImp implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


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

    @Override
    public Object findBySupplierId(Long id) {
        if(Objects.isNull(supplierRepository.findBySupplierId(id))) {
            throw new EntityNotFoundException("Supplier Not Found with id" + id);
        }
        return supplierRepository.findBySupplierId(id);
    }

    @Transactional
    @Override
    public Supplier save(String name, String address, String email, String telephoneNumber, List<Drug> drugs, List<Material> materials) {
        if (name == null || address == null || email == null ||
                telephoneNumber == null || drugs == null || materials == null ) {
            throw new IllegalArgumentException("Invalid input parameter");
        } else {
            Supplier supplier = new Supplier(
                    name,
                    address,
                    email,
                    telephoneNumber,
                    drugs,
                    materials);

            supplierRepository.saveSupplier(supplier);
            return supplier;
        }
    }

    @Transactional
    @Override
    public boolean edit(Long id, String name, String address, String email, String telephoneNumber, List<Drug> drugs, List<Material> materials) {
        if (Objects.nonNull(supplierRepository.findBySupplierId(id))) {
            Supplier supplier = supplierRepository.findBySupplierId(id);
            if (name != null) {
                supplier.setName(name);
            }
            if (address != null) {
                supplier.setAddress(address);
            }
            if (email != null) {
                supplier.setEmail(email);
            }
            if (telephoneNumber != null) {
                supplier.setTelephoneNumber(telephoneNumber);
            }
            if (drugs != null) {
                supplier.setDrugs(drugs);
            }
            if (materials != null) {
                supplier.setMaterials(materials);
            }
            supplierRepository.saveSupplier(supplier);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        if(Objects.isNull(supplierRepository.findBySupplierId(id))) {
            throw new IllegalArgumentException("Supplier with this id could not found!");
        }
        Supplier supplier = supplierRepository.findBySupplierId(id);
        supplierRepository.deleteSupplier(supplier);
        return true;
    }
}