DROP DATABASE IF EXISTS forage;

CREATE DATABASE IF NOT EXISTS forage;
USE forage;

CREATE TABLE IF NOT EXISTS Region (
    idRegion BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS District (
    idDistrict BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) UNIQUE NOT NULL,
    idRegion BIGINT NOT NULL,
    FOREIGN KEY (idRegion) REFERENCES Region(idRegion) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Commune (
    idCommune BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) UNIQUE NOT NULL,
    idDistrict BIGINT NOT NULL,
    FOREIGN KEY (idDistrict) REFERENCES District(idDistrict) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Users (
    idUser BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Demande (
    idDemande BIGINT PRIMARY KEY AUTO_INCREMENT,
    reference VARCHAR(255) UNIQUE NOT NULL,
    lieu VARCHAR(255),
    idUser BIGINT,
    date DATETIME,
    idCommune BIGINT,
    CONSTRAINT uk_demande_reference UNIQUE (reference),
    FOREIGN KEY (idCommune) REFERENCES Commune(idCommune) ON DELETE SET NULL,
    FOREIGN KEY (idUser) REFERENCES Users(idUser) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Status (
    idStatus BIGINT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Status_Demande (
    idStatusDemande BIGINT PRIMARY KEY AUTO_INCREMENT,
    idDemande BIGINT NOT NULL,
    idStatus BIGINT NOT NULL,
    dateStatus DATETIME,
    FOREIGN KEY (idDemande) REFERENCES Demande(idDemande) ON DELETE CASCADE,
    FOREIGN KEY (idStatus) REFERENCES Status(idStatus) ON DELETE CASCADE
);



INSERT INTO Users (username, password, role) VALUES 
('admin', 'admin123', 'Admin'),
('rakoto', 'rakoto123', 'User'),
('rasoa', 'rasoa123', 'User'),
('hery', 'hery123', 'User');



INSERT INTO Region (nom) VALUES 
('Analamanga'),
('Vakinankaratra'),
('Atsinanana'),
('Boeny'),
('Haute Matsiatra'),
('Alaotra Mangoro'),
('Diana'),
('Anosy');



INSERT INTO District (nom, idRegion) VALUES 
('Antananarivo Atsimondrano', 1),
('Antananarivo Avaradrano', 1),
('Antsirabe I', 2),
('Antsirabe II', 2),
('Toamasina I', 3),
('Mahajanga I', 4),
('Fianarantsoa I', 5),
('Ambatondrazaka', 6),
('Antsiranana II', 7),
('Taolagnaro', 8);



INSERT INTO Commune (nom, idDistrict) VALUES 
('Antananarivo', 1),
('Ambohidratrimo', 2),
('Sabotsy Namehana', 2),
('Antsirabe', 3),
('Betafo', 4),
('Toamasina', 5),
('Mahajanga', 6),
('Fianarantsoa', 7),
('Ambatondrazaka', 8),
('Antsiranana', 9),
('Taolagnaro', 10),
('Ivato', 1),
('Talatamaty', 1),
('Alakamisy Fenoarivo', 2),
('Miarinarivo', 2),
('Moramanga', 6),
('Manakara', 5),
('Nosy Be', 7);



INSERT INTO Status (libelle) VALUES 
('En attente'),
('Approuvée'),
('Rejetée'),
('En cours'),
('Complétée');



INSERT INTO Demande (reference, lieu, idUser, date, idCommune) VALUES 
('DMD-001', 'Antananarivo', 1, NOW(), 1),
('DMD-002', 'Antsirabe', 2, NOW(), 4),
('DMD-003', 'Toamasina', 3, NOW(), 6),
('DMD-004', 'Mahajanga', 4, NOW(), 7),
('DMD-005', 'Fianarantsoa', 2, NOW(), 8);



INSERT INTO Status_Demande (idDemande, idStatus, dateStatus) VALUES 
(1, 1, NOW()),
(2, 2, NOW()),
(3, 4, NOW()),
(4, 1, NOW()),
(5, 5, NOW());