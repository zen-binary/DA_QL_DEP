/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChiTietDep;
import Models.Dep;
import Models.LoaiDep;
import Models.MauSac;
import Models.Nsx;
import Models.Size;
import Reposetories.IDepReposetory;
import Reposetories.ILoaiDepReposetory;
import Reposetories.IMauSacReposetory;
import Reposetories.INsxReposetory;
import Reposetories.ISizeReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class SizeReposetory implements ISizeReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<Size> getLstDb() {
        List<Size> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT s FROM Size s");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(Size s) {
        try {
            transaction.begin();
            session.saveOrUpdate(s);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Size s) {
        try {
            transaction.begin();
            session.delete(s);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Size getObj(String ma) {
        Size s = null;
        try {
            Query query = session.createQuery("SELECT s FROM Size s WHERE s.ma = :ma");
            query.setParameter("ma", ma);
            s = (Size) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Size getObjById(int id) {
        Size s = null;
        try {
            Query query = session.createQuery("SELECT s FROM Size s WHERE s.id = :id");
            query.setParameter("id", id);
            s = (Size) query.getSingleResult();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Size> getLstBySize(Float size) {
        List<Size> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT s FROM Size s WHERE s.kichCo LIKE :size");
      
            query.setParameter("size", size);

      
        lst = query.getResultList();
        return lst;
    }

    @Override
    public Size getObjBySize(Float size) {
        Size s = null;
        try {
            Query query = session.createQuery("SELECT s FROM Size s WHERE s.kichCo LIKE :size");
            query.setParameter("size", size);
            s = (Size) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;   
    }

}
