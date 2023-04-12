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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    }

    public List<SanPhamView> getAllSP(int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<SanPhamView> lst = new ArrayList<>();
        for (Object[] object : tkRepo.getDoanhThuSpView(index)) {
            try {
                SanPhamView sp = new SanPhamView();
                sp.setMa(object[0].toString());
                sp.setTen(object[1].toString());
                sp.setNgayTao(sdf.parse(object[2].toString()));
                sp.setSlTon(Integer.valueOf(object[3].toString()));
                sp.setDonGia(Double.valueOf(object[4].toString()));
                sp.setSlBan(object[5]==null?0:Integer.valueOf(object[5].toString()));
                sp.setDoanhThu(object[6]==null?0.0:Double.valueOf(object[6].toString()));
                lst.add(sp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return lst;
    }

    public List<DoanhThuView> getALlDoanhThu(String year, String month) {
        List<DoanhThuView> lstDt = new ArrayList<>();
        for (Object[] objects : tkRepo.getDoanhThu(year, month)) {
            DoanhThuView dt = new DoanhThuView();
            dt.setThang(String.valueOf(objects[0]));
            dt.setSoLuong(Integer.valueOf(objects[1].toString()));
            dt.setKhoanChi(Double.valueOf(objects[2].toString()));
            dt.setDoanhThu(Double.valueOf(objects[3].toString()));
            dt.setTienLoi(Double.valueOf(objects[4].toString()));
            lstDt.add(dt);
        }
        
        return lstDt;
    }
    public List<String> getMonthDoanhThu(String year){
        return tkRepo.getMonthDoanhThu(year);
    }
    public List<String> getYearDoanhThu(){
        return tkRepo.getYearDoanhThu();
    }
}
