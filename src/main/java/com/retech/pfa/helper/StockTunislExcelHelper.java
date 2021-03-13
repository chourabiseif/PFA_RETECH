package com.retech.pfa.helper;

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

public class StockTunislExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // Method to verify if the uploaded file has excel format
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    ///////////////////////////////////////////////////////////

    public static List<StockTunis> exceltoStockTunis(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<StockTunis> StockTunisData = new ArrayList<StockTunis>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber== 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                StockTunis stockTunis = new StockTunis();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            stockTunis.setCustomerCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            stockTunis.setSerialNo1(currentCell.getStringCellValue());
                            break;

                        case 2:
                            stockTunis.setSerialNo2(currentCell.getStringCellValue());
                            break;
                        case 3:
                            stockTunis.setMaterialCode(currentCell.getStringCellValue());
                            break;
                        case 4:
                            stockTunis.setMaterialDesc(currentCell.getStringCellValue());
                            break;
                        case 5:
                            stockTunis.setQuantity((long) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            /*if(currentCell.getStringCellValue() == null){
                                stockTunis.setEmployeeName("not found");
                                System.out.println("bla bla");
                            } else{
                                stockTunis.setEmployeeName(currentCell.getStringCellValue());
                                }*/
                            stockTunis.setEmployeeName(currentCell.getStringCellValue());
                            break;
                        case 7:
                            stockTunis.setPartnerName(currentCell.getStringCellValue());
                            break;
                        case 8:
                            stockTunis.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 9:
                            stockTunis.setStoreLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            stockTunis.setBinCode(currentCell.getStringCellValue());
                            break;
                        case 11:
                            stockTunis.setPrice( (float)currentCell.getNumericCellValue());
                            break;
                        case 12:
                            stockTunis.setGRNDate(currentCell.getDateCellValue());

                            break;
                        case 13:
                            stockTunis.setWarrantyStatus(currentCell.getStringCellValue());

                            break;
                        case 14:
                            stockTunis.setClassification(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                StockTunisData.add(stockTunis);
            }

            workbook.close();

            return StockTunisData;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
