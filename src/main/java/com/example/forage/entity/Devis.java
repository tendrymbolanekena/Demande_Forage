package com.example.forage.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevis;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    private LocalDateTime dateDevis;

    @ManyToOne
    @JoinColumn(name = "idDemande", nullable = false)
    private Demande demande;

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailDevis> detailDevis;

    // Constructeurs
    public Devis() {
    }

    // Getters et Setters

    public Long getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(LocalDateTime dateDevis) {
        this.dateDevis = dateDevis;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public List<DetailDevis> getDetailDevis() {
        return detailDevis;
    }

    public void setDetailDevis(List<DetailDevis> detailDevis) {
        this.detailDevis = detailDevis;
    }
}