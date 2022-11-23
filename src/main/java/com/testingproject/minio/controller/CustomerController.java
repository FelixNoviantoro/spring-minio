package com.testingproject.minio.controller;

import com.testingproject.minio.dto.CustomerDto;
import com.testingproject.minio.entity.Customer;
import com.testingproject.minio.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = this.service.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(
            @PathVariable Integer id
    ) {
        Customer customer = this.service.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> save(
            @RequestBody CustomerDto.Save data
    ) {
        Integer id = this.service.save(data);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/update")
    public void update(
            @RequestBody CustomerDto.Update data
    ) {
        this.service.update(data);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        this.service.delete(id);
    }

}
