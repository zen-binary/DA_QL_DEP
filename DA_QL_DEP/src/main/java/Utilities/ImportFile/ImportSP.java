/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ImportFile;

import Models.ChiTietDep;
import Models.Dep;
import Services.IChatLieuService;
import Services.IChiTietDepService;
import Services.IDepService;
import Services.ILoaiDepService;
import Services.IMauSacService;
import Services.INsxService;
import Services.ISizeService;
import Services.implement.ChatLieuService;
import Services.implement.ChiTietDepService;
import Services.implement.DepService;
import Services.implement.LoaiDepService;
import Services.implement.MauSacService;
import Services.implement.NsxService;
import Services.implement.SizeService;
import java.awt.geom.Ellipse2D;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ImportSP {

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

    private static IChiTietDepService ctdService = new ChiTietDepService();
    private static IDepService depService = new DepService();
    private static IChatLieuService clSerivce = new ChatLieuService();
    private static IMauSacService msService = new MauSacService();
    private static INsxService nsxService = new NsxService();
    private static ILoaiDepService loaiSerivce = new LoaiDepService();
    private static ISizeService sizeService = new SizeService();

    public static List<ChiTietDep> readExcel(String excelFilePath) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<ChiTietDep> list = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            ChiTietDep ctd = new ChiTietDep();
            Dep d = null;
            Dep dep = new Dep();
            String maDep = "";
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_MA:
                        maDep = String.valueOf(cellValue);

                        break;
                    case COLUMN_DEP:
                        d = depService.getObj(maDep);
                        if (d == null) {
                            
                            dep.setMa(maDep);
                            dep.setTen(String.valueOf(cellValue));
                            
                        } else {
                            ctd.setDep(depService.getObj(maDep));
                        }
//                        ctd.setDep(depService.getObjByTen(String.valueOf(cellValue)));
                        break;
                    case COLUMN_SOLUONG:

                        ctd.setSoLuong((int) (double) cellValue);
                        break;
                    case COLUMN_GIABAN:
                        ctd.setGiaBan(BigDecimal.valueOf((double) cellValue));
                        break;
                    case COLUMN_GIANHAP:
                        ctd.setGiaNhap(BigDecimal.valueOf((double) cellValue));
                        break;
                    case COLUMN_LOAI:
                        ctd.setLoaiDep(loaiSerivce.getObjByTen(String.valueOf(cellValue)));
                        break;
                    case COLUMN_SIZE:
                        float size = 0;
                        try {
                            size = Float.valueOf(cellValue.toString());
//                            System.out.println("size3:" + size);
                        } catch (Exception e) {
                        }
                        ctd.setSize(sizeService.getObjBySize(size));
                        break;

                    case COLUMN_MAUSAC:
                        ctd.setMauSac(msService.getObjByMauSac(String.valueOf(cellValue)));
                        break;
                    case COLUMN_CHATLIEU:
                        ctd.setChatLieu(clSerivce.getObjByTen(String.valueOf(cellValue)));
                        break;
                    case COLUMN_NSX:
                        ctd.setNsx(nsxService.getObjByTen(String.valueOf(cellValue)));
                        break;
                    case COLUMN_MOTA:
                        ctd.setMoTa((String) getCellValue(cell));
                        break;
                    case COLUMN_NGAYTAO:
                        ctd.setNgayTao(new Date());
                        break;
                    case COLUMN_NGAYSUA:
                        ctd.setNgaySua(new Date());
                        break;
                    case COLUMN_TINHTRANG:
                        if (cellValue.equals("Ðang Kinh Doanh")) {
                            ctd.setTinhTrang(0);
                        } else if (cellValue.equals("Ngừng Kinh Doanh")) {
                            ctd.setTinhTrang(1);
                        } else {
                            ctd.setTinhTrang(-1);
                        }
                        break;
                    case COLUMN_HINHANH:
                         d = depService.getObj(maDep);
                        if (d == null){
                            dep.setNgayTao(new Date());
                            dep.setNgaySua(new Date());
                            dep.setTinhTrang(0);
                            dep.setHinhAnh((String) getCellValue(cell));
                            depService.save(dep);
                            ctd.setDep(depService.getObj(dep.getMa()));
                        }
                        break;

                    default:
                        break;
                }

            }
            list.add(ctd);
        }

        workbook.close();
        inputStream.close();

        return list;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }
}
