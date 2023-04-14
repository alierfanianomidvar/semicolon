package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order save(
            Long id,
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive
    );

    boolean edit(
            Long id,
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive
    );

    void delete (
            Order order
    );

    Order getById(
            Long id
    );

    List<OrderResponse> getAll();
}
