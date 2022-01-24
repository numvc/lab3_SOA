package soa.lab3.beans;

import soa.lab3.common.entities.HumanBeing;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class HumanBeingDaoImpl implements HumanBeingDao {
    @Override
    public HumanBeing findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(HumanBeing.class, id);
    }

    @Override
    public void save(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(humanBeing);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(humanBeing);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(humanBeing);
        tx1.commit();
        session.close();
    }

    @Override
    public ArrayList<HumanBeing> findAll() {
        return (ArrayList<HumanBeing>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HumanBeing").list();
    }

}
