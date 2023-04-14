package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.mapper.OrderMapper;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import com.unipd.semicolon.core.repository.entity.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(
            Long id,
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive){

        if (id == null || orderDate == null || price < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        }else {
            Order order = new Order(
                    orderDate,
                    status,
                    price,
                    isActive);
            List<Drug> newListDrug = new ArrayList<>();
            if(!orderDrugs.isEmpty()){
                for (Drug orderDrugs : orderDrugs) {
                    Drug drug = DrugRepository.getId(orderDrugs.getId());
                    newListDrug.add(drug);
                }
                order.setOrderDrugs(newListDrug);
            }

            List<Material> newListMaterial = new ArrayList<>();
            if(!orderMaterials.isEmpty()){
                for (Material orderMaterials : orderMaterials) {
                    Material material = MaterialRepository.findMaterialById(orderMaterials.getId());
                    newListMaterial.add(material);
                }
                order.setOrderMaterials(newListMaterial);
            }

            orderRepository.save(order);
            return order;
        }
    }

    @Override
    public boolean edit(
            Long id,
            LocalDate orderDate,
            List<Drug> orderDrugs,
            List<Material> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive) {
        if (id == null || orderDate == null || orderDrugs == null || orderMaterials == null || price < 0) {
            throw new IllegalArgumentException("Invalid input parameter");
        }else {
                Order order = orderRepository.findOrderById(id);
            if (order == null) {
                throw new EntityNotFoundException("Order Not Found with id" + id);
            }
                order.setOrderDate(orderDate);
                order.setOrderDrugs(orderDrugs);
                order.setOrderMaterials(orderMaterials);
                order.setStatus(status);
                order.setPrice(price);
                order.setActive(isActive);
                return true;
        }
    }

    @Override
    public void delete(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Can not delete null storage!");
        } else {
            orderRepository.delete(order);
        }
    }

    @Override
    public Order getById(Long id) {
        Order order = orderRepository.findOrderById(id);
        if(order != null) {
            return order;
        } else {
            throw new EntityNotFoundException("Order Not Found with id" + id);
        }
    }

    @Override
    public List<OrderResponse> getAll(){
        List<OrderResponse> orderList = new ArrayList<>();
        for (Order order : orderRepository.getAll()){
            orderList.add(OrderMapper.orderResponse(order));
        }
        return orderList;
    }

}
