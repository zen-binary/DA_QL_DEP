/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.NguoiDung;
import ViewThongKe.DoanhThuView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ExportDT {

    public static final int COLUMN_THANG = 0;
    public static final int COLUMN_SOLUONG = 1;
//    public static final int COLUMN_KHOANCHI = 2;
    public static final int COLUMN_DOANHTHU = 2;
//    public static final int COLUMN_TIENLOI = 4;
    public static void writeExcel(List<DoanhThuView> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Doanh Thu");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (DoanhThuView x : list) {
            Row row = sheet.createRow(rowIndex);
            writeBook(x, row);
            rowIndex++;
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleHeader(sheet);

        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(COLUMN_THANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tháng");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng Bán Ra");
//        
//        cell = row.createCell(COLUMN_KHOANCHI);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Khoản Chi");

        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("DoanhThu");

//        cell = row.createCell(COLUMN_TIENLOI);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Tiền Lời");

        

    }

    private static void writeBook(DoanhThuView dt, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_THANG);
        cell.setCellValue(dt.getThang());

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(dt.getSoLuong());

//        cell = row.createCell(COLUMN_KHOANCHI);
//        cell.setCellValue(dt.getKhoanChi());
        
        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellValue(dt.getDoanhThu());
//
//        cell = row.createCell(COLUMN_TIENLOI);
//        cell.setCellValue(dt.getTienLoi());


    }

    // Create CellStyle for header
    private static CellStyle createStyleHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try ( OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
