drop database school;

CREATE DATABASE school;
USE school;

CREATE TABLE departement (
    departement_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255)
);

CREATE TABLE emploi_du_tps (
    emploi_du_tps_id INT AUTO_INCREMENT PRIMARY KEY,
    jourEtHeure DATETIME
);

CREATE TABLE matiere (
    id INT AUTO_INCREMENT PRIMARY KEY,
    intitule VARCHAR(255),
    description TEXT,
    dureeMin INT,
    coeff INT
);

CREATE TABLE enseignant (
    enseignant_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    age INT,
    grade VARCHAR(255),
    profPrincipal BOOLEAN,
    isResponsable BOOLEAN,
    departement_id INT,
    FOREIGN KEY (departement_id) REFERENCES departement(departement_id)
);

CREATE TABLE etudiant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    dateNaissance DATE,
    email VARCHAR(255)
);
CREATE TABLE classe (
    classe_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    niveau VARCHAR(255),
    departement_id INT,
    emploi_du_tps_id INT,
    enseignant_id INT,
    FOREIGN KEY (departement_id) REFERENCES departement(departement_id),
    FOREIGN KEY (emploi_du_tps_id) REFERENCES emploi_du_tps(emploi_du_tps_id),
    FOREIGN KEY (enseignant_id) REFERENCES enseignant(enseignant_id)
);

CREATE TABLE note (
    note_id INT AUTO_INCREMENT PRIMARY KEY,
    commentaire TEXT,
    valeur INT,
    matiere_id INT,
    etudiant_id INT,
    FOREIGN KEY (matiere_id) REFERENCES matiere(id),
    FOREIGN KEY (etudiant_id) REFERENCES etudiant(id)
);
CREATE TABLE enseignant_matiere (
    enseignant_id INT,
    matiere_id INT,
    PRIMARY KEY (enseignant_id, matiere_id),
    FOREIGN KEY (enseignant_id) REFERENCES enseignant(enseignant_id),
    FOREIGN KEY (matiere_id) REFERENCES matiere(id)
);


-- Créer un département
-- INSERT INTO departement (nom) VALUES ('Beta');
-- select * from departement;

-- Créer un enseignant
-- INSERT INTO enseignant (nom, prenom, age, grade, prof_principal, departement_id) VALUES ('Test', 'Bob', 45, 'A', false,  1),('Stilinski', 'Bill', 45, 'B',true, null) ;


-- Créer un étudiant
-- INSERT INTO etudiant (nom, prenom, date_naissance, email, classe_id) VALUES ('Mouse', 'Minnie', '2004-05-20', 'minnie@gmail.com', 1);

-- Créer une matière
-- INSERT INTO matiere (intitule, description, duree_min, coeff) VALUES ('Info', 'Description', 45, 4);

-- Créer une note
-- INSERT INTO note (commentaire, valeur, matiere_id, etudiant_id) VALUES ('cool', 16, 1, 1);

-- Créer une classe
-- INSERT INTO classe (nom, niveau, departement_id) VALUES ('Terminale S', 'Lycée', 1);

-- Créer un emploi du temps
-- INSERT INTO emploi_du_tps (matiere_id, classe_id, jour_et_heure) VALUES (1, 1, '2024-01-20 08:30:00');

-- Afficher la liste des classes (sans les élèves)
-- SELECT * FROM classe;

-- Afficher le nombre de matière d'un élève
-- SELECT COUNT(matiere_id) FROM emploi_du_tps WHERE classe_id = (SELECT classe_id FROM etudiants WHERE id = 1);

-- Afficher la liste des notes d'un élève (avec les détails)
-- SELECT * FROM note WHERE etudiant_id = 1;


-- Afficher la moyenne d'un élève
-- SELECT AVG(valeur) FROM note WHERE etudiant_id = 1;

-- Afficher le nombre d'élèves d'un département
-- SELECT COUNT(*) FROM etudiants WHERE classe_id IN (SELECT id FROM classe WHERE departement_id = 1);

-- Afficher tous les noms des élèves d'un niveau
-- SELECT nom, prenom FROM etudiants WHERE classe_id IN (SELECT id FROM classe WHERE niveau = 'Lycée');

-- Suppression d'un élève (supprime ses notes mais pas sa classe)
-- DELETE FROM note WHERE etudiant_id = 1;
-- DELETE FROM etudiants WHERE id = 1;


-- Suppression classe (supprime uniquement les élèves de cette classe)
-- DELETE FROM etudiants WHERE classe_id = 1;
-- DELETE FROM classe WHERE id = 1;


-- Suppression d'un département (supprime toutes les classes et tous les professeurs)
-- DELETE FROM enseignant WHERE departement_id = 1;
-- DELETE FROM classe WHERE departement_id = 1;
-- DELETE FROM departement WHERE id = 1;
























