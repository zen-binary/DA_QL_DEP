/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.NguoiDung;
import Models.ChiTietDep;
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
public class ExportSP {

    public static final int COLUMN_MA = 0;
    public static final int COLUMN_DEP = 1;
    public static final int COLUMN_SOLUONG = 2;
    public static final int COLUMN_GIABAN = 3;
    public static final int COLUMN_GIANHAP = 4;
    public static final int COLUMN_LOAI = 5;
    public static final int COLUMN_SIZE = 6;
    public static final int COLUMN_MAUSAC = 7;
    public static final int COLUMN_CHATLIEU = 8;
    public static final int COLUMN_NSX = 9;
    public static final int COLUMN_MOTA = 10;
    public static final int COLUMN_NGAYTAO = 11;
    public static final int COLUMN_NGAYSUA = 12;
    public static final int COLUMN_TINHTRANG = 13;
    public static final int COLUMN_HINHANH = 14;

    public static void writeExcel(List<ChiTietDep> list, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Sản Phẩm");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;

        for (ChiTietDep x : list) {
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

        cell = row.createCell(COLUMN_DEP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Dép");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng");

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá Bán");

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá Nhập");

        cell = row.createCell(COLUMN_LOAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Loại");

        cell = row.createCell(COLUMN_SIZE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Size");

        cell = row.createCell(COLUMN_MAUSAC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Màu Sắc");

        cell = row.createCell(COLUMN_CHATLIEU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chất Liệu");

        cell = row.createCell(COLUMN_NSX);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Nhà Sản Xuất");

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô Tả");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Tạo");

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Sửa");

        cell = row.createCell(COLUMN_TINHTRANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình Trạng");

        cell = row.createCell(COLUMN_HINHANH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình Ảnh");

    }

    private static void writeBook(ChiTietDep ctd, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(ctd.getDep().getMa());

        cell = row.createCell(COLUMN_DEP);
        cell.setCellValue(ctd.getDep().getTen());

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(ctd.getSoLuong());

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellValue(ctd.getGiaBan().doubleValue());

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellValue(ctd.getGiaNhap().doubleValue());

        cell = row.createCell(COLUMN_LOAI);
        cell.setCellValue(ctd.getLoaiDep().getTen());

        cell = row.createCell(COLUMN_SIZE);
        cell.setCellValue(ctd.getSize().getKichCo());

        cell = row.createCell(COLUMN_MAUSAC);
        cell.setCellValue(ctd.getMauSac().getMauSac());

        cell = row.createCell(COLUMN_CHATLIEU);
        cell.setCellValue(ctd.getChatLieu().getTen());

        cell = row.createCell(COLUMN_NSX);
        cell.setCellValue(ctd.getNsx().getTen());

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellValue(ctd.getMoTa());

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(sdf.format(ctd.getNgayTao()));

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellValue(sdf.format(ctd.getNgaySua()));

        cell = row.createCell(COLUMN_TINHTRANG);
        if (ctd.getTinhTrang() == 0) {
            cell.setCellValue("Ðang Kinh Doanh");
        } else if (ctd.getTinhTrang() == 1) {
            cell.setCellValue("Ngừng Kinh Doanh");
        } else {
            cell.setCellValue("Nulls");
        }
        cell = row.createCell(COLUMN_HINHANH);
        cell.setCellValue(ctd.getDep().getHinhAnh());

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
