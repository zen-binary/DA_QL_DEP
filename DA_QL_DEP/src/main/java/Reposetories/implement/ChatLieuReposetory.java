/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories.implement;

import HibernateConfig.HibernateConfig;
import Models.ChatLieu;
import Models.ChucVu;
import Models.NguoiDung;
import Reposetories.IChatLieuReposetory;
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
public class ChatLieuReposetory implements IChatLieuReposetory {

    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();

    @Override
    public List<ChatLieu> getLstDb() {
        List<ChatLieu> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT cl FROM ChatLieu cl");
        lst = query.getResultList();
        return lst;
    }

    @Override
    public boolean save(ChatLieu cl) {
        try {
            transaction.begin();
            session.saveOrUpdate(cl);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ChatLieu cl) {
        try {
            transaction.begin();
            session.delete(cl);
            transaction.commit();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ChatLieu getObj(String ma) {
        ChatLieu cl = null;
        try {
            Query query = session.createQuery("SELECT cl FROM ChatLieu cl WHERE cl.ma = :ma");
            query.setParameter("ma", ma);
            cl = (ChatLieu) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return cl;

    }

    @Override
    public ChatLieu getObjById(int id) {
        ChatLieu cl = null;
        try {
            Query query = session.createQuery("SELECT cl FROM ChatLieu cl WHERE cl.id = :id");
            query.setParameter("id", id);
            cl = (ChatLieu) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }

    @Override
    public List<ChatLieu> getLstByCl(String cl) {
        List<ChatLieu> lst = new ArrayList<>();
        Query query = session.createQuery("SELECT cl FROM ChatLieu cl WHERE cl.ten LIKE :cl");
        query.setParameter("cl", "%"+cl+"%");
        lst = query.getResultList();
        return lst;    }

}
