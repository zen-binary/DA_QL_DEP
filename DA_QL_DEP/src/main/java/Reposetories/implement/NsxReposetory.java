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
import Reposetories.IDepReposetory;
import Reposetories.ILoaiDepReposetory;
import Reposetories.IMauSacReposetory;
import Reposetories.INsxReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class NsxReposetory implements INsxReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<Nsx> getLstDb() {
        List<Nsx> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT nsx FROM Nsx nsx");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(Nsx nsx) {
        try {
            transaction.begin();
            session.saveOrUpdate(nsx);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Nsx nsx) {
        try {
            transaction.begin();
            session.saveOrUpdate(nsx);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Nsx getObj(String ma) {
        Nsx nsx = null;
        try {
            Query query = session.createQuery("SELECT nsx FROM Nsx nsx WHERE nsx.ma = :ma");
            query.setParameter("ma", ma);
            nsx = (Nsx) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nsx;
    }

    @Override
    public Nsx getObjById(int id) {
       Nsx nsx = null;
        try {
            Query query = session.createQuery("SELECT nsx FROM Nsx nsx WHERE nsx.id = :id");
            query.setParameter("id", id);
            nsx = (Nsx) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nsx;
    }

}
