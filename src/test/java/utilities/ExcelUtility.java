package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        initializeWorkbook();
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            wb.close();
            fi.close();
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }
        int rowcount = sheet.getLastRowNum();
        closeWorkbook();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        initializeWorkbook();
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            closeWorkbook();
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }
        row = sheet.getRow(rownum);
        if (row == null) {
            closeWorkbook();
            throw new IllegalArgumentException("Row not found: " + rownum);
        }
        int cellcount = row.getLastCellNum();
        closeWorkbook();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        initializeWorkbook();
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            closeWorkbook();
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }
        row = sheet.getRow(rownum);
        if (row == null) {
            closeWorkbook();
            throw new IllegalArgumentException("Row not found: " + rownum);
        }
        cell = row.getCell(colnum);
        String data;

        try {
            data = cell != null ? cell.toString() : "";
        } catch (Exception e) {
            data = "";
        }
        closeWorkbook();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        initializeWorkbook();
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            closeWorkbook();
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }
        row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum); // Create row if it doesn't exist
        }
        cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum); // Create cell if it doesn't exist
        }
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);
        closeWorkbook();
    }

    private void initializeWorkbook() throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
    }

    private void closeWorkbook() throws IOException {
        if (wb != null) {
            wb.close();
        }
        if (fi != null) {
            fi.close();
        }
        if (fo != null) {
            fo.close();
        }
    }
}

