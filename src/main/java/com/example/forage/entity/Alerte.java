package com.example.forage.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertes")
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlerte")
    private Long idAlerte;

    @ManyToOne
    @JoinColumn(name = "idDemande", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "idStatusDemande", nullable = false)
    private StatusDemande statusDemande;

    @Column(name = "dateAlerte")
    private LocalDateTime dateAlerte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParametre", nullable = false)
    private Parametre parametre;

    // Constructeurs
    public Alerte() {}

    public Alerte(Demande demande, StatusDemande statusDemande, LocalDateTime dateAlerte, Parametre parametre) {
        this.demande = demande;
        this.statusDemande = statusDemande;
        this.dateAlerte = dateAlerte;
        this.parametre = parametre;
    }

    // Getters et Setters
    public Long getIdAlerte() { return idAlerte; }
    public void setIdAlerte(Long idAlerte) { this.idAlerte = idAlerte; }

    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }

    public StatusDemande getStatusDemande() { return statusDemande; }
    public void setStatusDemande(StatusDemande statusDemande) { this.statusDemande = statusDemande; }

    public LocalDateTime getDateAlerte() { return dateAlerte; }
    public void setDateAlerte(LocalDateTime dateAlerte) { this.dateAlerte = dateAlerte; }

    public Parametre getParametre() { return parametre; }
    public void setParametre(Parametre parametre) { this.parametre = parametre; }
}
