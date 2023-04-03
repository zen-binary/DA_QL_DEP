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
    private String soLuong;
    private String DoanhThu;

    public DoanhThuView() {
    }

    public DoanhThuView(String thang, String soLuong, String DoanhThu) {
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

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(String DoanhThu) {
        this.DoanhThu = DoanhThu;
    }
    
    
}
