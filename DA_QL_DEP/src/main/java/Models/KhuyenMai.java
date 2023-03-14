/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;
    
    @Column(name = "PhanTramGiam")
    private double phanTramGiam;
    
    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    
    @Column(name = "NgayKetThuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    
    @Column(name = "TinhTrang")
    private int tinhTrang;
    
    @Column(name = "MoTa")
    private String moTa;
    @OneToMany(mappedBy = "khuyenMai", fetch = FetchType.LAZY)
    private List<HoaDon> lstHoaDon;

    public KhuyenMai() {
    }

    public KhuyenMai(int id, String ma, String ten, double phanTramGiam, Date ngayBatDau, Date ngayKetThuc, int tinhTrang, String moTa, List<HoaDon> lstHoaDon) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tinhTrang = tinhTrang;
        this.moTa = moTa;
        this.lstHoaDon = lstHoaDon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<HoaDon> getLstHoaDon() {
        return lstHoaDon;
    }

    public void setLstHoaDon(List<HoaDon> lstHoaDon) {
        this.lstHoaDon = lstHoaDon;
    }
    
    
    

}
