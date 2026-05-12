package com.myproject.sdd.sdd_demo.controller;

import com.myproject.api.OrdersApi;
import com.myproject.model.OrderDto;
import com.myproject.sdd.sdd_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements the contract defined in openapi.yaml via the generated OrdersApi interface.
 * Exception handling (404) is delegated to GlobalExceptionHandler.
 */
@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> getOrderById(Integer id) {
        OrderDto order = orderService.getOrder(id);  // throws OrderNotFoundException → 404
        return ResponseEntity.ok(order);
    }
}
