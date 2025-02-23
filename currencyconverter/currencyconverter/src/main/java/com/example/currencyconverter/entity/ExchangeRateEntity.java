package com.example.currencyconverter.entity;

import java.util.Map;

public class ExchangeRateEntity {
	private String base;
	private Map<String, Double> rates;

	// Getters and Setters
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}