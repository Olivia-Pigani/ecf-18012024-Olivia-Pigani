
-- -- create database school;
-- -- use school;


-- create table if not exists enseignant(
-- id int primary key not null auto_increment,
-- nom varchar(50),
-- prenom varchar(50),
-- age int not null,
-- grade varchar(10) not null,
-- prof_principal boolean not null default false
-- );

-- create table if not exists departement(
-- id int primary key not null auto_increment,
-- nom varchar(50),
--     responsable_id int,

-- foreign key (responsable_id) references enseignant(id) -- pour le responsable du département

-- );

-- --  ALTER TABLE enseignant ADD departement_id INT;
-- --  ALTER TABLE enseignant ADD FOREIGN KEY (departement_id) REFERENCES departement(id);

-- --------------------------------------------------------------------------------------------

-- create table if not exists matiere(
-- id int primary key not null auto_increment,
-- intitule varchar(50) not null,
-- description varchar(1000),
-- duree_min int not null,
-- coeff int not null
-- );

-- create table if not exists enseignant_matiere(
--  enseignant_id int,
--  matiere_id int,
--   foreign key (enseignant_id) references enseignant(id),
--     foreign key (matiere_id) references matiere(id),
--     primary key (enseignant_id, matiere_id)
-- );

-- create table if not exists classe(
-- id int primary key not null auto_increment,
-- nom varchar(50),
-- niveau varchar(20),
--     departement_id int,
--     foreign key (departement_id) references departement(id)

-- );

-- create table if not exists emploi_du_tps(
-- matiere_id int,
-- classe_id int,
--     foreign key (matiere_id) references matiere(id),
--     foreign key (classe_id) references classe(id),
-- jour_et_heure datetime not null,
-- primary key (matiere_id, classe_id, jour_et_heure)

-- );


-- create table if not exists etudiant(
-- id int primary key not null auto_increment,
-- nom varchar(50),
-- prenom varchar(50),
-- date_naissance Date not null,
-- email varchar(150) not null,
--  classe_id INT,
--     FOREIGN KEY (classe_id) REFERENCES classe(id)


-- );

-- create table if not exists note (
-- id int primary key not null auto_increment,
-- commentaire varchar(100),
-- valeur int not null,
-- matiere_id int,
-- etudiant_id int,
-- foreign key (matiere_id) references matiere(id),
-- foreign key (etudiant_id) references etudiant(id)
-- );


-- Créer un département
-- INSERT INTO departement (nom) VALUES ('Beta');
-- select * from departement;

-- Créer un enseignant
-- INSERT INTO enseignant (nom, prenom, age, grade, prof_principal, departement_id) VALUES ('Test', 'Bob', 45, 'A', false,  1),('Stilinski', 'Bill', 45, 'B',true, null) ;


-- Créer un étudiant
-- INSERT INTO etudiants (nom, prenom, date_naissance, email, classe_id) VALUES ('Mouse', 'Minnie', '2004-05-20', 'minnie@gmail.com', 1);

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
























