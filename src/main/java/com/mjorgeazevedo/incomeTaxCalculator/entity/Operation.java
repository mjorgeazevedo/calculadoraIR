package com.mjorgeazevedo.incomeTaxCalculator.entity;

public enum Operation {
	SALE("V"),
	PURCHASE("C");

	private String value;
	
	Operation(String string) {
		this.value = string;
	}

	public String getValue() {
		return value;
	}
	
	public static Operation getOperation(String value) {
		for (Operation o : Operation.values()) {
			if (o.getValue().equals(value)) {
				return o;
			}
		}
		
		return null;
	}
}
