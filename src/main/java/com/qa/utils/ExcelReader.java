package com.qa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static void main(String[] args) {
        String filePath = "./src/test/resources/TestData/test_data_old.xlsx";

        try {
            Map<String, String> rowData = readRowData(filePath, 1);

            // Convert the map to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(rowData);

            // Print the JSON
            System.out.println(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> readRowData(String filePath, int rowNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming there is only one sheet

            Row row = sheet.getRow(rowNum - 1); // Adjusted to 0-based index

            if (row != null) {
                int cellCount = row.getLastCellNum();
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < cellCount; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getCellValueAsString(cell);
                    rowData.put("Column" + (i + 1), cellValue);
                }

                workbook.close();
                return rowData;
            }
        }
        return null;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}