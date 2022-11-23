package com.testingproject.minio.controller;

import com.testingproject.minio.dto.OrderDto;
import com.testingproject.minio.entity.Order;
import com.testingproject.minio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @GetMapping("/list")
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = this.service.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Order>> findByCustomerId(
            @PathVariable("customerId") Integer customerId
    ) {
        List<Order> orders = this.service.findByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/save")
    public ResponseEntity<Order> save(
            @RequestBody OrderDto.Save data
    ) {
        Order order = this.service.save(data);
        return ResponseEntity.ok(order);
    }

}
