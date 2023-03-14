/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "HOADONCHITIET")
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "IdHD")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "IdCTD")
    private ChiTietDep chiTietDep;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "NgayTao")
    @Temporal(TemporalType.DATE)
    private Date ngayTao;

    @Column(name = "NgaySua")
    @Temporal(TemporalType.DATE)
    private Date ngaySua;

    @Column(name = "TinhTrang")
    private int tinhTrang;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, HoaDon hoaDon, ChiTietDep chiTietDep, int soLuong, Date ngayTao, Date ngaySua, int tinhTrang) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.chiTietDep = chiTietDep;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietDep getChiTietDep() {
        return chiTietDep;
    }

    public void setChiTietDep(ChiTietDep chiTietDep) {
        this.chiTietDep = chiTietDep;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    
    
}
