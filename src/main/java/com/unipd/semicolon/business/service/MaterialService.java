package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public interface MaterialService {
    Material save(
            String name,
            Supplier supplier,
            Country countryOfProduction,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            float price,
            AgeGroup ageGroup,
            LocalDate lastModifiedDate,
            String description,
            List<Order> orders,
            List<Receipt> receipts
    );

    boolean edit(
            Long id,
            String name,
            Supplier supplier,
            LocalDate expirationDate,
            byte[] image,
            Gender gender,
            AgeGroup ageGroup,
            float price,
            LocalDate lastModifiedDate,
            String description,
            Country countryOfProduction,
            List<Order> orders,
            List<Receipt> receipts);

    Material getById(Long id);
}
