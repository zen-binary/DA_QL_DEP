/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KHACHHANG")
public class KhachHang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;
    
    @Column(name = "Sdt")
    private String sdt;
    
    @Column(name = "DiemTichLy")
    private String diemTichLy;
    
    @Column(name = "NgayTao")
    private Date ngayTao;
    
    @Column(name = "NgaySua")
    private Date ngaySua;
    
    @Column(name = "TinhTrang")
    private int tinhTrang;
    
    @OneToMany(mappedBy = "khachHang",fetch = FetchType.LAZY)
    private List<HoaDon> lstHoaDon;

    public KhachHang() {
    }

    public KhachHang(int id, String ma, String ten, String sdt, String diemTichLy, Date ngayTao, Date ngaySua, int tinhTrang, List<HoaDon> lstHoaDon) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diemTichLy = diemTichLy;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tinhTrang = tinhTrang;
        this.lstHoaDon = lstHoaDon;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiemTichLy() {
        return diemTichLy;
    }

    public void setDiemTichLy(String diemTichLy) {
        this.diemTichLy = diemTichLy;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public List<HoaDon> getLstHoaDon() {
        return lstHoaDon;
    }

    public void setLstHoaDon(List<HoaDon> lstHoaDon) {
        this.lstHoaDon = lstHoaDon;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
