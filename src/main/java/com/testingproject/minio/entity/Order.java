package com.testingproject.minio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private Integer customerId;
    private String product;
    private Integer quantity;

}
