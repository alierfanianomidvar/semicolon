package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import com.unipd.semicolon.core.repository.entity.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImp implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyServiceImp(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Boolean activation(Long id, PharmacyStatus status) {
        return true;
    }
}
