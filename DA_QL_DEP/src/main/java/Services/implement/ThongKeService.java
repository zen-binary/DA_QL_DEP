/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.ChiTietDep;
import Models.HoaDonChiTiet;
import Reposetories.IThongKeReposetory;
import Reposetories.implement.ThongKeReposetory;
import Services.IChiTietDepService;
import Services.IHoaDonChiTietService;
import ViewThongKe.DoanhThuView;
import ViewThongKe.SanPhamView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeService {
    IHoaDonChiTietService hdct;
    IChiTietDepService ctd;
    IThongKeReposetory tkRepo;

    public ThongKeService() {
        tkRepo = new ThongKeReposetory();
        ctd = new ChiTietDepService();
        hdct = new HoaDonChiTietService();
    }
    
    public List<SanPhamView> getAllSP(){
        List<SanPhamView> lst = new ArrayList<>();
        
        for (ChiTietDep ctd : ctd.getLst()) {
            SanPhamView sp = new SanPhamView();
            int soLuongBan = 0;
            Double donGia = 0.0;
            for (HoaDonChiTiet hdct : ctd.getListHdct()) {
                soLuongBan = soLuongBan + hdct.getSoLuong();
                donGia = hdct.getDonGia().doubleValue();
            }
            sp.setTen(ctd.getDep().getTen());
            sp.setSlTon(ctd.getSoLuong());
            sp.setSlBan(soLuongBan);
            sp.setDonGia(ctd.getGiaBan().doubleValue());
            sp.setNgayTao(ctd.getNgayTao());
            sp.setMa(ctd.getDep().getMa());
            lst.add(sp);
        }
        return lst;
    
    }
    
    public List<DoanhThuView> getALlDoanhThu(){
        List<DoanhThuView> lstDt = new ArrayList<>();
        for (Object[] objects : tkRepo.getDoanhThu()) {
            DoanhThuView dt = new DoanhThuView();
            dt.setThang(String.valueOf(objects[0]));
            dt.setSoLuong(String.valueOf(objects[1]));
            dt.setDoanhThu(String.valueOf(objects[2]));
            lstDt.add(dt);
        }
        
        return lstDt;
    }
}
