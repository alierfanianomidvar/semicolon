package com.unipd.semicolon.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    // order Id
    private Long id;

    // Map of order items with drug id as key and amount as value
    private Map<Long, Integer> orderItems;

    // Total price of the order
    private BigDecimal totalPrice;

    // Date when the order was placed
    private LocalDate date;

    // Status of the order (true for processed, false for pending)
    private boolean status;

    // Constructor
    public Order(Long id, Map<Long, Integer> orderItems, BigDecimal totalPrice, LocalDate date, boolean status) {
        this.id = id;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Long, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Helper methods

    // Add an order item to the map
    public void addItem(Long drugId, int amount) {
        if (orderItems == null) {
            orderItems = new HashMap<>();
        }
        if (orderItems.containsKey(drugId)) {
            int currentAmount = orderItems.get(drugId);
            orderItems.put(drugId, currentAmount + amount);
        } else {
            orderItems.put(drugId, amount);
        }
    }

    // Remove an order item from the map
    public void removeItem(Long drugId) {
        if (orderItems != null) {
            orderItems.remove(drugId);
        }
    }

    // Calculate the total price of the order based on the drug prices
    public void calculateTotalPrice(Map<Long, BigDecimal> drugPrices) {
        BigDecimal total = BigDecimal.ZERO;
        if (orderItems != null && drugPrices != null) {
            for (Map.Entry<Long, Integer> entry : orderItems.entrySet()) {
                Long drugId = entry.getKey();
                Integer amount = entry.getValue();
                BigDecimal price = drugPrices.get(drugId);
                if (price != null) {
                    total = total.add(price.multiply(BigDecimal.valueOf(amount)));
                }
            }
        }
        totalPrice = total;
    }

    // Change status function boolean "True" and "False"
    public void changeStatus() {
        status = !status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
