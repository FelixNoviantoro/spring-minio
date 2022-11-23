package com.testingproject.minio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Save{
        private Integer customerId;
        private String product;
        private Integer quantity;
    }

}
