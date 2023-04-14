package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.OrderModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.entity.Order;
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
                        model.getId(),
                        model.getOrderDate(),
                        model.getOrderDrugs(),
                        model.getOrderMaterials(),
                        model.getStatus(),
                        model.getPrice(),
                        model.isActive()
                ));
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseHelper
                .response(orderService.getById(id));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody OrderModel model){
        return ResponseHelper
                .response(orderService.edit(
                        id,
                        model.getOrderDate(),
                        model.getOrderDrugs(),
                        model.getOrderMaterials(),
                        model.getStatus(),
                        model.getPrice(),
                        model.isActive()
                ));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        orderService.delete(order);
        return ResponseHelper.response(true);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(orderService.getAll());
    }

}
