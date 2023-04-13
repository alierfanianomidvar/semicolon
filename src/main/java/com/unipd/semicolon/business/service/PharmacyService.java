package com.unipd.semicolon.business.service;



import com.unipd.semicolon.core.entity.enums.PharmacyStatus;

public interface PharmacyService {


    Boolean activation(Long id, PharmacyStatus status);


}
