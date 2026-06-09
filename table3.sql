
CREATE TABLE IF NOT EXISTS couleur (
    idCouleur BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) UNIQUE NOT NULL,
    INTERVALLE1 DOUBLE NOT NULL,
    INTERVALLE2 DOUBLE NOT NULL
);

ALTER TABLE Status_Demande ADD COLUMN nbJours DOUBLE DEFAULT 0;
ALTER TABLE Status_Demande ADD COLUMN idCouleur BIGINT;
ALTER TABLE Status_Demande ADD FOREIGN KEY (idCouleur) REFERENCES couleur(idCouleur) ON DELETE SET NULL;

INSERT INTO couleur (nom, INTERVALLE1, INTERVALLE2) VALUES
('Dark Green', 0.00, 10.00),
('Green', 10.01, 20.00),
('Light Green', 20.01, 30.00),
('Yellow Green', 30.01, 40.00),
('Yellow', 40.01, 50.00),
('Gold', 50.01, 60.00),
('Orange', 60.01, 70.00),
('Dark Orange', 70.01, 80.00),
('Orange Red', 80.01, 90.00),
('Red', 90.01, 100.00);