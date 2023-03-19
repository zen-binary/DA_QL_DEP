/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChucVu;
import Reposetories.IChucVuReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ChuVuReposetory implements IChucVuReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<ChucVu> getLstDb() {
        List<ChucVu> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT cv FROM ChucVu cv");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(ChucVu cv) {

        try {
            transaction.begin();
            session.saveOrUpdate(cv);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(ChucVu cv) {
        try {
            transaction.begin();
            session.delete(cv);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ChucVu getObj(String ma) {
        ChucVu cv = null;
        try {
            Query query = session.createQuery("SELECT cv FROM ChucVu cv WHERE cv.ma = :ma");
            query.setParameter("ma", ma);
            cv = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv;

    }

    @Override
    public ChucVu getObjById(int id) {
        ChucVu cv = null;
        try {
            Query query = session.createQuery("SELECT cv FROM ChucVu cv WHERE cv.id = :id");
            query.setParameter("id", id);
            cv = (ChucVu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv;
    }

}
