CREATE TABLE parametres(
    idParametre BIGINT PRIMARY KEY AUTO_INCREMENT,
    idStatus1 BIGINT NOT NULL,
    idStatus2 BIGINT NOT NULL,
    max DECIMAL(10, 2) NOT NULL,
    couleur VARCHAR(50) NOT NULL,
    FOREIGN KEY (idStatus1) REFERENCES Status(idStatus) ON DELETE CASCADE,
    FOREIGN KEY (idStatus2) REFERENCES Status(idStatus) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alertes(
    idAlerte BIGINT PRIMARY KEY AUTO_INCREMENT,
    idDemande BIGINT NOT NULL,
    idStatusDemande BIGINT NOT NULL,
    dateAlerte DATETIME,
    idParametre BIGINT NOT NULL,
    FOREIGN KEY (idDemande) REFERENCES Demande(idDemande) ON DELETE CASCADE,
    FOREIGN KEY (idStatusDemande) REFERENCES Status_Demande(idStatusDemande) ON DELETE CASCADE,
    FOREIGN KEY (idParametre) REFERENCES parametres(idParametre) ON DELETE CASCADE

); 

SELECT SUM(nbJours) FROM Status_Demande WHERE idDemande =22 AND idStatus >= 1 AND idStatus <= 4 AND dateStatus < '2026-06-13 00:00:00';
SELECT idStatusDemande FROM Status_Demande WHERE idDemande = 23;