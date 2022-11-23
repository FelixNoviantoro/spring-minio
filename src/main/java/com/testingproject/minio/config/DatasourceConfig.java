package com.testingproject.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    public MinioClient minioClient(
            @Value("${spring.minio.url}") String url,
            @Value("${spring.minio.access-key}") String accessKey,
            @Value("${spring.minio.storageAccountKey}") String storageAccountKey){
        try {
            return MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, storageAccountKey)
                    .build();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Bean
    @Primary
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    public DataSource dataSourceRead(
            @Value("${spring.datasource-read.url}") String url,
            @Value("${spring.datasource-read.username}") String username,
            @Value("${spring.datasource-read.password}") String password){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplateRead(@Qualifier("dataSourceRead") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplateWrite(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplateRead(@Qualifier("dataSourceRead") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
