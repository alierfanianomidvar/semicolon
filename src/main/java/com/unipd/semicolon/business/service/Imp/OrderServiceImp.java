package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.InvalidTokenException;
import com.unipd.semicolon.business.mapper.OrderMapper;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import com.unipd.semicolon.core.repository.entity.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;


    @Override
    public Order save(
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive,
            Pharmacy pharmacy) {

        Pharmacy pharmacyExist = Optional.ofNullable(pharmacyRepository.findById(pharmacy.getId()))
                .orElseThrow(() -> new EntityNotFoundException()).get();

        if (orderDrugs.isEmpty() && orderMaterials.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameter");
        }

        Order order = new Order(
                orderDate,
                status,
                price,
                isActive,
                pharmacyExist);
        Order save = orderRepository.save(order);

        if (!orderDrugs.isEmpty()) {
            Map<Drug, Integer> drugIntegerMap = drugListCheck(orderDrugs);
            for (Drug drug : drugIntegerMap.keySet()) {
                orderProductsRepository.save(new OrderProduct(
                        save,
                        drug,
                        null,
                        drugIntegerMap.get(drug)
                ));
            }
        } else if (!orderMaterials.isEmpty()) {
            Map<Material, Integer> materialIntegerMap = materialListCheck(orderMaterials);
            for (Material material : materialIntegerMap.keySet()) {
                orderProductsRepository.save(new OrderProduct(
                        save,
                        null,
                        material,
                        materialIntegerMap.get(material)
                ));
            }
        }
        return save;
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
        if (order != null) {
            return order;
        } else {
            throw new EntityNotFoundException("Order Not Found with id" + id);
        }
    }

    @Override
    public List<OrderResponse> getAll() {
        List<OrderResponse> orderList = new ArrayList<>();
        for (Order order : orderRepository.getAll()) {
            orderList.add(OrderMapper.orderResponse(order));
        }
        return orderList;
    }

    @Override
    public Order status(
            Long orderId,
            OrderStatus orderStatus
    ) {
        Order order = orderRepository.findOrderById(orderId);
        if (order != null){
            order.setStatus(orderStatus);
            if(orderStatus.equals(OrderStatus.DELIVERED)){

            }
            return orderRepository.save(order);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /* -----  private classes  ----- */

    private Map<Drug, Integer> drugListCheck(Map<Long, Integer> orderDrugList) {

        Map<Drug, Integer> newMapDrug = new HashMap<>();
        for (Long drugId : orderDrugList.keySet()) {
            Drug drug = drugRepository.findById(drugId);
            if (drug != null) {
                newMapDrug.put(drug, orderDrugList.get(drugId));
            } else {
                throw new EntityNotFoundException();
            }
        }
        return newMapDrug;
    }

    private Map<Material, Integer> materialListCheck(Map<Long, Integer> materialList) {
        Map<Material, Integer> newMaterialMap = new HashMap<>();
        for (Long materialId : materialList.keySet()) {
            Material material = materialRepository.findMaterialById(materialId);
            if (material != null) {
                newMaterialMap.put(material, materialList.get(materialId));
            } else {
                throw new EntityNotFoundException();
            }
        }
        return newMaterialMap;
    }
}
