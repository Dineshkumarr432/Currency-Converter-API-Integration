package com.example.currencyconverter.dto;

public class ConversionRequestDTO {
	private String from;
	private String to;
	private double amount;

	// Getters and Setters
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}