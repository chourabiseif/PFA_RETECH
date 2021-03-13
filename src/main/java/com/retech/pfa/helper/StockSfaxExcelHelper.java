package com.retech.pfa.helper;

import com.retech.pfa.models.StockSfax;
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

public class StockSfaxExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // Method to verify if the uploaded file has excel format
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    ///////////////////////////////////////////////////////////

    public static List<StockSfax> exceltoStockSfax(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<StockSfax> StockSfaxData = new ArrayList<StockSfax>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber== 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                StockSfax stockSfax = new StockSfax();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            stockSfax.setCustomerCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            stockSfax.setSerialNo1(currentCell.getStringCellValue());
                            break;

                        case 2:
                            stockSfax.setSerialNo2(currentCell.getStringCellValue());
                            break;
                        case 3:
                            stockSfax.setMaterialCode(currentCell.getStringCellValue());
                            break;
                        case 4:
                            stockSfax.setMaterialDesc(currentCell.getStringCellValue());
                            break;
                        case 5:
                            stockSfax.setQuantity((long) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            /*if(currentCell.getStringCellValue() == null){
                                stockTunis.setEmployeeName("not found");
                                System.out.println("bla bla");
                            } else{
                                stockTunis.setEmployeeName(currentCell.getStringCellValue());
                                }*/
                            stockSfax.setEmployeeName(currentCell.getStringCellValue());
                            break;
                        case 7:
                            stockSfax.setPartnerName(currentCell.getStringCellValue());
                            break;
                        case 8:
                            stockSfax.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 9:
                            stockSfax.setStoreLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            stockSfax.setBinCode(currentCell.getStringCellValue());
                            break;
                        case 11:
                            stockSfax.setPrice( (float)currentCell.getNumericCellValue());
                            break;
                        case 12:
                            stockSfax.setGRNDate(currentCell.getDateCellValue());

                            break;
                        case 13:
                            stockSfax.setWarrantyStatus(currentCell.getStringCellValue());

                            break;
                        case 14:
                            stockSfax.setClassification(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                StockSfaxData.add(stockSfax);
            }

            workbook.close();

            return StockSfaxData;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
