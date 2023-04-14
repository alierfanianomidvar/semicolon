package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.PharmacyStatus;
import com.unipd.semicolon.core.repository.entity.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PharmacyServiceImp implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyServiceImp(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Boolean activation(Long pharmacyId, PharmacyStatus status) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new IllegalStateException("Pharmacy not found - " + pharmacyId));


        if (status != null && !Objects.equals(status, pharmacy.getStatus())) {
            pharmacy.setStatus(status);
            if (pharmacy.getStaff().toArray().length > 0) {
                for (User user: pharmacy.getStaff() ) {
                    user.setAccountStatus(status.toString());
                }
            }
        } else {
            throw new IllegalStateException("email taken before!");
        }

        return true;
    }
}
