package com.retech.pfa.helper;

import com.retech.pfa.models.Bom;
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

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Tunisia";

    // Method to verify if the uploaded file has excel format
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Bom> excelToBom(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Bom> boms = new ArrayList<Bom>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Bom bom = new Bom();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            bom.setCode((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            bom.setSAP_Code((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            bom.setBrand(currentCell.getStringCellValue());
                            break;
                        case 3:
                            bom.setModel(currentCell.getStringCellValue());
                            break;
                        case 4:
                            bom.setColor(currentCell.getStringCellValue());
                            break;
                        case 5:
                            bom.setMaterialdescriptionChinese(currentCell.getStringCellValue());
                            break;
                        case 6:
                            bom.setMaterialdescriptionEnglish(currentCell.getStringCellValue());
                            break;
                        case 7:
                            bom.setBOMlevel((long) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            bom.setComponentdescriptionChinese(currentCell.getStringCellValue());
                            break;
                        case 9:
                            bom.setComponentdescriptionEnglish(currentCell.getStringCellValue());
                            break;
                        case 10:
                            bom.setUnitQuantity((long) currentCell.getNumericCellValue());
                            break;
                        case 11:
                            bom.setBOMCategory(currentCell.getStringCellValue());
                            break;
                        case 12:
                            bom.setProcurementtype(currentCell.getStringCellValue());
                            break;
                        case 13:
                            bom.setMaterialGroupCode((long) currentCell.getNumericCellValue());
                            break;
                        case 14:
                            bom.setMaterialGroupChinese(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                boms.add(bom);
            }

            workbook.close();

            return boms;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
