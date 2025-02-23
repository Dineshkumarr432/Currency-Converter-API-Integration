package com.example.currencyconverter.service;

import com.example.currencyconverter.dto.ConversionRequestDTO;
import com.example.currencyconverter.dto.ConversionResponseDTO;
import com.example.currencyconverter.dto.ExchangeRateDTO;

public interface CurrencyConversionService {
    ExchangeRateDTO getExchangeRates(String base);
    ConversionResponseDTO convertCurrency(ConversionRequestDTO request);
}