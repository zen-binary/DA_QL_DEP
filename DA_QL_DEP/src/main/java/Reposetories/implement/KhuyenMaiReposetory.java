/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChucVu;
import Models.KhuyenMai;
import Models.NguoiDung;
import Reposetories.IKhuyenMaiReposetory;
import Reposetories.INguoiDungReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class KhuyenMaiReposetory implements IKhuyenMaiReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<KhuyenMai> getLstDb() {
        List<KhuyenMai> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT km FROM KhuyenMai km");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(KhuyenMai km) {
        try {
            transaction.begin();
            session.saveOrUpdate(km);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(KhuyenMai km) {
        try {
            transaction.begin();
            session.delete(km);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public KhuyenMai getObj(String ma) {
        KhuyenMai km = null;
        try {
            Query query = session.createQuery("SELECT km FROM KhuyenMai km WHERE km.ma = :ma");
            query.setParameter("ma", ma);
            km = (KhuyenMai) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return km;

    }

    @Override
    public KhuyenMai getObjById(int id) {
        KhuyenMai km = null;
        try {
            Query query = session.createQuery("SELECT km FROM KhuyenMai km WHERE km.id = :id");
            query.setParameter("id", id);
            km = (KhuyenMai) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }

    @Override
    public List<KhuyenMai> getAllByTen(String ten) {
        List<KhuyenMai> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT km FROM KhuyenMai km WHERE km.ten LIKE :ten");
        query.setParameter("ten", "%"+ten+"%");
        lst = query.getResultList();
        return lst;    }

}
