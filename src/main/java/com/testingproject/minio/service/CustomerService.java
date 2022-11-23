package com.testingproject.minio.service;

import com.testingproject.minio.dao.CustomerDao;
import com.testingproject.minio.dto.CustomerDto;
import com.testingproject.minio.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao dao;

    public Integer save(CustomerDto.Save data){
        return this.dao.save(data);
    }

    public List<Customer> findAll(){
        return this.dao.findAll();
    }

    public Customer findById(Integer id){
        return this.dao.findById(id).orElseThrow(() -> new RuntimeException("customer dengan id " + id + " tidak ditemukan"));
    }

    public void update(CustomerDto.Update data){
        findById(data.getId());
        this.dao.update(data);
    }

    public void delete(Integer id){
        findById(id);
        this.dao.delete(id);
    }

}
