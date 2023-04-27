package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.EntityNotFoundException;
import com.unipd.semicolon.business.exception.InvalidParameterException;
import com.unipd.semicolon.business.mapper.OrderMapper;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.business.service.StorageService;
import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import com.unipd.semicolon.core.repository.entity.*;
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

    @Autowired
    private StorageService storageService;


    @Override
    public Order save(
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive,
            Pharmacy pharmacy) throws CustomException {


        Pharmacy pharmacyExist = pharmacyRepository.findById(pharmacy.getId())
                .orElseThrow(() -> new EntityNotFoundException("Pharmacy does not exist"));

        if (orderDrugs.isEmpty() && orderMaterials.isEmpty()) {
            throw new InvalidParameterException();
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
    public Order getById(Long id) throws CustomException {

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
        if (order != null) {
            order.setStatus(orderStatus);
            if (orderStatus.equals(OrderStatus.DELIVERED)) {
                for (OrderProduct orderProduct : order.getOrderProducts()) {
                    Storage storage = storageService.storageExist(
                            order.getPharmacy(),
                            orderProduct.getDrug(),
                            orderProduct.getMaterial()
                    );
                    if (storage != null) {
                        storageService.updateStorage(
                                storage,
                                orderProduct.getQuantity() + storage.getAmount()
                        );
                    } else {
                        //TODO: validation to use the correct token
                        List<Long> drugList = new ArrayList<>();
                        drugList.add(orderProduct.getDrug().getId());
                        List<Long> materialList = new ArrayList<>();
                        materialList.add(orderProduct.getMaterial().getId());
                        storageService.save(
                                order.getPharmacy().getId(),
                                drugList,
                                materialList,
                                orderProduct.getQuantity(),
                                1,
                                1,
                                ""
                        );
                    }
                }

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
            Drug drug = drugRepository.findById(drugId)
                    .orElseThrow(() -> new EntityNotFoundException("Drug not found - " + drugId));
            newMapDrug.put(drug, orderDrugList.get(drugId));
        }
        return newMapDrug;
    }

    private Map<Material, Integer> materialListCheck(Map<Long, Integer> materialList) {
        Map<Material, Integer> newMaterialMap = new HashMap<>();
        for (Long materialId : materialList.keySet()) {
            Material material = materialRepository.findById(materialId)
                    .orElseThrow(() -> new EntityNotFoundException("Material not found - " + materialId));
            newMaterialMap.put(material, materialList.get(materialId));
        }
        return newMaterialMap;
    }
}
