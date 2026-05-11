package com.example.forage.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detail_devis")
public class DetailDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailDevis;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer quantite;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(insertable = false, updatable = false)
    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "idDevis", nullable = false)
    private Devis devis;

    // Constructeurs
    public DetailDevis() {
    }

  

    public Long getIdDetailDevis() {
        return idDetailDevis;
    }

    public void setIdDetailDevis(Long idDetailDevis) {
        this.idDetailDevis = idDetailDevis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
}