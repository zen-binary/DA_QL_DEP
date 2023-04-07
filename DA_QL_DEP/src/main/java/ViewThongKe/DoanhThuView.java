/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewThongKe;


/**
 *
 * @author Admin
 */
public class DoanhThuView {
    private String thang;
    private int soLuong;
    private Double khoanChi;
    private Double DoanhThu;
    private Double tienLoi;

    public DoanhThuView() {
    }

    public DoanhThuView(String thang, int soLuong, Double khoanChi, Double DoanhThu, Double tienLoi) {
        this.thang = thang;
        this.soLuong = soLuong;
        this.khoanChi = khoanChi;
        this.DoanhThu = DoanhThu;
        this.tienLoi = tienLoi;
    }


    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getKhoanChi() {
        return khoanChi;
    }

    public void setKhoanChi(Double khoanChi) {
        this.khoanChi = khoanChi;
    }

    public Double getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(Double DoanhThu) {
        this.DoanhThu = DoanhThu;
    }

    public Double getTienLoi() {
        return tienLoi;
    }

    public void setTienLoi(Double tienLoi) {
        this.tienLoi = tienLoi;
    }

   
    
}
