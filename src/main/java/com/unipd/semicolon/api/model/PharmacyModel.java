package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.enums.PharmacyStatus;

public class PharmacyModel {

    private PharmacyStatus status;

    public PharmacyModel() {
    }

    public PharmacyModel(PharmacyStatus status) {
        this.status = status;
    }

    public PharmacyStatus getStatus() {
        return status;
    }
}
