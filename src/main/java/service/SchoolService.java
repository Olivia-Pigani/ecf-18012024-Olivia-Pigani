package service;

import daoImpl.*;
import entities.*;

import java.util.Date;
import java.util.List;

public class SchoolService {

    private DepartementDAOimpl departementDAO;
    private EnseignantDAOimpl enseignantDAO;

    private EtudiantDAOimpl etudiantDAO;

    private MatiereDAOimpl matiereDAO;

    private NoteDAOimpl noteDAO;

    private ClasseDAOimpl classeDAO;

    private EmploiDTDAOimpl emploiDTDAO;

    public SchoolService(DepartementDAOimpl departementDAO, EnseignantDAOimpl enseignantDAO, EtudiantDAOimpl etudiantDAO, MatiereDAOimpl matiereDAO, NoteDAOimpl noteDAO, ClasseDAOimpl classeDAO, EmploiDTDAOimpl emploiDTDAO) {
        this.departementDAO = departementDAO;
        this.enseignantDAO = enseignantDAO;
        this.etudiantDAO = etudiantDAO;
        this.matiereDAO = matiereDAO;
        this.noteDAO = noteDAO;
        this.classeDAO = classeDAO;
        this.emploiDTDAO = emploiDTDAO;
    }

    public void createdep(String nom) {
        try {
            Departement newDepartement = new Departement();
            newDepartement.setNom(nom);
            departementDAO.add(newDepartement);
            System.out.println("un département a été produit !! ");
        } catch (Exception e) {
            System.out.println("erreur dans la production de département !! ");
            e.printStackTrace();
        }

    }

    public Departement getById(int depId) {
        try {
            Departement departement = departementDAO.getById(depId);
            return departement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createEnseignant(String nom, String prenom, String grade, Boolean responsable, Boolean profPx, int age, Boolean responsable1) {
        try {
            Enseignant newEnseignant = new Enseignant();
            newEnseignant.setNom(nom);
            newEnseignant.setPrenom(prenom);
            newEnseignant.setGrade(grade);
            newEnseignant.setAge(age);
            newEnseignant.setProfPrincipal(profPx);
            newEnseignant.setIsResponsable(responsable);
            enseignantDAO.add(newEnseignant);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public void addEtudiant(String nom, String prenom, Date birthDate, String email) {
        try {
            Etudiant newEtudiant = new Etudiant();
            newEtudiant.setPrenom(prenom);
            newEtudiant.setNom(nom);
            newEtudiant.setEmail(email);
            newEtudiant.setDateNaissance(birthDate);

            etudiantDAO.add(newEtudiant);

            System.out.println(" the student has been correctly produced !! ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMatiere(String intitulé, String description, int dureeMin, int coeff) {
        try {
            Matiere newMatiere = new Matiere();
            newMatiere.setIntitule(intitulé);
            newMatiere.setDescription(description);
            newMatiere.setCoeff(coeff);
            newMatiere.setDureeMin(dureeMin);

            matiereDAO.add(newMatiere);
            System.out.println(" the matiere has been correctly produced !! ");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Matiere getByIdMatiere(int matiereId) {
        return matiereDAO.getById(matiereId);
    }

    public Etudiant getByIdEtudiant(int eleveId) {
        return etudiantDAO.getById(eleveId);
    }

    public void addNote(int score, String com, int matiereId, int eleveId) {
        try {
            Matiere matiere = getByIdMatiere(matiereId);
            Etudiant etudiant = getByIdEtudiant(eleveId);
            Note newNote = new Note();
            newNote.setValeur(score);
            newNote.setCommentaire(com);
            newNote.setMatiere(matiere);
            newNote.setEtudiant(etudiant);

            noteDAO.add(newNote);
            System.out.println("success ! ");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void addClasse(String nom, String niveau) {
        try {
            Classe newClasse = new Classe();
            newClasse.setNiveau(niveau);
            newClasse.setNom(nom);

            classeDAO.add(newClasse);
            System.out.println("success ! ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEDT(Date jourEtHeure) {
        try {
            EmploiDuTemps newEDT = new EmploiDuTemps();
            newEDT.setJourEtHeure(jourEtHeure);
            emploiDTDAO.add(newEDT);
            System.out.println("success ! ");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getAllClassesNoStudent() {
        try {

            List<Etudiant> etudiantList =  classeDAO.getAllClassesNoStudent();

            for (Etudiant e:etudiantList
                 ) {
                System.out.println(e);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Etudiant getByIdEleve(int eleveId) {
        return etudiantDAO.getById(eleveId);

    }

    public void deleteEleve(int eleveId) {
        try {
            etudiantDAO.delete(eleveId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Classe getByIdClasse(int classeId) {

    return classeDAO.getById(classeId);
    }

    public void deleteClasse(int classeId) {
        try {
            classeDAO.delete(classeId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Departement getByIdDept(int deptId) {
        try {
           return departementDAO.getById(deptId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteDept(int deptId) {
        try {
            departementDAO.delete(deptId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
