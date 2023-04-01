/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.ChiTietDep;
import Models.HoaDon;
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
public class ExportHD {
    public static final int COLUMN_MA = 0;
    public static final int COLUMN_KHACHHANG = 1;
    public static final int COLUMN_TONGTIEN =    2;
    public static final int COLUMN_THANHTIEN = 3;
    public static final int COLUMN_KHUYENMAI = 4;
    public static final int COLUMN_NGAYTHANHTOAN = 5;
    public static final int COLUMN_NGAYTAO = 6;
    public static final int COLUMN_NGAYSUA = 7;
    public static final int COLUMN_MOTA = 8;
    public static final int COLUMN_TINHTRANG = 9;

    public static void writeExcel(List<HoaDon> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Hoá Đơn");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (HoaDon x : list) {
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
        cell.setCellValue("Mã");

        cell = row.createCell(COLUMN_KHACHHANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Khách Hàng");
        
        cell = row.createCell(COLUMN_TONGTIEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tổng Tiền");

        cell = row.createCell(COLUMN_THANHTIEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thành Tiền");
        
        cell = row.createCell(COLUMN_KHUYENMAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Khuyến Mãi");

        cell = row.createCell(COLUMN_NGAYTHANHTOAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Thanh Toán");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngay Tạo");

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Sửa");

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô Tả");

        cell = row.createCell(COLUMN_TINHTRANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình Trạng");

    }

    private static void writeBook(HoaDon hd, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(hd.getMa());

        cell = row.createCell(COLUMN_KHACHHANG);
        cell.setCellValue(hd.getKhachHang() == null?"Khách Lẻ":hd.getKhachHang().getTen());
        
        cell = row.createCell(COLUMN_TONGTIEN);
        cell.setCellValue(hd.getTongTien().doubleValue());

        cell = row.createCell(COLUMN_THANHTIEN);
        cell.setCellValue(hd.getThanhTien().doubleValue());
        cell = row.createCell(COLUMN_KHUYENMAI);
        cell.setCellValue(hd.getKhuyenMai()==null?"null":hd.getKhuyenMai().getTen());

        cell = row.createCell(COLUMN_NGAYTHANHTOAN);
        cell.setCellValue(sdf.format(hd.getNgayThanhToan()));

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(sdf.format(hd.getNgayTao()));

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellValue(sdf.format(hd.getNgaySua()));

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellValue(hd.getMoTa());

        cell = row.createCell(COLUMN_TINHTRANG);
         if (hd.getTinhTrang() == 0) {
            cell.setCellValue("Đã Thanh Toán");
        } else if (hd.getTinhTrang() == 1) {
            cell.setCellValue("Chờ Thanh Toán");
        } else {
            cell.setCellValue("Đã Huỷ");
        }

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
