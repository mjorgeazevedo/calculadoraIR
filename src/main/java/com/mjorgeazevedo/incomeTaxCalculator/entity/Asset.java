package com.mjorgeazevedo.incomeTaxCalculator.entity;

public class Asset {
	private String id;
	private String name;
	private String cnpj;
	
	public Asset(String id, String name, String cnpj) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	@Override
	public String toString() {
		return "Asset [id=" + id + ", name=" + name + ", cnpj=" + cnpj + "]";
	} 
}
