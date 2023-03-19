/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChiTietDep;
import Models.Dep;
import Reposetories.IDepReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class DepReposetory implements IDepReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<Dep> getLstDb() {
        List<Dep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT d FROM Dep d");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(Dep d) {
        try {
            transaction.begin();
            session.saveOrUpdate(d);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Dep d) {
        try {
            transaction.begin();
            session.saveOrUpdate(d);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Dep getObj(String ma) {
        Dep d = null;
        try {
            Query query = session.createQuery("SELECT d FROM Dep d WHERE d.ma = :ma");
            query.setParameter("ma", ma);
            d = (Dep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public Dep getObjById(int id) {
        Dep d = null;
        try {
            Query query = session.createQuery("SELECT d FROM Dep d WHERE d.id = :id");
            query.setParameter("id", id);
            d = (Dep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

}
