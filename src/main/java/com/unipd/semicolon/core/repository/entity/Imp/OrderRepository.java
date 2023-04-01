package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find order by id
    Optional<Order> findById(Long id);

    // Save an order to the database
    Order save(Order order);

    // Delete an order from the database
    void delete(Order order);

    // Get all orders
    List<Order> findAll();

    // Get orders by status
    List<Order> findByStatus(boolean status);

    // Get orders by date
    List<Order> findByDate(LocalDate date);

    // Get orders by total price range
    List<Order> findByTotalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
