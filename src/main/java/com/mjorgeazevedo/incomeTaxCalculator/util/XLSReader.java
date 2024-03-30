package com.mjorgeazevedo.incomeTaxCalculator.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mjorgeazevedo.incomeTaxCalculator.entity.Category;
import com.mjorgeazevedo.incomeTaxCalculator.entity.Operation;
import com.mjorgeazevedo.incomeTaxCalculator.entity.StatusInvestTransaction;
import com.mjorgeazevedo.incomeTaxCalculator.entity.Transaction;

public class XLSReader {
	public static List<Transaction> getTransactionsFromFile(String fileAbsolutePath) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		FileInputStream file = null;
		XSSFWorkbook wb = null;
		
		try {
			file = new FileInputStream(fileAbsolutePath);

			// Create Workbook instance holding reference to .xlsx file
			wb = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet ws = wb.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = ws.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() > 1) {
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
	
					Transaction transaction = new StatusInvestTransaction();
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
		
						switch (cell.getColumnIndex()) {
						case 0:
							transaction.setDate(string2date(cell.getStringCellValue()));
							break;
						case 1:
							transaction.setCategory(Category.getCategory(cell.getStringCellValue()));
							break;
						case 2:
							transaction.setAsset(AssetFactory.createAsset(cell.getStringCellValue()));
							break;
						case 3:
							transaction.setOperation(Operation.getOperation(cell.getStringCellValue()));
							break;
						case 4:
							transaction.setQuantity(string2numeric(cell.getStringCellValue()));
							break;
						case 5:
							transaction.setUnitPrice(string2numeric(cell.getStringCellValue()));
							break;
						case 6:
							transaction.setStockBroker(cell.getStringCellValue());
							break;
						case 7:
							transaction.setBrokerage(string2numeric(cell.getStringCellValue()));
							break;
						case 8:
							transaction.setFees(string2numeric(cell.getStringCellValue()));
							break;
						case 9:
							transaction.setTaxes(string2numeric(cell.getStringCellValue()));
							break;
						case 10:
							transaction.setIncomeTaxes(string2numeric(cell.getStringCellValue()));
							break;
						}
					}
					
					transactionList.add(transaction);
					//System.out.println(transaction);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {}
			}
			
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {}
			}
			
		}
		
		return transactionList;
	}
	
	private static double string2numeric(String value) {
		return Double.valueOf(value.replace(".", "").replace(",", "."));
	}
	
	private static LocalDate string2date(String value) {
		int day = Integer.valueOf(value.substring(0, 2));
		int month = Integer.valueOf(value.substring(3, 5));
		int year = Integer.valueOf(value.substring(6, value.length()));
		
		return LocalDate.of(year, month, day);
	}
}
