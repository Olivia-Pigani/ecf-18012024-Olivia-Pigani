package controller;

import daoImpl.*;
import entities.Departement;
import entities.Enseignant;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import service.SchoolService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IHM {

    private Scanner scanner = new Scanner(System.in);

    private int choice;

    private boolean run = true;

    // import services et dao
    private static ClasseDAOimpl classeDAO;
    private static DepartementDAOimpl departementDAO;
    private static EmploiDTDAOimpl emploiDTDAO;
    private static EnseignantDAOimpl enseignantDAO;
    private static EtudiantDAOimpl etudiantDAO;
    private static MatiereDAOimpl matiereDAO;
    private static NoteDAOimpl noteDAO;

    private static SchoolService schoolService;


    public IHM() {
    }


    /// à voir

    public void printMenu() {
        choice = scanner.nextInt();
        while (run) {
            System.out.println("===Menu===");
            System.out.println("1. Create ");
            System.out.println("2. Read ");
            System.out.println("3. Delete ");
            System.out.println("4. Close App");

            switch (choice) {
                case 1:
                    createMenu();
                    break;
                case 2:
                    ReadMenu();
                    break;
                case 3:
                    DeleteMenu();
                    break;
                case 4:
                    closeAll();
                default:
                    System.out.println("wrong input, retry ! ");
                    run = false;
            }


        }


    }

    private void closeAll() {
    }

    private void ReadMenu() {
        choice = scanner.nextInt();
        while (run) {

            System.out.println("You want to read ?");
            System.out.println("1. Afficher la liste des classes (sans les eleves)");
            System.out.println("2. Afficher le nombre de matière d'un élève");
            System.out.println("3. Afficher la liste des notes d'un eleve (avec les détails).");
            System.out.println("4. Afficher la moyenne d'un eleve");
            System.out.println("5. Afficher le nombre d'eleve d'un département.");
            System.out.println("6. Afficher tous les noms des eleves d'un niveau.");


            switch (choice) {
                case 1:
                    getAllClasses();
                    break;
                case 2:
                    getAllMatierePareleve();
                    break;
                case 3:
                    getStudentScoresDetails();
                    break;
                case 4:
                    studentAvg();
                    break;
                case 5:
                    allStudentNameByLevel();
                    break;
                default:
                    System.out.println("wrong input, retry ! ");
                    run = false;
            }
        }
    }

    private void getAllClasses() {
        schoolService.getAllClassesNoStudent();
    }

    private void DeleteMenu() {
        choice = scanner.nextInt();
        while (run) {

            System.out.println("You want to delete ?");
            System.out.println("1. un eleve");
            System.out.println("2. une classe");
            System.out.println("3. un département");

            switch (choice) {
                case 1:
                    deleteEleve();
                    break;
                case 2:
                    deleteClasse();
                    break;
                case 3:
                    deleteDepartement();
                    break;
                default:
                    System.out.println("wrong input, retry ! ");
                    run = false;
            }

        }
    }

    private void deleteDepartement() {
        try {
            System.out.println("quel est l'id du departement à delete ?");
            int deptId = scanner.nextInt();
            if (schoolService.getByIdDept(deptId) == null ){
                throw new IllegalArgumentException();
            }
            schoolService.deleteDept(deptId);
            System.out.println("success !! ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteClasse() {
        try {
            System.out.println("quel est l'id de la classe à delete ?");
            int classeId = scanner.nextInt();
            if (schoolService.getByIdClasse(classeId) == null){
                throw  new IllegalArgumentException();
            }
            schoolService.deleteClasse(classeId);
            System.out.println("success !! ");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteEleve() {
        try {
            System.out.println("quel est l'id de leleve à delete ?");
            int eleveId = scanner.nextInt();
            scanner.nextLine();
            if (schoolService.getByIdEleve(eleveId) == null){
                throw new IllegalArgumentException();
            }

            schoolService.deleteEleve(eleveId);
            System.out.println("success !");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createMenu() {
        choice = scanner.nextInt();
        while (run) {
            System.out.println("You want to create ? ");
            System.out.println("1. département ");
            System.out.println("2. enseignant ");
            System.out.println("3. etudiant ");
            System.out.println("4. matiere");
            System.out.println("5. note");
            System.out.println("6. classe");
            System.out.println("7. emploi du temps");
            System.out.println("8. back ");

            switch (choice) {
                case 1:
                    createdep();
                    break;
                case 2:
                    createEnseignant();
                    break;
                case 3:
                    createEtudiant();
                    break;
                case 4:
                    createMatiere();
                    break;
                case 5:
                    createNote();
                    break;
                case 6:
                    createClasse();
                    break;
                case 7:
                    createEDT();
                    break;
                case 8:
                    printMenu();
                default:
                    System.out.println(" wrong input ");
                    run = false;
            }


        }


    }

    private void createEDT() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            System.out.println("Quel est le jour et l'heure ? Format => dd-MM-yyyy HH:mm:ss");
            String jourEtHeureStr = scanner.nextLine();
            Date jourEtHeure = dateFormat.parse(jourEtHeureStr);

            schoolService.addEDT(jourEtHeure);





        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void createClasse() {
        try {
            System.out.println("Quel est son nom ?");
            String nom = scanner.nextLine();
            System.out.println("Quel est le niveau ?");
            String niveau = scanner.nextLine();

            schoolService.addClasse(nom,niveau);

        }catch (Exception e){
            System.out.println("something wrong !! ");
            e.printStackTrace();
        }
    }

    private void createNote() {
        try {
            System.out.println("quelle est la valeur ?");
            int score = scanner.nextInt();
            scanner.nextLine();
            System.out.println("quelle est le commentaire ?");
            String com = scanner.nextLine();
            System.out.println("Quelle est l'id de la matière concernée ? ");
            int matiereId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Quelle est l'id de l'eleve concernée ? ");
            int eleveId = scanner.nextInt();
            scanner.nextLine();

            if (schoolService.getByIdMatiere(matiereId) == null || schoolService.getByIdEtudiant(eleveId) == null){
                throw new IllegalArgumentException();
            }

            schoolService.addNote(score,com,matiereId, eleveId);


        }catch (Exception e){
            System.out.println("aucune matiere correspondante et/ou aucun eleve correspondant ! ");
            e.printStackTrace();
        }
    }

    private void createMatiere() {
        try {
            System.out.println("Quelle est l'intitulé ? ");
            String intitulé = scanner.nextLine();
            System.out.println("Quelle est la description? ");
            String description = scanner.nextLine();
            System.out.println("Quelle est la durée en minute ? "); // entre 0 et 60 minutes max
            int dureeMin = scanner.nextInt();
            scanner.nextLine();
            if ((dureeMin <0) || (dureeMin>60)){
                System.out.println("la durée doit etre au delà de 0 et pas au dessus de 60 minutes !! ");
                throw new IllegalArgumentException();
            }
            System.out.println("Quel est le coeff ? ");
            int coeff = scanner.nextInt();
            if (coeff<0 || coeff>5){
                throw new IllegalArgumentException(" le coeff doit etre compris entre 1 et 5 ");
            }

            schoolService.addMatiere(intitulé,description,dureeMin,coeff);
        }catch (Exception e){
            System.out.println("something wrong during ?matière? producing ! ");
            e.printStackTrace();
        }



    }

    private void createEtudiant() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            System.out.println("Quel est son nom ?");
            String nom = scanner.nextLine();
            System.out.println("Quel est son prenom ?");
            String prenom = scanner.nextLine();

            System.out.println("Quel est sa date de naissance ?");
            String birthDateStr = scanner.nextLine();
            Date birthDate = format.parse(birthDateStr);


                System.out.println("Quel est son email ? ce doit etre un email de type 'gmail.com'");
                String email = scanner.nextLine();
                if (!email.contains("gmail.com")) {
                    throw new IllegalArgumentException("not a gmail ! ");
                }



            schoolService.addEtudiant(nom,prenom,birthDate,email);


        }catch (Exception e){
            System.out.println("something wrong during student producing ! ");
            e.printStackTrace();

        }
    }

    private void createEnseignant() {
        try {
            System.out.println("Quel nom à cet enseignant ?");
            String nom = scanner.nextLine();
            System.out.println("Quel prenom à cet enseignant ?");
            String prenom = scanner.nextLine();

            System.out.println("Quel est l'age de cet enseignant ?");
            int age = scanner.nextInt();

            System.out.println("Quel est le grade de cet enseignant ?");
            String grade = scanner.nextLine();

            System.out.println("Est il un prof principal ? oui ou non ");
            String profPxStr = scanner.nextLine().toLowerCase();
            Boolean profPx = false;
            if (profPxStr == "oui") {
                profPx = true;
            }

            System.out.println("est t'il responsable d'un departement ? oui ou non ");
            String responsableStr = scanner.nextLine().toLowerCase();
            Boolean responsable = false;

            if (responsableStr == "oui") {

                System.out.println("quel est l'id de ce departement ? ");
                int depId = scanner.nextInt();
                scanner.nextLine();

                if (schoolService.getById(depId) != null){

                    responsable = true;
                }

            }


            schoolService.createEnseignant(nom,prenom,grade,responsable,profPx,age,responsable);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createdep() {
        try {
            System.out.println("Quel nom à ce département ?");
            String nom = scanner.nextLine();
            schoolService.createdep(nom);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
