/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.Dep;
import Models.HoaDonChiTiet;
import Reposetories.IHoaDonChiTietReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietReposetory implements IHoaDonChiTietReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<HoaDonChiTiet> getLstDb() {
        List<HoaDonChiTiet> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT hdct FROM HoaDonChiTiet hdct");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(HoaDonChiTiet hdCt) {
        try {
            transaction.begin();
            session.saveOrUpdate(hdCt);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(HoaDonChiTiet hdCt) {
        try {
            transaction.begin();
            session.delete(hdCt);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }

    @Override
    public HoaDonChiTiet getObj(String ma) {
       HoaDonChiTiet hdct = null;
        try {
            Query query = session.createQuery("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.ma = :ma");
            query.setParameter("ma", ma);
            hdct = (HoaDonChiTiet) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdct;
    }

    @Override
    public HoaDonChiTiet getObjById(int id) {
        HoaDonChiTiet hdct = null;
        try {
            Query query = session.createQuery("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.id = :id");
            query.setParameter("id", id);
            hdct = (HoaDonChiTiet) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdct;
    }

}
