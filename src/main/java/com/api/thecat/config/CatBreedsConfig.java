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

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cat")
public class CatBreedsConfig {

    private String url_cat;
    private String url_address;

    private Integer connectTimeout;
    private Integer readTimeout;

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

}
