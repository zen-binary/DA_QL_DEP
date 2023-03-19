/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChatLieu;
import Models.ChiTietDep;
import Reposetories.IChiTietDepReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ChiTietDepReposetory implements IChiTietDepReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<ChiTietDep> getLstDb() {
        List<ChiTietDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(ChiTietDep ctd) {
        try {
            transaction.begin();
            session.saveOrUpdate(ctd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ChiTietDep ctd) {
        try {
            transaction.begin();
            session.delete(ctd);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ChiTietDep getObj(String ma) {
        ChiTietDep ctd = null;
        try {
            Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.dep.ma = :ma");
            query.setParameter("ma", ma);
            ctd = (ChiTietDep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctd;
    }

    @Override
    public ChiTietDep getObjById(int id) {
        ChiTietDep ctd = null;
        try {
            Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.id = :id");
            query.setParameter("id", id);
            ctd = (ChiTietDep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctd;
    }

    @Override
    public List<ChiTietDep> getAllByObj(int tinhTrang, String ten, int soLuong) {
        List<ChiTietDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.tinhTrang = :tinhTrang AND ctd.dep.ten LIKE :ten AND ctd.soLuong > :soLuong");
        query.setParameter("tinhTrang", tinhTrang);
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("soLuong", soLuong);
        lst = query.getResultList();
        return lst;
    }

    public static void main(String[] args) {
        System.out.println(new ChiTietDepReposetory().getObj("D001"));
    }

}
