package com.unipd.semicolon.business.mapper;

import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Order;

public class OrderMapper {
    public static OrderResponse orderResponse(Order order){
        return new OrderResponse(
                order.getOrderDate(),
                null,
                null,
                order.getStatus(),
                order.getPrice(),
                order.isActive()
        );
    }
}
