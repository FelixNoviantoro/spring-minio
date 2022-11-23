package com.testingproject.minio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Save{
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String imageUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private Integer id;
        private String address;
        private String city;
        private String imageUrl;
    }

}
