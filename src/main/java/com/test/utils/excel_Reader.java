package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class excel_Reader {
	private Workbook workbook;

//constructor load the excel file
	public excel_Reader() {
		try {
			FileInputStream fis= new FileInputStream("Testdata/testdata.xlsx");
			workbook=new XSSFWorkbook(fis);
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load excel file!!");
		}
	}
		
		// fetch test data by testcase name
		
		public Map<String, String> getTestData(String sheetName, String testcasename){
			Map<String, String> dataMap = new HashMap<>();

	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            throw new RuntimeException("Sheet " + sheetName + " not found!");
	        }
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            throw new RuntimeException("Header row missing in sheet " + sheetName);
	        }

	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (row == null) continue;

	            Cell testcaseCell = row.getCell(0); // First column: Testcase
	            if (testcaseCell != null && testcaseCell.getStringCellValue().equalsIgnoreCase(testcasename)) {
	                for (int j = 1; j < row.getLastCellNum(); j++) {
	                    String key = headerRow.getCell(j).getStringCellValue();
	                    String value = row.getCell(j).getStringCellValue();
	                    dataMap.put(key, value);
	                }
	                break;
	            }
	        }
	        headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            throw new RuntimeException("Header row missing in sheet " + sheetName);
	        }
	        return dataMap;
		}
}

