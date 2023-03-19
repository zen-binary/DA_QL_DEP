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
import Reposetories.IDepReposetory;
import Reposetories.ILoaiDepReposetory;
import Reposetories.IMauSacReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class MauSacReposetory implements IMauSacReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<MauSac> getLstDb() {
        List<MauSac> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ms FROM MauSac ms");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(MauSac ms) {
        try {
            transaction.begin();
            session.saveOrUpdate(ms);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(MauSac ms) {
        try {
            transaction.begin();
            session.saveOrUpdate(ms);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MauSac getObj(String ma) {
        MauSac ms = null;
        try {
            Query query = session.createQuery("SELECT ms FROM MauSac ms WHERE ms.ma = :ma");
            query.setParameter("ma", ma);
            ms = (MauSac) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    @Override
    public MauSac getObjById(int id) {
       MauSac ms = null;
        try {
            Query query = session.createQuery("SELECT ms FROM MauSac ms WHERE ms.id = :id");
            query.setParameter("id", id);
            ms = (MauSac) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    @Override
    public List<MauSac> getLstByMs(String ms) {
        List<MauSac> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ms FROM MauSac ms WHERE ms.mauSac LIKE :ms");
        query.setParameter("ms", "%"+ ms +"%");
        lst = query.getResultList();
        return lst;  }

}
