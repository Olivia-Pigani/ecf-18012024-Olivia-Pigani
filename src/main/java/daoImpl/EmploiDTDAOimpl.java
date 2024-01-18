package daoImpl;

import dao.BaseDAO;
import entities.Departement;
import entities.EmploiDuTemps;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class EmploiDTDAOimpl implements BaseDAO<EmploiDuTemps> {

    private SessionFactory sessionFactory;

    public EmploiDTDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public boolean add(EmploiDuTemps element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(element);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
                return false;
            }
        }finally {
            session.close();
            sessionFactory.close();
        }
        return false;
    }

    @Override
    public EmploiDuTemps getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            EmploiDuTemps emploiDuTempsToFind = session.load(EmploiDuTemps.class,id);
            return emploiDuTempsToFind;

        }catch (Exception e){

            if (tx != null){
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
            sessionFactory.close();
        }
        return null;
    }

    @Override
    public List<EmploiDuTemps> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<EmploiDuTemps> query = session.createQuery("from EmploiDuTemps ");
            List<EmploiDuTemps> departementList = query.list();
            return departementList;

        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
            sessionFactory.close();
        }
        return null;
    }

    @Override
    public boolean update(int id, EmploiDuTemps element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            EmploiDuTemps emploiDuTempsToUpdate = getById(id);
            if (emploiDuTempsToUpdate!= null){
                emploiDuTempsToUpdate.setJourEtHeure(element.getJourEtHeure());

                session.update(emploiDuTempsToUpdate);
                tx.commit();
                return true;
            }

        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
            sessionFactory.close();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            EmploiDuTemps emploiDuTempsToRemove = session.load(EmploiDuTemps.class,id);
            session.delete(emploiDuTempsToRemove);

            session.getTransaction().commit();

            return true;

        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
                return false;
            }

        }finally {
            session.close();
            sessionFactory.close();
        }
        return false;
    }
}
