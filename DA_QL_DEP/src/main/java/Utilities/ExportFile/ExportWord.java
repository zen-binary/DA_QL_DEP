/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities.ExportFile;

import Models.HoaDon;
import Models.HoaDonChiTiet;
import Services.IHoaDonChiTietService;
import Services.IHoaDonService;
import Services.implement.HoaDonChiTietService;
import Services.implement.HoaDonService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Admin
 */
public class ExportWord {

    //https://stackjava.com/library/doc-ghi-file-ms-word-bang-java-voi-apache-poi.html
    public static void main(String[] args) throws IOException {
        // Create Blank document
        XWPFDocument document = new XWPFDocument();
        // Create new Paragraph
        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph1.createRun();
        run.setText("Paragraph 1: stackjava.com");

        XWPFParagraph paragraph2 = document.createParagraph();
        run = paragraph2.createRun();
        run.setText("Paragraph 2: demo read/write file MS-Word");

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("./ExportHoaDon/demo-aspache-apoi-word.docx"));
        document.write(out);
        out.close();
        document.close();
        System.out.println("successully");
    }

    public static void ExportWord(HoaDon hd, Double giaGiam, Double KhachDua, Double tienThua) {
        IHoaDonService hdService = new HoaDonService();
        IHoaDonChiTietService hdctService = new HoaDonChiTietService();

        String maHd = hd.getMa();
        Double tongTien = 0.0;
        for (HoaDonChiTiet hdct : hdctService.getAllByMa(maHd)) {
            tongTien = tongTien + (hdct.getSoLuong() * Double.valueOf(String.valueOf(hdct.getDonGia())));
        }

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraphTitle = document.createParagraph();
        paragraphTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runName = paragraphTitle.createRun();
        runName.setText("DÉP SHOP");
        runName.setBold(true);
        runName.setFontSize(36);
        runName.setFontFamily("Tahoma");
        runName.addBreak();

        XWPFRun runDiaChi = paragraphTitle.createRun();
        runDiaChi.setText("ÐC: Tòa nhà FPT Polytechnic, Phố Trịnh Văn Bô, Nam Từ Liêm, Hà Nội");
        runDiaChi.setFontSize(12);
        runDiaChi.setFontFamily("Tahoma");
        runDiaChi.addBreak();

        XWPFRun runPhone = paragraphTitle.createRun();
        runPhone.setText("SÐT: 09123456789");
        runPhone.setFontSize(12);
        runPhone.setFontFamily("Tahoma");
        runPhone.addBreak();

        XWPFRun runKe1 = paragraphTitle.createRun();
        runKe1.setText("-----------------------");
        runKe1.setBold(true);
        runKe1.setFontSize(12);
        runKe1.setFontFamily("Tahoma");
        runKe1.addBreak();
        runKe1.addBreak();

        XWPFRun runTile = paragraphTitle.createRun();
        runTile.setText("HÓA ĐƠN THANH TOÁN");
        runTile.setBold(true);
        runTile.setFontSize(20);
        runTile.setFontFamily("Tahoma");
        runTile.addBreak();

        XWPFRun runMaHD = paragraphTitle.createRun();
        runMaHD.setText("Số: HD001");
        runMaHD.setBold(true);
        runMaHD.setFontSize(14);
        runMaHD.setFontFamily("Tahoma");
        runMaHD.addBreak();

        //left
        XWPFParagraph paragraphLeft = document.createParagraph();
        paragraphLeft.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun runNgayMua = paragraphLeft.createRun();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        Date d = new Date();
        runNgayMua.setText("Ngày mua: " + sdf.format(d));
        runNgayMua.setFontSize(14);
        runNgayMua.setFontFamily("Tahoma");
        runNgayMua.addBreak();

        String khachHang = hd.getKhachHang() == null ? "Khách lẻ" : hd.getKhachHang().getTen();
        XWPFRun runKhachHang = paragraphLeft.createRun();
        runKhachHang.setText("Khách Hàng: " + khachHang);
        runKhachHang.setFontSize(14);
        runKhachHang.setFontFamily("Tahoma");
        runKhachHang.addTab();
        runKhachHang.addTab();
        runKhachHang.addTab();
        runKhachHang.addTab();

        String thuNgan = hd.getNguoiDung() == null ? "Nhân Viên Ảo" : hd.getNguoiDung().getTen();
        XWPFRun runThuNgan = paragraphLeft.createRun();
        runThuNgan.setText("Thu Ngân: " + thuNgan);
        runThuNgan.setFontSize(14);
        runThuNgan.setFontFamily("Tahoma");
        runThuNgan.addBreak();

        XWPFRun runke2 = paragraphLeft.createRun();
        runke2.setText("_____________________________________________________________");
        runke2.setFontSize(14);
        runke2.setFontFamily("Tahoma");
        runke2.addBreak();

        XWPFRun runColum = paragraphLeft.createRun();
        runColum.setFontSize(20);
        runColum.setBold(true);
        runColum.setFontFamily("Tahoma");
        runColum.setText("Tên Sp");
        runColum.addTab();
        runColum.addTab();
        runColum.addTab();
        runColum.setText("SL");
        runColum.addTab();
        runColum.addTab();
        runColum.setText("Đ.Giá");
        runColum.addTab();
        runColum.addTab();
        runColum.addTab();
        runColum.setText("T.Tiền");
        runColum.addBreak();

        XWPFRun runke3 = paragraphLeft.createRun();
        runke3.setText("_____________________________________________________________");
        runke3.setFontSize(14);
        runke3.setFontFamily("Tahoma");
        runke3.addBreak();

        XWPFRun SP = paragraphLeft.createRun();
        SP.setFontSize(14);
        SP.setFontFamily("Tahoma");

        String tenSp = "";
        String soLuongSp = "";
        String giaBan = "";
        Double thanhTien = 0.0;
        for (HoaDonChiTiet hdct : hdctService.getAllByMa(maHd)) {
            tenSp = hdct.getChiTietDep().getDep().getTen();
            SP.setText(tenSp);
            SP.addTab();
            SP.addTab();
            SP.addTab();
            soLuongSp = String.valueOf(hdct.getSoLuong());
            SP.setText(soLuongSp);
            SP.addTab();
            SP.addTab();
            giaBan = String.valueOf(hdct.getDonGia());
            SP.setText(giaBan);
            SP.addTab();
            SP.addTab();
            SP.addTab();
            thanhTien = Double.valueOf(giaBan) * Double.valueOf(soLuongSp);
            SP.setText(String.valueOf(thanhTien));
            SP.addBreak();

        }

        XWPFRun runke4 = paragraphLeft.createRun();
        runke4.setText("_____________________________________________________________");
        runke4.setFontSize(14);
        runke4.setFontFamily("Tahoma");
        runke4.addBreak();

        XWPFRun runTongTien = paragraphLeft.createRun();
        runTongTien.setText("Tổng Tiền:");
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.addTab();
        runTongTien.setText(String.valueOf(tongTien));
        runTongTien.setFontSize(14);
        runTongTien.setFontFamily("Tahoma");
        runTongTien.addBreak();

        XWPFRun runGiamGia = paragraphLeft.createRun();
        runGiamGia.setText("Giảm Giá:");
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.addTab();
        runGiamGia.setText(String.valueOf(giaGiam));
        runGiamGia.setFontSize(14);
        runGiamGia.setFontFamily("Tahoma");
        runGiamGia.addBreak();

        XWPFRun runThanhTien = paragraphLeft.createRun();
        runThanhTien.setText("Tổng Thanh Toán:");
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.addTab();
        runThanhTien.setText(String.valueOf(tongTien - giaGiam));
        runThanhTien.setFontSize(14);
        runThanhTien.setFontFamily("Tahoma");
        runThanhTien.addBreak();

        XWPFRun runKhachDua = paragraphLeft.createRun();
        runKhachDua.setText("Tiền Khách Đưa:");
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.addTab();
        runKhachDua.setText(String.valueOf(KhachDua));
        runKhachDua.setFontSize(14);
        runKhachDua.setFontFamily("Tahoma");
        runKhachDua.addBreak();

        XWPFRun runTienThua = paragraphLeft.createRun();
        runTienThua.setText("Tiền Thừa:");
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.addTab();
        runTienThua.setText(String.valueOf(tienThua));
        runTienThua.setFontSize(14);
        runTienThua.setFontFamily("Tahoma");
        runTienThua.addBreak();

        XWPFRun runKe5 = paragraphLeft.createRun();       
        runKe5.setText("_____________________________________________________________");
        runKe5.setBold(true);
        runKe5.setFontSize(12);
        runKe5.setFontFamily("Tahoma");
        runKe5.addBreak();
        runKe5.addBreak();

        XWPFRun runThanks = paragraphLeft.createRun();
        runThanks.setText("Xin cảm ơn, hẹn gặp lại quý khách!");
        runThanks.setBold(true);
        runThanks.setFontSize(20);
        runThanks.setFontFamily("Tahoma");
        runThanks.addBreak();

        try {
            FileOutputStream out = new FileOutputStream(new File("./ExportHoaDon/" + maHd + ".docx"));
            document.write(out);
            out.close();
            document.close();
            System.out.println("thành công");
        } catch (FileNotFoundException ex) {
            System.out.println("Trùng Tên File");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Nhân 2");
            ex.printStackTrace();
        }

    }

//    public static void main(String[] args) {
////        ExportWord();
//
//    }
}
