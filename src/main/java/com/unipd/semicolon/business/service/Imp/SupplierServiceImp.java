package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CreatePharmacyDataNotFound;
import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.PermissionException;
import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.*;
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

    @Autowired
    private DrugService drugService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SecurityService securityService;

    private SupplierDao supplierDao;

    public SupplierServiceImp() {
        this.supplierDao = new SupplierDao();
    }

    @Override
    public List<Supplier> getSupplierList() throws SQLException {
        return supplierDao.findAll();
    }

    @Override
    public Supplier create(
            String name,
            String address,
            String email,
            String telephoneNumber,
            String token
    ) throws SQLException {

        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.equals("superAdmin")) {
            if (name.isBlank() || address.isBlank() || email.isBlank() || telephoneNumber.isBlank()) {
                throw new CreatePharmacyDataNotFound();
            }
            List<Supplier> suppliers = supplierDao.findByEmail(email);
            if (suppliers.toArray().length > 0) {
                throw new PharmacyExistsException();
            }

            Supplier supplier = new Supplier(name, address, email, telephoneNumber);
            return supplierDao.create(supplier);
        } else {
            throw new PermissionException(token);
        }

    }

    @Override
    public Object findBySupplierId(Long id) {
        if (Objects.isNull(supplierRepository.findById(id))) {
            throw new EntityNotFoundException("Supplier Not Found with id" + id);
        }
        return supplierRepository.findById(id);
    }

    @Transactional
    @Override
    public Supplier save(
            String name,
            String address,
            String email,
            String telephoneNumber,
            List<Drug> drugs,
            List<Material> materials,
            String token
    ) throws SQLException {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.equals("superAdmin")) {
            if (name == null || address == null || email == null ||
                    telephoneNumber == null || drugs == null || materials == null) {
                throw new IllegalArgumentException("Invalid input parameter");
            } else {
                Supplier supplier = new Supplier(
                        name,
                        address,
                        email,
                        telephoneNumber
                );

                supplierRepository.saveSupplier(supplier);
                //First supplier is added to the database.
                //add drugs if not null
                if (!drugs.isEmpty()) {
                    for (Drug drug : drugs) {
                        drugService.save(
                                drug.getName(),
                                drug.getSupplier().getId(),
                                drug.getExpirationDate(),
                                drug.getImage(),
                                drug.getShape(),
                                drug.getGender(),
                                drug.getAgeGroup(),
                                drug.isSensitive(),
                                drug.isNeedPrescription(),
                                drug.getDescription(),
                                drug.getLimitation(),
                                drug.getPrice(),
                                drug.getCountryOFProduction()
                        );
                    }
                }

                if (!materials.isEmpty()) {
                    for (Material material : materials) {
                        materialService.save(
                                material.getName(),
                                material.getSupplier().getId(),
                                material.getCountryOfProduction(),
                                material.getExpirationDate(),
                                material.getImage(),
                                material.getGender(),
                                material.getPrice(),
                                material.getAgeGroup(),
                                material.getLastModifiedDate(),
                                material.getDescription()
                        );
                    }
                }
                return supplier;
            }
        } else {
            throw new PermissionException(token);
        }

    }

    @Transactional
    @Override
    public boolean edit(Long id,
                        String name,
                        String address,
                        String email,
                        String telephoneNumber,
                        List<Drug> drugs,
                        List<Material> materials,
                        String token
    ) throws SQLException {
        String roleFromToken = securityService.getRoleFromToken(token);
        if (roleFromToken.equals("superAdmin")) {
            if (Objects.nonNull(supplierRepository.findById(id))) {
                Supplier supplier = supplierRepository.findById(id);
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
                if (drugs != null && !drugs.isEmpty()) {
                    for (Drug drug : drugs) {
                        if (drugService.findDrugsByNameAndSupplierAndExpirationDateAndShapeAndAgeGroupAndCountryOFProduction(
                                drug.getName(),
                                drug.getSupplier(),
                                drug.getExpirationDate(),
                                drug.getShape(),
                                drug.getAgeGroup(),
                                drug.getCountryOFProduction()
                        ).isEmpty()) {
                            drugService.save(
                                    drug.getName(),
                                    drug.getSupplier().getId(),
                                    drug.getExpirationDate(),
                                    drug.getImage(),
                                    drug.getShape(),
                                    drug.getGender(),
                                    drug.getAgeGroup(),
                                    drug.isSensitive(),
                                    drug.isNeedPrescription(),
                                    drug.getDescription(),
                                    drug.getLimitation(),
                                    drug.getPrice(),
                                    drug.getCountryOFProduction()
                            );
                        }

                    }
                    supplier.setDrugs(drugs);
                }
                if (materials != null && !materials.isEmpty()) {
                    //TODO: first we need to check if the drug or material exist
                    for (Material material : materials) {
                        materialService.save(
                                material.getName(),
                                material.getSupplier().getId(),
                                material.getCountryOfProduction(),
                                material.getExpirationDate(),
                                material.getImage(),
                                material.getGender(),
                                material.getPrice(),
                                material.getAgeGroup(),
                                material.getLastModifiedDate(),
                                material.getDescription()
                        );
                    }
                    supplier.setMaterials(materials);
                }
                supplierRepository.saveSupplier(supplier);
                return true;
            } else {
                return false;
            }
        } else {
            throw new PermissionException(token);
        }

    }

    @Transactional
    @Override
    public boolean remove(Long id, String token) {
        if (Objects.isNull(supplierRepository.findById(id))) {
            throw new IllegalArgumentException("Supplier with this id could not found!");
        }
        Supplier supplier = supplierRepository.findById(id);
        supplierRepository.deleteSupplier(supplier);
        return true;
    }
}