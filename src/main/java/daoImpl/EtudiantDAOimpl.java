package daoImpl;

import dao.BaseDAO;
import entities.EmploiDuTemps;
import entities.Etudiant;
import entities.Note;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class EtudiantDAOimpl implements BaseDAO<Etudiant> {

    private SessionFactory sessionFactory;

    public EtudiantDAOimpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    @Override
    public boolean add(Etudiant element) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.save(element);
            tx.commit();
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
    public Etudiant getById(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            return session.get(Etudiant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public List<Etudiant> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Etudiant> etudiantList = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            Query<Etudiant> query = session.createQuery("from Etudiant ", Etudiant.class);
            etudiantList = query.list();
            return etudiantList;

        }catch (Exception e){
                e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
        return etudiantList;
    }

    @Override
    public boolean update(int id, Etudiant element) {
        Transaction tx = null;
        Etudiant etudiantToUpdate = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            etudiantToUpdate = getById(id);
            if (etudiantToUpdate != null) {
                etudiantToUpdate.setNom(element.getNom());
                etudiantToUpdate.setPrenom(element.getPrenom());
                etudiantToUpdate.setEmail(element.getEmail());
                etudiantToUpdate.setDateNaissance(element.getDateNaissance());

                session.update(etudiantToUpdate);
                tx.commit();
                return true;
            }
            return false;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            sessionFactory.close();
        }
    }


    @Override
    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Etudiant etudiantToRemove = session.get(Etudiant.class, id);

            if (etudiantToRemove != null) {
                session.delete(etudiantToRemove);
                tx.commit();
                return true;
            }
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



    public int showMatiereNbByStudent(int studentId) {
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("select count(distinct note.matiere) from Note note where note.etudiant.id = :studentId");
            query.setParameter("studentId", studentId);

            int matiereByStudent = (int) query.uniqueResult();

            return matiereByStudent;


        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            sessionFactory.close();
        }

        return -1;
    }


    public List<Note> showScoresByStudent(int studentId){
        List<Note> noteList = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Query<Note> noteQuery = session.createQuery("from Note where etudiant.id = :studentId", Note.class);
            noteQuery.setParameter("studentId",studentId);

            noteList = noteQuery.list();

            return noteList;

        }catch (Exception e){
                e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
        return noteList;
    }

    public float showStudentAVG(int idStudent) {
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("select sum(note.valeur * matiere.coeff) / sum(matiere.coeff) from Note note join note.matiere matiere where note.etudiant.id = :idStudent");
            query.setParameter("idStudent",idStudent);

            return (float) query.uniqueResult();

        }catch (Exception e){
                e.printStackTrace();
        }finally {
            sessionFactory.close();
        }

        return -1;
    }

    public List<Etudiant> getAllByLevel(String niveau){
        List<Etudiant> etudiantList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Query<Etudiant> query = session.createQuery("SELECT e FROM Etudiant e JOIN e.classe c WHERE c.niveau = :niveau", Etudiant.class);
            query.setParameter("niveau", niveau);
            etudiantList = query.list();

        }catch (Exception e){
                e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
        return etudiantList;
    }


}
