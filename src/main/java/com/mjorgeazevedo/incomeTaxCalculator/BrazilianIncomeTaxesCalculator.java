package com.mjorgeazevedo.incomeTaxCalculator;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mjorgeazevedo.incomeTaxCalculator.entity.AssetSummary;
import com.mjorgeazevedo.incomeTaxCalculator.entity.Category;
import com.mjorgeazevedo.incomeTaxCalculator.entity.Operation;
import com.mjorgeazevedo.incomeTaxCalculator.entity.Transaction;
import com.mjorgeazevedo.incomeTaxCalculator.util.XLSReader;

public class BrazilianIncomeTaxesCalculator {

	public static void main(String[] args) {
		try {
			processFile(Integer.parseInt(args[0]), args[1]);
			
			System.exit(0);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void processFile(int year, String file) {
		List<Transaction> transactions = XLSReader.getTransactionsFromFile(file);
		Collections.sort(transactions, (a,b)->a.getDate().compareTo(b.getDate()));
		
		Map<String, AssetSummary> result = new HashMap<String, AssetSummary>();
		
		for (Transaction transaction : transactions) {
			if (transaction.getDate().getYear() <= year) {
				if (Category.STOCK.equals(transaction.getCategory()) 
						|| Category.REAL_STATE_FUND.equals(transaction.getCategory()) 
						|| Category.AGRICULTURAL_FUND.equals(transaction.getCategory()) ) {
					
					//System.out.println(transaction);
					
					AssetSummary valuesOfYear;
					
					if (result.containsKey(transaction.getAsset().getId())) {
						valuesOfYear = result.get(transaction.getAsset().getId());
					} else {
						valuesOfYear = new AssetSummary();
						valuesOfYear.setAsset(transaction.getAsset());
					}
					
					int signal = Operation.PURCHASE.equals(transaction.getOperation()) ? 1 : -1;
					
					if (transaction.getDate().getYear() < year) {
						valuesOfYear.setInitialQuantity(valuesOfYear.getInitialQuantity() + (transaction.getQuantity() * signal));
						valuesOfYear.setFinalQuantity(valuesOfYear.getFinalQuantity() + (transaction.getQuantity() * signal));
						valuesOfYear.setInitialTotalValue(valuesOfYear.getInitialTotalValue() + (transaction.getAmount() * signal));
						valuesOfYear.setFinalTotalValue(valuesOfYear.getFinalTotalValue() + (transaction.getAmount() * signal));
					} else {
						valuesOfYear.setFinalQuantity(valuesOfYear.getFinalQuantity() + (transaction.getQuantity() * signal));
						valuesOfYear.setFinalTotalValue(valuesOfYear.getFinalTotalValue() + (transaction.getAmount() * signal));
					}
					
					result.put(transaction.getAsset().getId(), valuesOfYear);
				}
			}	
		}
		
		System.out.println("TICKER;NOME;CNPJ;31/12/" + (year-1) + ";31/12/" + year);
		
		for (String id : result.keySet()) {
			AssetSummary valuesOfYear = result.get(id);
			
			if (valuesOfYear.getInitialQuantity() > 0 || valuesOfYear.getFinalQuantity() > 0) {
				//System.out.println(valuesOfYear);
				
				DecimalFormat df = new DecimalFormat("#,##0.00");
				
				System.out.println(valuesOfYear.getAsset().getId() +
						";" + valuesOfYear.getAsset().getName() + 
						";" + valuesOfYear.getAsset().getCnpj() + 
						";" + df.format(valuesOfYear.getInitialTotalValue()) +
						";" + df.format(valuesOfYear.getFinalTotalValue() < 0 || valuesOfYear.getFinalQuantity() <= 0 ? 0 : valuesOfYear.getFinalTotalValue())
						);
			}	
		}
	}
}
          