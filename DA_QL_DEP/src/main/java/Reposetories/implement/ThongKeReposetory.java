/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.HoaDon;
import Reposetories.IThongKeReposetory;
import ViewThongKe.SanPhamView;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ThongKeReposetory implements IThongKeReposetory{
    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();
    
    @Override
    public List<Object> getLstSpView() {
       List<Object> lst = new ArrayList<>();
       Query query = session.createQuery("SELECT hdct FROM HoaDonChiTiet hdct JOIN HDCT.chiTietDep");
       lst = query.getResultList();
       
       
       return lst;
    }

    @Override
    public List<Object[]> getDoanhThu() {
        List<Object[]> lst = new ArrayList<>();
       Query query = session.createQuery("SELECT month(hdct.hoaDon.ngayThanhToan), sum(hdct.soLuong), SUM(hdct.hoaDon.tongTien) FROM HoaDonChiTiet hdct  WHERE hdct.hoaDon.tinhTrang =1 GROUP BY month(hdct.hoaDon.ngayThanhToan)");
       lst = query.getResultList();
       return lst;
    }
    
}
