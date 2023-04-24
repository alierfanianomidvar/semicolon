package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.OrderProduct;

import java.util.List;

public class OrderMapper {
    public static OrderResponse orderResponse(Order order) {
        return new OrderResponse(
                order.getOrderDate(),
                order.getStatus(),
                order.getPrice(),
                order.isActive(),
                order.getOrderProducts()
        );
    }
}
