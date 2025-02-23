package com.example.currencyconverter.service.imp;

import com.example.currencyconverter.config.ApiConfig;
import com.example.currencyconverter.dto.ConversionRequestDTO;
import com.example.currencyconverter.dto.ConversionResponseDTO;
import com.example.currencyconverter.dto.ExchangeRateDTO;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import com.example.currencyconverter.exception.CurrencyConversionException;
import com.example.currencyconverter.mapper.ExchangeRateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyConversionServiceImplTest {

    @Mock
    private ApiConfig apiConfig;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExchangeRateMapper exchangeRateMapper;

    @InjectMocks
    private CurrencyConversionServiceImplTest currencyConversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExchangeRates_Success() {
        // Arrange
        String base = "USD";
        String url = "https://api.exchangeratesapi.io/v1/latest?access_key=YOUR_API_KEY&base=USD";

        ExchangeRateEntity entity = new ExchangeRateEntity();
        entity.setBase(base);
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        entity.setRates(rates);

        ExchangeRateDTO dto = new ExchangeRateDTO();
        dto.setBase(base);
        dto.setRates(rates);

        when(apiConfig.getApiUrl()).thenReturn("https://api.exchangeratesapi.io/v1/latest?access_key=YOUR_API_KEY");
        when(restTemplate.getForObject(url, ExchangeRateEntity.class)).thenReturn(entity);
        when(exchangeRateMapper.toDTO(entity)).thenReturn(dto);

        // Act
        ExchangeRateDTO result = currencyConversionService.getExchangeRates(base);

        // Assert
        assertNotNull(result);
        assertEquals(base, result.getBase());
        assertEquals(0.85, result.getRates().get("EUR"));
        verify(restTemplate, times(1)).getForObject(url, ExchangeRateEntity.class);
        verify(exchangeRateMapper, times(1)).toDTO(entity);
    }

    @Test
    void testGetExchangeRates_Failure() {
        // Arrange
        String base = "USD";
        String url = "https://api.exchangeratesapi.io/v1/latest?access_key=YOUR_API_KEY&base=USD";

        when(apiConfig.getApiUrl()).thenReturn("https://api.exchangeratesapi.io/v1/latest?access_key=YOUR_API_KEY");
        when(restTemplate.getForObject(url, ExchangeRateEntity.class)).thenReturn(null);

        // Act & Assert
        assertThrows(CurrencyConversionException.class, () -> currencyConversionService.getExchangeRates(base));
        verify(restTemplate, times(1)).getForObject(url, ExchangeRateEntity.class);
    }

    @Test
    void testConvertCurrency_Success() {
        // Arrange
        ConversionRequestDTO request = new ConversionRequestDTO();
        request.setFrom("USD");
        request.setTo("EUR");
        request.setAmount(100);

        ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        exchangeRateDTO.setRates(rates);

        when(currencyConversionService.getExchangeRates("USD")).thenReturn(exchangeRateDTO);

        // Act
        ConversionResponseDTO result = currencyConversionService.convertCurrency(request);

        // Assert
        assertNotNull(result);
        assertEquals("USD", result.getFrom());
        assertEquals("EUR", result.getTo());
        assertEquals(100, result.getAmount());
        assertEquals(85.0, result.getConvertedAmount());
    }

    private ConversionResponseDTO convertCurrency(ConversionRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    void testConvertCurrency_InvalidCurrency() {
        // Arrange
        ConversionRequestDTO request = new ConversionRequestDTO();
        request.setFrom("USD");
        request.setTo("INVALID");
        request.setAmount(100);

        ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        exchangeRateDTO.setRates(rates);

        when(currencyConversionService.getExchangeRates("USD")).thenReturn(exchangeRateDTO);

        // Act & Assert
        assertThrows(CurrencyConversionException.class, () -> currencyConversionService.convertCurrency(request));
    }

	private ExchangeRateDTO getExchangeRates(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}