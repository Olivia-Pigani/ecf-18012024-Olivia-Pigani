package daoImpl;

import dao.BaseDAO;
import entities.Classe;
import entities.Departement;
import entities.Etudiant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ClasseDAOimpl implements BaseDAO<Classe> {

    private SessionFactory sessionFactory;

    public ClasseDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public boolean add(Classe element) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.save(element);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
                return false;
            }
        } finally {
            sessionFactory.close();
        }
        return false;
    }

    @Override
    public Classe getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Classe classeToFind = session.get(Classe.class,id);
            return classeToFind;

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
    public List<Classe> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Classe> query = session.createQuery("from Classe ");
            List<Classe> classeList = query.list();
            return classeList;

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
    public boolean update(int id, Classe element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Classe classeToUpdate = getById(id);
            if (classeToUpdate!= null){
                classeToUpdate.setNom(element.getNom());
                classeToUpdate.setNiveau(element.getNiveau());

                session.update(classeToUpdate);
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

            Classe classeToRemove = session.load(Classe.class,id);
            session.delete(classeToRemove);

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


    public List<Classe> getAllClassesNoStudent() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Classe> classeQuery = session.createQuery("select nom, niveau from Classe ");
            List<Classe> classeList = classeQuery.list();
            if (classeList != null){
                return classeList;

            }


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
}
