package com.retech.pfa.helper;


import com.retech.pfa.models.CountryStock;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountryStockExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

     // Method to verify if the uploaded file has excel format
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    ///////////////////////////////////////////////////////////

    public static List<CountryStock> excelToCountryStock(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<CountryStock> CountryStocks = new ArrayList<CountryStock>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                CountryStock countryStock = new CountryStock();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            countryStock.setCountryCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            countryStock.setCountryName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            countryStock.setPlantCode(currentCell.getStringCellValue());
                            break;
                        case 3:
                            countryStock.setPlantName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            countryStock.setCustomerCode(currentCell.getStringCellValue());
                            break;
                        case 5:
                            countryStock.setCustomerName(currentCell.getStringCellValue());
                            break;
                        case 6:
                            countryStock.setDivisionCode(currentCell.getStringCellValue());
                            break;
                        case 7:
                            countryStock.setMaterialCode(currentCell.getStringCellValue());
                            break;
                        case 8:
                            countryStock.setMaterialName(currentCell.getStringCellValue());
                            break;
                        case 9:
                            countryStock.setStoreCode(currentCell.getStringCellValue());
                            break;
                        case 10:
                            countryStock.setStoreName(currentCell.getStringCellValue());
                            break;
                        case 11:
                            countryStock.setTransitQty((long) currentCell.getNumericCellValue());
                            break;
                        case 12:
                            countryStock.setActualSQty((long) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                CountryStocks.add(countryStock);
            }

            workbook.close();

            return CountryStocks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
