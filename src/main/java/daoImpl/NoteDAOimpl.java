package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;
import entities.Note;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class NoteDAOimpl implements BaseDAO<Note> {

    private SessionFactory sessionFactory;

    public NoteDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public boolean add(Note element) {
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
    public Note getById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Note noteToFind = session.load(Note.class,id);
            return noteToFind;

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
    public List<Note> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query<Note> query = session.createQuery("from Note ");
            List<Note> noteList = query.list();
            return noteList;

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
    public boolean update(int id, Note element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Note noteToUpdate = getById(id);
            if (noteToUpdate!= null){
                noteToUpdate.setCommentaire(element.getCommentaire());
                noteToUpdate.setValeur(element.getValeur());

                session.update(noteToUpdate);
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

            Note noteToRemove = session.load(Note.class,id);
            session.delete(noteToRemove);

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
    public List<Note> showScoreByStudent(int id){
        return null;
    }
}
