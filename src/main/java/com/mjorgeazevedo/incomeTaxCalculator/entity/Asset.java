package com.mjorgeazevedo.incomeTaxCalculator.entity;

public class Asset {
	private String id;
	private double initialQuantity;
	private double finalQuantity;
	private double initialTotalValue;
	private double finalTotalValue;
	
	public double getProfit() {
		if (finalQuantity == 0) {
			return finalTotalValue - initialTotalValue;
		} else {
			return 0;
		}
	}

	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public double getInitialQuantity() {
		return initialQuantity;
	}



	public void setInitialQuantity(double initialQuantity) {
		this.initialQuantity = initialQuantity;
	}



	public double getFinalQuantity() {
		return finalQuantity;
	}



	public void setFinalQuantity(double finalQuantity) {
		this.finalQuantity = finalQuantity;
	}



	public double getInitialTotalValue() {
		return initialTotalValue;
	}



	public void setInitialTotalValue(double initialTotalValue) {
		this.initialTotalValue = initialTotalValue;
	}



	public double getFinalTotalValue() {
		return finalTotalValue;
	}



	public void setFinalTotalValue(double finalTotalValue) {
		this.finalTotalValue = finalTotalValue;
	}



	@Override
	public String toString() {
		return "Asset [id=" + id + ", initialQuantity=" + initialQuantity + ", finalQuantity=" + finalQuantity
				+ ", initialTotalValue=" + initialTotalValue + ", finalTotalValue=" + finalTotalValue + ", getProfit()="
				+ getProfit() + "]";
	}
}
