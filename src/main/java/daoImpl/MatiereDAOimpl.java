package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;
import entities.Etudiant;
import entities.Matiere;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class MatiereDAOimpl implements BaseDAO<Matiere> {

    private SessionFactory sessionFactory;

    public MatiereDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    @Override
    public boolean add(Matiere element) {
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
    public Matiere getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Matiere matiereToFind = session.load(Matiere.class,id);
            return matiereToFind;

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
    public List<Matiere> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Matiere> query = session.createQuery("from Matiere ");
            List<Matiere> matiereList = query.list();
            return matiereList;

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
    public boolean update(int id, Matiere element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Matiere matiereToUpdate = getById(id);
            if (matiereToUpdate!= null){
                matiereToUpdate.setCoeff(element.getCoeff());
                matiereToUpdate.setDescription(element.getDescription());
                matiereToUpdate.setIntitule(element.getIntitule());
                matiereToUpdate.setDureeMin(element.getDureeMin());

                session.update(matiereToUpdate);
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

            Matiere matiereToRemove = session.load(Matiere.class,id);
            session.delete(matiereToRemove);

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

    //todo
    public int showAllMatiere(int id){
        return 0;
    }




}
