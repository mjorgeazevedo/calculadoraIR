package com.mjorgeazevedo.incomeTaxCalculator.entity;

public class AssetSummary {
	private Asset asset;
	private double initialQuantity;
	private double finalQuantity;
	private double initialTotalValue;
	private double finalTotalValue;

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
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
		return "AssetSummary [asset=" + asset + ", initialQuantity=" + initialQuantity + ", finalQuantity="
				+ finalQuantity + ", initialTotalValue=" + initialTotalValue + ", finalTotalValue=" + finalTotalValue
				+ "]";
	}
}
