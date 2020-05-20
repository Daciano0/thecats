package com.api.thecat.config;

import com.api.thecat.exceptions.RestTemplateResponseErrorHandler;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Optional;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "thecat")
public class CatBreedsConfig {

    private String url_cat;
    private String url_address;

    private Integer connectTimeout = 300;
    private Integer readTimeout = 300;

    private CatBreedsConfig catBreedsConfig;

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(Optional.ofNullable(connectTimeout).orElse(300)))
                .setReadTimeout(Duration.ofMillis(Optional.ofNullable(connectTimeout).orElse(300)))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

}
