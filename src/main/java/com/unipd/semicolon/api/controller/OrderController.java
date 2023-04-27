package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.OrderModel;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.enums.OrderReport;
import com.unipd.semicolon.business.exception.EntityNotFoundException;
import com.unipd.semicolon.business.exception.InvalidParameterException;
import com.unipd.semicolon.business.exception.PermissionException;
import com.unipd.semicolon.business.service.OrderService;
import com.unipd.semicolon.core.entity.Order;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Operation(summary = "Saving order",
            description = "Saving the order base the info")
    public ResponseEntity save(@RequestBody OrderModel model) {
        try {
            return ResponseHelper
                    .response(orderService.save(
                            model.getToken(),
                            model.getOrderDate(),
                            model.getOrderDrugs(),
                            model.getOrderMaterials(),
                            model.getStatus(),
                            model.getPrice(),
                            model.isActive(),
                            model.getPharmacy()
                    ));
        } catch (PermissionException e) {
            return ResponseHelper.response(
                    e.getData(),
                    e.getMsg(),
                    e.getStatus()
            );
        } catch (EntityNotFoundException e) {
            return ResponseHelper.response(
                    "Entity ID :" + model.getPharmacy().getId(),
                    e.getMsg(),
                    e.getStatus()
            );
        } catch (InvalidParameterException e) {
            return ResponseHelper.response(
                    " drags size : " + model.getOrderDrugs().size()
                            + "|" +
                            " Material size : " + model.getOrderMaterials().size(),
                    e.getMsg(),
                    e.getStatus()
            );
        }
    }

    @RequestMapping(value = "/{id}/{token}", method = RequestMethod.GET)
    @Operation(summary = "Getting order base an id.",
            description = "we will send order id and token.")
    public ResponseEntity getById(
            @PathVariable("id") Long id,
            @PathVariable("token") String token) {
        try {
            return ResponseHelper
                    .response(orderService.getById(token, id));
        } catch (PermissionException e) {
            return ResponseHelper.response(
                    e.getData(),
                    e.getMsg(),
                    e.getStatus()
            );
        } catch (EntityNotFoundException e) {
            return ResponseHelper.response(
                    "",
                    e.getMsg(),
                    e.getStatus()
            );
        }
    }

    @RequestMapping(value = "/{id}/{token}", method = RequestMethod.DELETE)
    @Operation(summary = "Deleting order",
            description = "we will send order id and token.")
    public ResponseEntity deleteById(
            @PathVariable("token") String token,
            @PathVariable("id") Long id) {
        try {
            orderService.delete(token, id);
            return ResponseHelper.response(true);
        } catch (PermissionException e) {
            return ResponseHelper.response(
                    e.getData(),
                    e.getMsg(),
                    e.getStatus()
            );
        }
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseHelper
                .response(orderService.getAll());
    }

    @RequestMapping(value = "/{id}/{status}/{token}", method = RequestMethod.PATCH)
    @Operation(summary = "Changing the status of order",
            description = "we will send order order id, status(Ex: DELIVERED) and  token.")
    public ResponseEntity status(
            @PathVariable("id") Long id,
            @PathVariable("status") OrderStatus status,
            @PathVariable("token") String token) {
        try {
            return ResponseHelper
                    .response(orderService.status(token, id, status));
        } catch (PermissionException e) {
            return ResponseHelper.response(
                    e.getData(),
                    e.getMsg(),
                    e.getStatus()
            );
        } catch (EntityNotFoundException e) {
            return ResponseHelper.response(
                    "ID : " + id
                            + "|" +
                            "Status :" + status,
                    e.getMsg(),
                    e.getStatus()
            );
        }
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity report(
            @RequestParam(required = true) String token,
            @RequestParam(required = false) OrderReport orderReport,
            @RequestParam(required = false) Short num) {
        try {
            return ResponseHelper
                    .response(orderService.reportBaseDate(
                            token,
                            orderReport,
                            num));
        } catch (PermissionException e) {
            return ResponseHelper.response(
                    e.getData(),
                    e.getMsg(),
                    e.getStatus()
            );
        } catch (EntityNotFoundException e) {
            return ResponseHelper.response(
                    "ID : " + 1
                            + "|" +
                            "Status :" + 2,
                    e.getMsg(),
                    e.getStatus()
            );
        }
    }


}
