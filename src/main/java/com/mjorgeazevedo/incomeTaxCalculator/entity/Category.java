package com.mjorgeazevedo.incomeTaxCalculator.entity;

public enum Category {
	STOCK("Ações"),
	REAL_STATE_FUND("Fundos imobiliários"),
	INVESTMENT_FUND("Fundos de Investimento"),
	AGRICULTURAL_FUND("FIAGRO"),
	CRYPTOCURRENCY("Criptomoedas"),
	PUBLIC_DEBIT_TITLE("Tesouro direto");
	
	private String value;
	
	Category(String string) {
		this.value = string;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Category getCategory(String value) {
		for (Category c : Category.values()) {
			if (c.getValue().equals(value)) {
				return c;
			}
		}
		
		return null;
	}
}
