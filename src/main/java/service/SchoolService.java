package service;

import daoImpl.DepartementDAOimpl;
import daoImpl.EnseignantDAOimpl;
import daoImpl.EtudiantDAOimpl;
import daoImpl.MatiereDAOimpl;
import entities.Departement;
import entities.Enseignant;
import entities.Etudiant;
import entities.Matiere;

import java.util.Date;

public class SchoolService {

    private DepartementDAOimpl departementDAO;
    private EnseignantDAOimpl enseignantDAO;

    private EtudiantDAOimpl etudiantDAO;

    private MatiereDAOimpl matiereDAO;

    public SchoolService(DepartementDAOimpl departementDAO, EnseignantDAOimpl enseignantDAO, EtudiantDAOimpl etudiantDAO, MatiereDAOimpl matiereDAO) {
        this.departementDAO = departementDAO;
        this.enseignantDAO = enseignantDAO;
        this.etudiantDAO = etudiantDAO;
        this.matiereDAO = matiereDAO;
    }

    public void createdep(String nom) {
        try {
            Departement newDepartement = new Departement();
            newDepartement.setNom(nom);
            departementDAO.add(newDepartement);
            System.out.println("un département a été produit !! ");
        }catch (Exception e){
            System.out.println("erreur dans la production de département !! ");
            e.printStackTrace();
        }

    }

    public Departement getById(int depId) {
        try {
            Departement departement =  departementDAO.getById(depId);
            return departement;
        }catch (Exception e){
            e.printStackTrace();
        }

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
        }catch (Exception e){
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

        }catch (Exception e){
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

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
