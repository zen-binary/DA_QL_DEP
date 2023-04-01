/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Reposetories.IHoaDonChiTietReposetory;
import Reposetories.IHoaDonReposetory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HoaDonReposetory implements IHoaDonReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<HoaDon> getLstDb() {
        List<HoaDon> lst = new ArrayList<>();
        Query query = session.createQuery("FROM HoaDon");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(HoaDon hd) {
        try {
            transaction.begin();
            session.saveOrUpdate(hd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(HoaDon hd) {
        try {
            transaction.begin();
            session.delete(hd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public HoaDon getObj(String ma) {
        HoaDon hd = null;
        try {
            Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.ma = :ma");
            query.setParameter("ma", ma);
            hd = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return hd;
    }

    @Override
    public HoaDon getObjById(int id) {
        HoaDon hd = null;
        try {
            Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.id = :id");
            query.setParameter("id", id);
            hd = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public static void main(String[] args) {
        new HoaDonChiTietReposetory().getLstDb();
    }

    @Override
    public List<HoaDon> getAllByObj(String maHd, int tinhTrang) {
        List<HoaDon> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.ma LIKE :hd AND hd.tinhTrang = :tinhTrang");
        query.setParameter("hd", "%" + maHd + "%");
        query.setParameter("tinhTrang", tinhTrang);
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<HoaDon> getAllByMa(String maHd) {
        List<HoaDon> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.ma LIKE :hd");
        query.setParameter("hd", "%" + maHd + "%");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public Double getDoanhThu(String value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        Double doanhThu = 0.0;

        try {
            transaction.begin();
            Query query = null;
            if (value.equalsIgnoreCase("day")) {
                query = session.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd WHERE DAY(hd.ngayThanhToan) = :day");
                query.setParameter("day", day);
            } else if (value.equalsIgnoreCase("month")) {
                query = session.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd WHERE YEAR(hd.ngayThanhToan) = :year AND MONTH(hd.ngayThanhToan) = :month");
                query.setParameter("year", year);
                query.setParameter("month", month);
            } else if (value.equalsIgnoreCase("year")) {
                query = session.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd WHERE YEAR(hd.ngayThanhToan) = :year");
                query.setParameter("year", year);
            } else {
                query = session.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd");
            }
//            Query query = session.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd WHERE YEAR(hd.ngayThanhToan) = :year AND MONTH(hd.ngayThanhToan) = :month");
//            query.setParameter("year", year);
//            query.setParameter("month", 4);
//            query.setParameter("day", day);
            List<Object> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                doanhThu = new BigDecimal(query.getResultList().get(0).toString()).doubleValue();
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        return doanhThu;
    }

    @Override
    public int getCountHoaDon(String value, int tinhTrang) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int count = 0;
        try {
            transaction.begin();
            Query query = null;
            if (value.equalsIgnoreCase("day")) {
                query = session.createQuery("SELECT COUNT(*) FROM HoaDon hd WHERE DAY(hd.ngaySua) = :day AND hd.tinhTrang =:tinhTrang");
                query.setParameter("day", day);
                query.setParameter("tinhTrang", tinhTrang);
            }else if (value.equalsIgnoreCase("month")) {
                query = session.createQuery("SELECT COUNT(*) FROM HoaDon hd WHERE YEAR(hd.ngaySua) = :year AND MONTH(hd.ngaySua) = :month AND hd.tinhTrang =:tinhTrang");
                query.setParameter("year", year);
                query.setParameter("month", month);
                query.setParameter("tinhTrang", tinhTrang);
            } else if (value.equalsIgnoreCase("year")) {
                query = session.createQuery("SELECT COUNT(*) FROM HoaDon hd WHERE YEAR(hd.ngaySua) = :year AND hd.tinhTrang =:tinhTrang");
                query.setParameter("year", year);
                query.setParameter("tinhTrang", tinhTrang);
            } else {
                query = session.createQuery("SELECT COUNT(*) FROM HoaDon hd");
            }
            List<Object> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                count = ((Long) result.get(0)).intValue();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return count;
    }

}
