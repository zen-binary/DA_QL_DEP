/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.Dep;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.KhachHang;
import Reposetories.IHoaDonChiTietReposetory;
import Reposetories.IHoaDonReposetory;
import Reposetories.IKhachHangReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class KhachHangReposetory implements IKhachHangReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<KhachHang> getLstDb() {
        List<KhachHang> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT kh FROM KhachHang kh");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(KhachHang kh) {
        try {
            transaction.begin();
            session.saveOrUpdate(kh);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(KhachHang kh) {
        try {
            transaction.begin();
            session.delete(kh);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }    }

    @Override
    public KhachHang getObj(String ma) {
       KhachHang kh = null;
        try {
            Query query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.ma = :ma");
            query.setParameter("ma", ma);
            kh = (KhachHang) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return kh;
    }

    @Override
    public KhachHang getObjById(int id) {
       KhachHang kh = null;
        try {
            Query query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.id = :id");
            query.setParameter("id", id);
            kh = (KhachHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    @Override
    public List<KhachHang> getAllByObj(String ten) {
       List<KhachHang> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.ten LIKE :ten OR kh.ma LIKE :ma OR kh.sdt LIKE :sdt");
        query.setParameter("ten", "%"+ten+"%");
        query.setParameter("ma", "%"+ten+"%");
        query.setParameter("sdt", "%"+ten+"%");
        lst = query.getResultList();
        return lst;    }

}
