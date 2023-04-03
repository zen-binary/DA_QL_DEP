/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories;

import HibernateConfig.HibernateConfig;
import Models.ChiTietDep;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Reposetories.implement.ChiTietDepReposetory;
import Reposetories.implement.HoaDonReposetory;
import Reposetories.implement.ThongKeReposetory;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrator
 */
public class test {
    
    public static void main(String[] args) {
        
        Session session = HibernateConfig.getFACTORY().openSession();
//        IChiTietDepReposetory ctd = new ChiTietDepReposetory();
//        int size = ctd.getLstDb().size() -1;
//        for (int i = 0; i < size; i++) {
//            int soluong = 0;
//            List<HoaDonChiTiet> lst = ctd.getLstDb().get(i).getListHdct();
//            for (HoaDonChiTiet hdct : lst) {
//                soluong = soluong + hdct.getSoLuong();
//            }
//            System.out.println("MaSp: "+ ctd.getLstDb().get(i).getDep().getMa());
//            System.out.println("SO luong: " +soluong);
//        }
//
//        IThongKeReposetory tk = new ThongKeReposetory();
//        System.out.println("tk: " +tk.getDoanhThu());

//        IThongKeReposetory tk = new ThongKeReposetory();
//        for (HoaDon hd : tk.getDoanhThu()) {
//            String thang = "";
//            Double tongChi = 0.0;
//            for (HoaDonChiTiet hdct : hd.getLstHdct()) {
//                tongChi = tongChi + hdct.getChiTietDep().getGiaNhap().doubleValue();
//            }
//            System.out.println("THang: " + hd.getNgayThanhToan());
//            System.out.println("TOng CHi: "+tongChi);
//        }

//             Query query = session.createQuery("SELECT month(hd.ngayThanhToan),  hd. FROM HoaDon hd WHERE hd.tinhTrang = 1 GROUP BY month(hd.ngayThanhToan)");
             Query query2 = session.createQuery("SELECT month(hdct.hoaDon.ngayThanhToan), sum(hdct.soLuong), SUM(hdct.chiTietDep.giaNhap), SUM(hdct.donGia), SUM(hdct.donGia)*sum(hdct.soLuong) FROM HoaDonChiTiet hdct  WHERE hdct.hoaDon.tinhTrang =1 GROUP BY month(hdct.hoaDon.ngayThanhToan)");
             List<Object[]> lst = query2.getResultList();
             for (Object[] ob : lst) {
                 System.out.println("1:"+ob[0]);
                 System.out.println("2:"+ob[1]);
                 System.out.println("3:"+ob[2]);
                 System.out.println("4:"+ob[3]);
                 System.out.println("5:"+ob[4]);
        }
    }
}
