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
    private Double DoanhThu;

    public DoanhThuView() {
    }

    public DoanhThuView(String thang, int soLuong, Double DoanhThu) {
        this.thang = thang;
        this.soLuong = soLuong;
        this.DoanhThu = DoanhThu;
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

    public Double getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(Double DoanhThu) {
        this.DoanhThu = DoanhThu;
    }

   
}
