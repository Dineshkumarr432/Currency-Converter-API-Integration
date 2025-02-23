package com.example.currencyconverter.mapper;

import com.example.currencyconverter.dto.ExchangeRateDTO;
import com.example.currencyconverter.entity.ExchangeRateEntity;

public interface ExchangeRateMapper {
    ExchangeRateDTO toDTO(ExchangeRateEntity entity);
}