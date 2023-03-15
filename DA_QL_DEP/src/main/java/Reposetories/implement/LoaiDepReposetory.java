/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChiTietDep;
import Models.Dep;
import Models.LoaiDep;
import Reposetories.IDepReposetory;
import Reposetories.ILoaiDepReposetory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class LoaiDepReposetory implements ILoaiDepReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<LoaiDep> getLstDb() {
        List<LoaiDep> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT ld FROM Dep ld");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(LoaiDep ld) {
        try {
            transaction.begin();
            session.saveOrUpdate(ld);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(LoaiDep ld) {
        try {
            transaction.begin();
            session.saveOrUpdate(ld);
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LoaiDep getObj(String ma) {
        LoaiDep ld = null;
        try {
            Query query = session.createQuery("SELECT ld FROM LoaiDep ld WHERE ld.ma = :ma");
            query.setParameter("ma", ma);
            ld = (LoaiDep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ld;
    }

    @Override
    public LoaiDep getObjById(int id) {
        LoaiDep ld = null;
        try {
            Query query = session.createQuery("SELECT ld FROM LoaiDep ld WHERE ld.id = :id");
            query.setParameter("id", id);
            ld = (LoaiDep) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ld;
    }

}
