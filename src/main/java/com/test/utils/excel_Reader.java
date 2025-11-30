package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel_Reader {

    private Workbook workbook;

    // Constructor to load Excel file
    public excel_Reader() {
        try {
            String path = System.getProperty("user.dir")
                         + File.separator + "Testdata"
                         + File.separator + "testdata.xlsx";

            System.out.println("Excel Path: " + path);
            System.out.println("File exists: " + new File(path).exists());

            FileInputStream fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load excel file!!");
        }
    }

    // Fetch test data by testcase name
    public Map<String, String> getTestData(String sheetName, String testcasename) {

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

            Cell testcaseCell = row.getCell(0);

            if (testcaseCell != null &&
                testcaseCell.getStringCellValue().equalsIgnoreCase(testcasename)) {

                for (int j = 1; j < row.getLastCellNum(); j++) {

                    String key = headerRow.getCell(j).getStringCellValue().trim();
                    String value = (row.getCell(j) != null)
                                     ? row.getCell(j).getStringCellValue().trim()
                                     : "";

                    dataMap.put(key, value);
                }
                break;
            }
        }

        System.out.println("FINAL MAP: " + dataMap);
        return dataMap;
    }
}
