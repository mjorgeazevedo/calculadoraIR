package com.mjorgeazevedo.incomeTaxCalculator.entity;

public class StatusInvestTransaction extends Transaction {
	
	@Override
	public double getAmount() {
		double value = (this.getQuantity() * this.getUnitPrice());
		double costs = this.getBrokerage() + this.getFees() + this.getTaxes() + this.getIncomeTaxes();
		
		if (Operation.PURCHASE.equals(this.getOperation())) {
			value += costs;
		} else {
			value -= costs;
		}
		
		return value;
	}
}
