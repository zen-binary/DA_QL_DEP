/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.HoaDon;
import Reposetories.IThongKeReposetory;
import ViewThongKe.DoanhThuView;
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
public class ThongKeReposetory implements IThongKeReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<Object[]> getDoanhThuSpView(int index) {
        List<Object[]> lst = new ArrayList<>();
        Query query = null;
        if (index == 1) {
            query = session.createNativeQuery("SELECT TOP(3) dbo.DEP.Ma AS N'Mã', dbo.DEP.Ten AS N'Tên', dbo.CHITIETDEP.NgayTao AS N'Ngày Tạo', dbo.CHITIETDEP.SoLuong AS N'Số Lượng Tồn', dbo.CHITIETDEP.GiaBan AS N'Giá Bán',SUM(dbo.HOADONCHITIET.SoLuong) AS N'Số Lượng', SUM(dbo.HOADONCHITIET.SoLuong)*dbo.CHITIETDEP.GiaBan AS N'Doanh Thu' FROM dbo.CHITIETDEP JOIN dbo.DEP ON DEP.Id = CHITIETDEP.IdDep JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdCTD = CHITIETDEP.Id JOIN dbo.HOADON ON HOADON.Id = HOADONCHITIET.IdHD  GROUP BY dbo.DEP.Ma,dbo.DEP.Ten,dbo.CHITIETDEP.NgayTao, dbo.CHITIETDEP.SoLuong,dbo.CHITIETDEP.GiaBan ORDER BY SUM(dbo.HOADONCHITIET.SoLuong) DESC");

        } else if (index == 2) {
            query = session.createNativeQuery("SELECT dbo.DEP.Ma AS N'Mã', dbo.DEP.Ten AS N'Tên', dbo.CHITIETDEP.NgayTao AS N'Ngày Tạo', dbo.CHITIETDEP.SoLuong AS N'Số Lượng Tồn', dbo.CHITIETDEP.GiaBan AS N'Giá Bán',SUM(dbo.HOADONCHITIET.SoLuong) AS N'Số Lượng', SUM(dbo.HOADONCHITIET.SoLuong)*dbo.CHITIETDEP.GiaBan AS N'Doanh Thu' FROM dbo.CHITIETDEP JOIN dbo.DEP ON DEP.Id = CHITIETDEP.IdDep JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdCTD = CHITIETDEP.Id JOIN dbo.HOADON ON HOADON.Id = HOADONCHITIET.IdHD WHERE  dbo.CHITIETDEP.SoLuong < 100 GROUP BY dbo.DEP.Ma,dbo.DEP.Ten,dbo.CHITIETDEP.NgayTao, dbo.CHITIETDEP.SoLuong,dbo.CHITIETDEP.GiaBan ");
        } else {
            query = session.createNativeQuery("SELECT dbo.DEP.Ma AS N'Mã', dbo.DEP.Ten AS N'Tên', dbo.CHITIETDEP.NgayTao AS N'Ngày Tạo', dbo.CHITIETDEP.SoLuong AS N'Số Lượng Tồn', dbo.CHITIETDEP.GiaBan AS N'Giá Bán',SUM(dbo.HOADONCHITIET.SoLuong) AS N'Số Lượng', SUM(dbo.HOADONCHITIET.SoLuong)*dbo.CHITIETDEP.GiaBan AS N'Doanh Thu' FROM dbo.CHITIETDEP JOIN dbo.DEP ON DEP.Id = CHITIETDEP.IdDep JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdCTD = CHITIETDEP.Id JOIN dbo.HOADON ON HOADON.Id = HOADONCHITIET.IdHD  GROUP BY dbo.DEP.Ma,dbo.DEP.Ten,dbo.CHITIETDEP.NgayTao, dbo.CHITIETDEP.SoLuong,dbo.CHITIETDEP.GiaBan");

        }
//        Query query = session.createQuery("SELECT dep.ma , dep.ten , ctd.ngayTao, ctd.soLuong , ctd.giaBan , SUM(hdc.soLuong), SUM(hdc.soLuong) * ctd.giaBan FROM ChiTietDep ctd JOIN ctd.dep dep JOIN ctd.hoaDonChiTiet hdc JOIN hdc.hoaDon hd GROUP BY dep.ma, dep.ten, ctd.ngayTao, ctd.soLuong, ctd.giaBan");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<Object[]> getDoanhThu(String year, String month) {
        List<Object[]> lst = new ArrayList<>();
        Query query = session.createNativeQuery("SELECT MONTH(dbo.HOADON.NgayThanhToan) AS N'Tháng' ,SUM(HOADONCHITIET.SoLuong) AS N'Số Lượng', SUM(HOADONCHITIET.SoLuong*dbo.CHITIETDEP.GiaNhap) AS 'Khoản Chi', SUM(HOADONCHITIET.SoLuong*dbo.CHITIETDEP.GiaBan) AS N'Doanh Thu', SUM(HOADONCHITIET.SoLuong*dbo.CHITIETDEP.GiaBan) - SUM(HOADONCHITIET.SoLuong*dbo.CHITIETDEP.GiaNhap) AS N'Tiền Lời'  FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdHD = HOADON.Id JOIN dbo.CHITIETDEP ON CHITIETDEP.Id = HOADONCHITIET.IdCTD WHERE HOADON.TinhTrang = 1 AND YEAR(dbo.HOADON.NgayThanhToan) LIKE :year AND MONTH(dbo.HOADON.NgayThanhToan) LIKE :month GROUP BY YEAR(dbo.HOADON.NgayThanhToan), MONTH(dbo.HOADON.NgayThanhToan)");
//        Query query = session.createQuery("SELECT month(hdct.hoaDon.ngayThanhToan), sum(hdct.soLuong), SUM(hdct.hoaDon.tongTien) FROM HoaDonChiTiet hdct  WHERE hdct.hoaDon.tinhTrang =1 GROUP BY month(hdct.hoaDon.ngayThanhToan)");
        query.setParameter("year", "%" + year + "%");
        query.setParameter("month", "%" + month + "%");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<String> getMonthDoanhThu(String year) {
        List<String> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT MONTH(hd.ngayThanhToan) FROM HoaDon hd WHERE hd.tinhTrang = 1 GROUP BY MONTH(hd.ngayThanhToan)");
//        query.setParameter("year", "%" + year + "%");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<String> getYearDoanhThu() {
        List<String> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT YEAR(hd.ngayThanhToan) FROM HoaDon hd WHERE hd.tinhTrang = 1 GROUP BY YEAR(hd.ngayThanhToan) ORDER BY YEAR(hd.ngayThanhToan) DESC");
        lst = query.getResultList();
        return lst;
    }

}
