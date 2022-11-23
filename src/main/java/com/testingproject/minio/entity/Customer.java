package com.testingproject.minio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String imageUrl;
}
