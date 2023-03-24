/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChucVu;
import Models.NguoiDung;
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
public class NguoiDungReposetory implements INguoiDungReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<NguoiDung> getLstDb() {
        List<NguoiDung> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT nd FROM NguoiDung nd");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(NguoiDung nd) {
        try {
            transaction.begin();
            session.saveOrUpdate(nd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(NguoiDung nd) {
        try {
            transaction.begin();
            session.delete(nd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NguoiDung getObj(String ma) {
        NguoiDung nd = null;
        try {
            Query query = session.createQuery("SELECT nd FROM NguoiDung nd WHERE nd.ma = :ma");
            query.setParameter("ma", ma);
            nd = (NguoiDung) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return nd;

    }

    @Override
    public NguoiDung getObjById(int id) {
        NguoiDung nd = null;
        try {
            Query query = session.createQuery("SELECT nd FROM NguoiDung nd WHERE nd.id = :id");
            query.setParameter("id", id);
            nd = (NguoiDung) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nd;
    }

    @Override
    public List<NguoiDung> getLstByTen(String ten) {
        List<NguoiDung> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT nd FROM NguoiDung nd WHERE nd.ma LIKE :ma OR nd.ten LIKE :ten");
        query.setParameter("ma", "%" + ten + "%");
        query.setParameter("ten", "%" + ten + "%");
        lst = query.getResultList();
        return lst;
    }

}
