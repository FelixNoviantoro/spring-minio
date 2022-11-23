package com.testingproject.minio.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder restBuilder) {
        return restBuilder.requestFactory(() ->
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .interceptors().build();
    }

}
