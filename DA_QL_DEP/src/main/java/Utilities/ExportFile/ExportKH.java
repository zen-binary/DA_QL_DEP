/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.KhachHang;
import Models.NguoiDung;
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
 * @author Administrator
 */
public class ExportKH {

    public static final int COLUMN_MA = 0;
    public static final int COLUMN_TEN = 1;
    public static final int COLUMN_NGAYSINH = 2;
    public static final int COLUMN_GIOITINH = 3;
    public static final int COLUMN_SDT = 4;
    public static final int COLUMN_EMAIL = 5;
    public static final int COLUMN_DIACHI = 6;
    public static final int COLUMN_DIEMTICHLUY = 7;
    public static final int COLUMN_NGAYTAO = 8;
    public static final int COLUMN_NGAYSUA = 9;
    public static final int COLUMN_TINHTRANG = 10;

    public static void writeExcel(List<KhachHang> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Khách Hàng");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (KhachHang kh : list) {
            Row row = sheet.createRow(rowIndex);
            writeBook(kh, row);
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
        cell.setCellValue("Mã người dùng");

        cell = row.createCell(COLUMN_TEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên người dùng");

        cell = row.createCell(COLUMN_NGAYSINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(COLUMN_GIOITINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới Tính");

        cell = row.createCell(COLUMN_SDT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(COLUMN_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
        
        cell = row.createCell(COLUMN_DIACHI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa Chỉ");

        cell = row.createCell(COLUMN_DIEMTICHLUY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Điểm Tích Luỹ");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày tạo");

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sửa");

//        cell = row.createCell(COLUMN_TINHTRANG);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Tình Trạng");

    }

    private static void writeBook(KhachHang n, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(n.getMa());

        cell = row.createCell(COLUMN_TEN);
        cell.setCellValue(n.getTen());

        cell = row.createCell(COLUMN_NGAYSINH);
        cell.setCellValue(sdf.format(n.getNgaySinh()));

        cell = row.createCell(COLUMN_GIOITINH);
        if (n.getGioiTinh() == 0) {
            cell.setCellValue("Nam");
        } else {
            cell.setCellValue("Nữ");
        }

        cell = row.createCell(COLUMN_SDT);
        cell.setCellValue(n.getSdt());

        cell = row.createCell(COLUMN_EMAIL);
        cell.setCellValue(n.getEmail());
        
        cell = row.createCell(COLUMN_DIACHI);
        cell.setCellValue(n.getDiaChi());

        cell = row.createCell(COLUMN_DIEMTICHLUY);
        cell.setCellValue(n.getDiemTichLy());

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(sdf.format(n.getNgayTao()));

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellValue(sdf.format(n.getNgaySua()));

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
