package com.mjorgeazevedo.incomeTaxCalculator;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mjorgeazevedo.incomeTaxCalculator.entity.Asset;
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
		
		Map<String, Asset> result = new HashMap<String, Asset>();
		
		for (Transaction transaction : transactions) {
			if (transaction.getDate().getYear() <= year) {
				if (Category.STOCK.equals(transaction.getCategory()) 
						|| Category.REAL_STATE_FUND.equals(transaction.getCategory()) 
						|| Category.AGRICULTURAL_FUND.equals(transaction.getCategory()) ) {
					
					//System.out.println(transaction);
					
					Asset valuesOfYear;
					
					if (result.containsKey(transaction.getId())) {
						valuesOfYear = result.get(transaction.getId());
					} else {
						valuesOfYear = new Asset();
						valuesOfYear.setId(transaction.getId());
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
					
					result.put(transaction.getId(), valuesOfYear);
				}
			}	
		}
		
		for (String id : result.keySet()) {
			Asset valuesOfYear = result.get(id);
			
			if (valuesOfYear.getInitialQuantity() > 0 || valuesOfYear.getFinalQuantity() > 0) {
				//System.out.println(valuesOfYear);
				
				System.out.println(valuesOfYear.getId() + 
						";" + NumberFormat.getCurrencyInstance().format(valuesOfYear.getInitialTotalValue()) +
						";" + NumberFormat.getCurrencyInstance().format(valuesOfYear.getFinalTotalValue() < 0 || valuesOfYear.getFinalQuantity() <= 0 ? 0 : valuesOfYear.getFinalTotalValue())
						);
			}	
		}
	}
}
          