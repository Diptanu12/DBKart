package com.example.DbKart.controller;


import com.example.DbKart.dto.request.OrderRequest;
import com.example.DbKart.dto.response.OrderResponse;
import com.example.DbKart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity placeOrder(@RequestBody OrderRequest orderRequest) {

        try {
            OrderResponse orderResponse = orderService.placeOrder(orderRequest);
            return new ResponseEntity(orderResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
