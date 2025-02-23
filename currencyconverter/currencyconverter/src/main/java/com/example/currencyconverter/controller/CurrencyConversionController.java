package com.example.currencyconverter.controller;

import com.example.currencyconverter.dto.ConversionRequestDTO;
import com.example.currencyconverter.dto.ConversionResponseDTO;
import com.example.currencyconverter.dto.ExchangeRateDTO;
import com.example.currencyconverter.service.CurrencyConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/rates")
    public ExchangeRateDTO getRates(@RequestParam(defaultValue = "USD") String base) {
        return currencyConversionService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public ConversionResponseDTO convertCurrency(@RequestBody ConversionRequestDTO request) {
        return currencyConversionService.convertCurrency(request);
    }
}