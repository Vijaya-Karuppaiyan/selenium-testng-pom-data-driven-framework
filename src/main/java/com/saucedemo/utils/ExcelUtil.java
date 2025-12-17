package com.saucedemo.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	// Read Excel sheet from src/test/resources/testdata/LoginData.xlsx
    @SuppressWarnings("resource")
	public static Object[][] getTestData(String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (InputStream is = ExcelUtil.class.getClassLoader()
                .getResourceAsStream("testdata/LoginData.xlsx")) {

            if (is == null) {
                throw new RuntimeException("LoginData.xlsx not found in classpath/testdata");
            }

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in LoginData.xlsx");
            }
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                System.out.println("Found sheet: " + wb.getSheetName(i));
            }
            // assume first row is header
            int rows = sheet.getLastRowNum();
            Row header = sheet.getRow(0);
            int cols = header.getLastCellNum();

            for (int r = 1; r <= rows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Object[] rowData = new Object[cols];
                for (int c = 0; c < cols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    // Always convert to string for simplicity
                    switch (cell.getCellType()) {
                    case STRING:
                        rowData[c] = cell.getStringCellValue();
                        break;

                    case NUMERIC:
                        // convert numeric to string
                        rowData[c] = String.valueOf((long) cell.getNumericCellValue());
                        break;

                    case BOOLEAN:
                        rowData[c] = String.valueOf(cell.getBooleanCellValue());
                        break;

                    case BLANK:
                        rowData[c] = "";
                        break;

                    default:
                        rowData[c] = cell.toString();
                        break;
                }

                }
                data.add(rowData);
            }
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage(), e);
        }

        // convert list to Object[][]
        Object[][] arr = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) arr[i] = data.get(i);
        return arr;
    }

}
