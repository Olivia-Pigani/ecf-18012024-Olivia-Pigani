package controller;

import daoImpl.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


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


    public IHM() {
    }



    /// à voir

    public void printMenu(){
        choice = scanner.nextInt();
        while (run){
            System.out.println("===Menu===");
            System.out.println("1. Create ");
            System.out.println("2. Read ");
            System.out.println("3. Delete ");
            System.out.println("4. Close App");

            switch (choice){
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
        while (run){

            System.out.println("You want to read ?");
            System.out.println("1. Afficher la liste des classes (sans les eleves)");
            System.out.println("2. Afficher le nombre de matière d'un élève");
            System.out.println("3. Afficher la liste des notes d'un eleve (avec les détails).");
            System.out.println("4. Afficher la moyenne d'un eleve");
            System.out.println("5. Afficher le nombre d'eleve d'un département.");
            System.out.println("6. Afficher tous les noms des eleves d'un niveau.");
            
            
            switch (choice){
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
                    run=false;
            }
        }
    }

    private void DeleteMenu() {
        choice = scanner.nextInt();
        while (run){

            System.out.println("You want to delete ?");
            System.out.println("1. un eleve");
            System.out.println("2. une classe");
            System.out.println("3. un département");
            
            switch (choice){
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
                    run=false;
            }
            
        }
    }

    private void createMenu() {
        choice = scanner.nextInt();
        while (run){
            System.out.println("You want to create ? ");
            System.out.println("1. département ");
            System.out.println("2. enseignant ");
            System.out.println("3. etudiant ");
            System.out.println("4. matiere");
            System.out.println("5. note");
            System.out.println("6. classe");
            System.out.println("7. emploi du temps");
            System.out.println("8. back ");

            switch (choice){
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
                    cretaeEDT();
                    break;
                case 8:
                    printMenu();
                default:
                    System.out.println(" wrong input ");
                    run = false;
            }
            
            
        }



    }
    
    
    


}
