package com.unipd.semicolon.business.service;

import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Order save(
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
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
