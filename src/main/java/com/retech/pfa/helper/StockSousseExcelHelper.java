package com.retech.pfa.helper;

import com.retech.pfa.models.StockSousse;
import com.retech.pfa.models.StockTunis;
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

public class StockSousseExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // Method to verify if the uploaded file has excel format
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    ///////////////////////////////////////////////////////////

    public static List<StockSousse> exceltoStockSousse(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<StockSousse> StockSousseData = new ArrayList<StockSousse>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber== 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                StockSousse stockSousse = new StockSousse();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            stockSousse.setCustomerCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            stockSousse.setSerialNo1(currentCell.getStringCellValue());
                            break;

                        case 2:
                            stockSousse.setSerialNo2(currentCell.getStringCellValue());
                            break;
                        case 3:
                            stockSousse.setMaterialCode(currentCell.getStringCellValue());
                            break;
                        case 4:
                            stockSousse.setMaterialDesc(currentCell.getStringCellValue());
                            break;
                        case 5:
                            stockSousse.setQuantity((long) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            /*if(currentCell.getStringCellValue() == null){
                                stockTunis.setEmployeeName("not found");
                                System.out.println("bla bla");
                            } else{
                                stockTunis.setEmployeeName(currentCell.getStringCellValue());
                                }*/
                            stockSousse.setEmployeeName(currentCell.getStringCellValue());
                            break;
                        case 7:
                            stockSousse.setPartnerName(currentCell.getStringCellValue());
                            break;
                        case 8:
                            stockSousse.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 9:
                            stockSousse.setStoreLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            stockSousse.setBinCode(currentCell.getStringCellValue());
                            break;
                        case 11:
                            stockSousse.setPrice( (float)currentCell.getNumericCellValue());
                            break;
                        case 12:
                            stockSousse.setGRNDate(currentCell.getDateCellValue());

                            break;
                        case 13:
                            stockSousse.setWarrantyStatus(currentCell.getStringCellValue());

                            break;
                        case 14:
                            stockSousse.setClassification(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                StockSousseData.add(stockSousse);
            }

            workbook.close();

            return StockSousseData;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
