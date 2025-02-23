package com.example.currencyconverter.service.impl;

import com.example.currencyconverter.config.ApiConfig;
import com.example.currencyconverter.dto.ConversionRequestDTO;
import com.example.currencyconverter.dto.ConversionResponseDTO;
import com.example.currencyconverter.dto.ExchangeRateDTO;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import com.example.currencyconverter.exception.CurrencyConversionException;
import com.example.currencyconverter.mapper.ExchangeRateMapper;
import com.example.currencyconverter.service.CurrencyConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;
    private final ExchangeRateMapper exchangeRateMapper;

    public CurrencyConversionServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate, ExchangeRateMapper exchangeRateMapper) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @Override
    public ExchangeRateDTO getExchangeRates(String base) {
        String url = apiConfig.getApiUrl() + "&base=" + base;
        ExchangeRateEntity entity = restTemplate.getForObject(url, ExchangeRateEntity.class);
        if (entity == null || entity.getRates() == null) {
            throw new CurrencyConversionException("Failed to fetch exchange rates.");
        }
        return exchangeRateMapper.toDTO(entity);
    }

    @Override
    public ConversionResponseDTO convertCurrency(ConversionRequestDTO request) {
        ExchangeRateDTO exchangeRateDTO = getExchangeRates(request.getFrom());
        double rate = exchangeRateDTO.getRates().get(request.getTo());
        if (rate == 0) {
            throw new CurrencyConversionException("Invalid currency code.");
        }

        ConversionResponseDTO response = new ConversionResponseDTO();
        response.setFrom(request.getFrom());
        response.setTo(request.getTo());
        response.setAmount(request.getAmount());
        response.setConvertedAmount(request.getAmount() * rate);
        return response;
    }
}
