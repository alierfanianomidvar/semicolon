package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.OrderModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/order")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save (@RequestBody OrderModel model){
        return ResponseHelper
                .response(orderService.save(
                        model.getOrderDate(),
                        model.getOrderDrugs(),
                        model.getOrderMaterials(),
                        model.getStatus(),
                        model.getPrice(),
                        model.isActive(),
                        model.getPharmacy()
                ));
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(orderService.getById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        orderService.delete(order);
        return ResponseHelper.response(true);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(orderService.getAll());
    }

    @RequestMapping(value = "/status/{id}/{status}", method = RequestMethod.PATCH)
    public ResponseEntity status(@PathVariable("id") Long id,@PathVariable("status") OrderStatus status) {
        return ResponseHelper
                .response(orderService.status(id,status));
    }



}
