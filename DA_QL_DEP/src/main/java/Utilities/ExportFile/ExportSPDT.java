/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.NguoiDung;
import Models.ChiTietDep;
import ViewThongKe.SanPhamView;
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
public class ExportSPDT {

    public static final int COLUMN_MA = 0;
    public static final int COLUMN_DEP = 1;
    public static final int COLUMN_NGAYTAO = 2;
    public static final int COLUMN_SOLUONG = 3;
    public static final int COLUMN_GIABAN = 4;
    public static final int COLUMN_SOLUONGBAN = 5;
    public static final int COLUMN_DOANHTHU = 6;


    public static void writeExcel(List<SanPhamView> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Sản Phẩm");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (SanPhamView x : list) {
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

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Sản Phẩm");

        cell = row.createCell(COLUMN_DEP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên Sản Phẩm");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Tạo");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng Tồn");

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Đơn Giá");

        cell = row.createCell(COLUMN_SOLUONGBAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng Bán");

        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Doanh Thu");

    }

    private static void writeBook(SanPhamView sp, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(sp.getMa());

        cell = row.createCell(COLUMN_DEP);
        cell.setCellValue(sp.getTen());

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(sdf.format(sp.getNgayTao()));

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(sp.getSlTon());

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellValue(sp.getDonGia());

        cell = row.createCell(COLUMN_SOLUONGBAN);
        cell.setCellValue(sp.getSlBan());

        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellValue(sp.getDoanhThu());


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
