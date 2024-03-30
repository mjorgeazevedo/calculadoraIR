package com.mjorgeazevedo.incomeTaxCalculator.entity;

import java.time.LocalDate;

public abstract class Transaction {
	
	private LocalDate date;
	private Category category;
	private String id;
	private Operation operation;
	private double quantity;
	private double unitPrice;
	private String stockBroker;
	private double brokerage;
	private double fees;
	private double taxes;
	private double incomeTaxes;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getStockBroker() {
		return stockBroker;
	}
	public void setStockBroker(String stockBroker) {
		this.stockBroker = stockBroker;
	}
	public double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getIncomeTaxes() {
		return incomeTaxes;
	}
	public void setIncomeTaxes(double incomeTaxes) {
		this.incomeTaxes = incomeTaxes;
	}

	public abstract double getAmount();
	
	@Override
	public String toString() {
		return "Transaction [date=" + date + ", category=" + category + ", id=" + id + ", operation=" + operation
				+ ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", stockBroker=" + stockBroker
				+ ", brokerage=" + brokerage + ", fees=" + fees + ", taxes=" + taxes + ", incomeTaxes=" + incomeTaxes
				+ ", amount()=" + getAmount() + "]";
	}
}
