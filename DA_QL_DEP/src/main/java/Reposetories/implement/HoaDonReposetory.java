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
import java.util.ArrayList;
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
            e.printStackTrace();
            return false;
        }    }

    @Override
    public HoaDon getObj(String ma) {
       HoaDon hd = null;
        try {
            Query query = session.createQuery("SELECT hd FROM HoaDon hd WHERE hd.ma = :ma");
            query.setParameter("ma", ma);
            hd = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
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

}
