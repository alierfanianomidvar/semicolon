package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository{
    Order save(Order order);

    List<Order> getAll();

    Order findOrderById(Long id);

    void delete(Order order);

}