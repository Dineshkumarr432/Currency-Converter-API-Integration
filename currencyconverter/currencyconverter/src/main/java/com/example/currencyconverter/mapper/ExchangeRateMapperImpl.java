package com.example.currencyconverter.mapper;

import com.example.currencyconverter.dto.ExchangeRateDTO;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateMapperImpl implements ExchangeRateMapper {
    @Override
    public ExchangeRateDTO toDTO(ExchangeRateEntity entity) {
        ExchangeRateDTO dto = new ExchangeRateDTO();
        dto.setBase(entity.getBase());
        dto.setRates(entity.getRates());
        return dto;
    }
}
