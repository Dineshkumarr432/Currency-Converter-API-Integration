package com.example.currencyconverter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Value("${currency.api.url}")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }
}