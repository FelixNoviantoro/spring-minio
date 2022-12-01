package com.testingproject.minio.controller;

import com.testingproject.minio.dto.OrderDto;
import com.testingproject.minio.entity.Order;
import com.testingproject.minio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> update(
            @RequestBody OrderDto.Save data, @PathVariable("orderId") Integer orderId
    ) {
        System.out.println("masukkkkkkkkkkkkkkkkkkkk update");
        Order order = this.service.update(orderId, data);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findByOrderId(
            @PathVariable("orderId") Integer orderId
    ){
        System.out.println("masukkkkkkkkkkkkkkkkkkkk findByOrderId");
//        return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
        Order order = this.service.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(
            @PathVariable("id") Integer id
    ){
        System.out.println("masukkkkkkkkkkkkkkkkkkkk delete");

        try {
            this.service.delete(id);
            return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
