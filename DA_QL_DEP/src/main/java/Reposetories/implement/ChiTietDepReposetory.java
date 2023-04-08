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
//            e.printStackTrace();
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
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.tinhTrang = :tinhTrang AND (ctd.dep.ten LIKE :ten OR ctd.dep.ma LIKE :ma) AND ctd.soLuong > :soLuong");
        query.setParameter("tinhTrang", tinhTrang);
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("ma", "%" + ten + "%");
        query.setParameter("soLuong", soLuong);
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<ChiTietDep> getAllBySoLuong(int soLuong, String ten) {
        List<ChiTietDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.soLuong = :soLuong AND (ctd.dep.ten LIKE :ten OR ctd.dep.ma LIKE :ma)");
        query.setParameter("soLuong", soLuong);
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("ma", "%" + ten + "%");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public List<ChiTietDep> getAllByTinhTrang(int tinhTrang, String ten) {
        List<ChiTietDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.tinhTrang = :tinhTrang AND (ctd.dep.ten LIKE :ten OR ctd.dep.ma LIKE :ma)");
        query.setParameter("tinhTrang", tinhTrang);
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("ma", "%" + ten + "%");
        lst = query.getResultList();
        return lst;
    }

    public static void main(String[] args) {
        System.out.println(new ChiTietDepReposetory().getObj("D001"));
    }

    @Override
    public List<ChiTietDep> getLstByTen(String ten) {
        List<ChiTietDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.dep.ten LIKE :ten OR ctd.dep.ma LIKE :ma");
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("ma", "%" + ten + "%");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public ChiTietDep getFindAllObj(int idDep, int idLoai, int idSize, int idMs, int idCl, int idNsx) {
        ChiTietDep ctd = null;
        try {
            Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd WHERE ctd.dep.id = :idDep AND ctd.loaiDep = :idLoai AND ctd.size = :idSize AND ctd.mauSac.id = :idMs AND ctd.chatLieu.id = :idCl AND ctd.nsx.id = :idNsx");
            query.setParameter("idDep", idDep);
            query.setParameter("idLoai", idLoai);
            query.setParameter("idSize", idSize);
            query.setParameter("idMs", idMs);
            query.setParameter("idCl", idCl);
            query.setParameter("idNsx", idNsx);
            ctd = (ChiTietDep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctd;

    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            transaction.begin();
            String hql = "SELECT COUNT(*) FROM ChiTietDep";
            Query query = session.createQuery(hql);
            List<Object> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                count = ((Long) result.get(0)).intValue();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();

        }
        return count;
    }

    @Override
    public int getCountTinhTrang(int tinhTrang) {
        int count = 0;
        try {
            transaction.begin();
            Query query = session.createQuery("SELECT COUNT(*) FROM ChiTietDep ctd WHERE ctd.tinhTrang = :tinhTrang");
            query.setParameter("tinhTrang", tinhTrang);
            List<Object> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                count = ((Long) result.get(0)).intValue();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return count;
    }

}
