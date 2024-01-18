package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;
import entities.Etudiant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class EtudiantDAOimpl implements BaseDAO<Etudiant> {

    private SessionFactory sessionFactory;

    public EtudiantDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    @Override
    public boolean add(Etudiant element) {
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
    public Etudiant getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Etudiant etudiantsToFind = session.load(Etudiant.class,id);
            return etudiantsToFind;

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
    public List<Etudiant> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Etudiant> query = session.createQuery("from Etudiant ");
            List<Etudiant> etudiantList = query.list();
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
    public boolean update(int id, Etudiant element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Etudiant etudiantToUpdate = getById(id);
            if (etudiantToUpdate!= null){
                etudiantToUpdate.setNom(element.getNom());
                etudiantToUpdate.setPrenom(element.getPrenom());
                etudiantToUpdate.setEmail(element.getEmail());
                etudiantToUpdate.setDateNaissance(element.getDateNaissance());

                session.update(etudiantToUpdate);
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

            Etudiant etudiantToRemove = session.load(Etudiant.class,id);
            session.delete(etudiantToRemove);

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
