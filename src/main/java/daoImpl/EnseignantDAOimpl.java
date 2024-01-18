package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;
import entities.Enseignant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class EnseignantDAOimpl implements BaseDAO<Enseignant> {

    private SessionFactory sessionFactory;

    public EnseignantDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    @Override
    public boolean add(Enseignant element) {
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
    public Enseignant getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Enseignant enseignantToFind = session.load(Enseignant.class,id);
            return enseignantToFind;

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
    public List<Enseignant> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Enseignant> query = session.createQuery("from Enseignant ");
            List<Enseignant> enseignantList = query.list();
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
    public boolean update(int id, Enseignant element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Enseignant enseignantToUpdate = getById(id);
            if (enseignantToUpdate!= null){
                enseignantToUpdate.setAge(element.getAge());
                enseignantToUpdate.setGrade(element.getGrade());
                enseignantToUpdate.setPrenom(element.getPrenom());
                enseignantToUpdate.setNom(element.getNom());
                enseignantToUpdate.setProfPrincipal(element.getProfPrincipal());

                session.update(enseignantToUpdate);
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

            Enseignant enseignantToRemove = session.load(Enseignant.class,id);
            session.delete(enseignantToRemove);

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
