package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataUtil {

    public static List<String> readSearchTexts(String fileName) {
        List<String> searchTexts = new ArrayList<>();
        String filePath = ("src/test/resources/").replace("/", File.separator) + fileName;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

            for (Row row : sheet) {
                Cell searchCell = row.getCell(0); // Assuming the search text is in the first column
                if (searchCell != null) {
                    searchTexts.add(getCellValueAsString(searchCell));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchTexts;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

}

