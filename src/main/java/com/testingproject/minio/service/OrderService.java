package com.testingproject.minio.service;

import com.testingproject.minio.dto.OrderDto;
import com.testingproject.minio.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${order.host}")
    private String orderHost;

    private final RestTemplate restTemplate;

    public Order save(OrderDto.Save data) {
        System.out.println(" =========== host ======== " + orderHost);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/save";
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<OrderDto.Save> entity = new HttpEntity(data, headers);

        ResponseEntity<Order> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.POST,
                entity,
                Order.class);
        System.out.println("response =====> " + response.getBody());
        return response.getBody();

    }

    public Order update(Integer orderId, OrderDto.Save data) {
        System.out.println(" =========== host ======== " + orderHost);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/update/" + orderId;
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<OrderDto.Save> entity = new HttpEntity(data, headers);

        ResponseEntity<Order> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.PUT,
                entity,
                Order.class);
        System.out.println("response =====> " + response.getBody());
        return response.getBody();

    }

    public List<Order> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/list/";
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<Order[]> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.GET,
                entity,
                Order[].class);
        System.out.println("response =====> " + response.getBody());
        return Arrays.asList(response.getBody());
    }

    public List<Order> findByCustomerId(Integer customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/list/" + customerId;
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<Order[]> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.GET,
                entity,
                Order[].class);
        System.out.println("response =====> " + response.getBody());
        return Arrays.asList(response.getBody());
    }

    public Order findById(Integer orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/" + orderId;
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<Order> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.GET,
                entity,
                Order.class);
        System.out.println("response =====> " + response.getBody());
        return response.getBody();
    }

    public void delete(Integer orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = orderHost + "/api/order/" + orderId;
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<Order> response = this.restTemplate.exchange(
                urlTemplate.build().toUriString(),
                HttpMethod.DELETE,
                entity,
                Order.class);
        System.out.println("response =====> " + response.getBody());
    }
}
