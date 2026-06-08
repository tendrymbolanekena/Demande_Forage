package com.example.forage.dto;

import com.example.forage.entity.Demande;
import com.example.forage.entity.StatusDemande;
import com.example.forage.entity.Devis;
import com.example.forage.entity.DetailDevis;
import com.example.forage.entity.Alerte;
import java.util.List;

public class DemandeCompletDTO {
    private Demande demande;
    private List<StatusDemande> historiquesStatuts;
    private List<Alerte> alertes;
    private DevisInfoDTO devis;

    public DemandeCompletDTO(Demande demande, List<StatusDemande> historiquesStatuts, List<Alerte> alertes, DevisInfoDTO devis) {
        this.demande = demande;
        this.historiquesStatuts = historiquesStatuts;
        this.alertes = alertes;
        this.devis = devis;
    }

    // Getters et Setters
    public Demande getDemande() { return demande; }
    public List<StatusDemande> getHistoriquesStatuts() { return historiquesStatuts; }
    public List<Alerte> getAlertes() { return alertes; }
    public DevisInfoDTO getDevis() { return devis; }

    // Sous-DTO pour nettoyer le Devis et ses détails du lien circulaire vers "demande"
    public static class DevisInfoDTO {
        private Long idDevis;
        private String reference;
        private java.math.BigDecimal montant;
        private java.time.LocalDateTime dateDevis;
        private String status;
        private List<DetailDevisDTO> details;

        public DevisInfoDTO(Devis devis, List<DetailDevisDTO> details) {
            if (devis != null) {
                this.idDevis = devis.getIdDevis();
                this.reference = devis.getReference();
                this.montant = devis.getMontant();
                this.dateDevis = devis.getDateDevis();
                this.status = devis.getStatus() != null ? devis.getStatus().getLabel() : null;
                this.details = details;
            }
        }
        public Long getIdDevis() { return idDevis; }
        public String getReference() { return reference; }
        public java.math.BigDecimal getMontant() { return montant; }
        public java.time.LocalDateTime getDateDevis() { return dateDevis; }
        public String getStatus() { return status; }
        public List<DetailDevisDTO> getDetails() { return details; }
    }

    // Sous-DTO pour nettoyer les détails du devis du lien circulaire vers "devis"
    public static class DetailDevisDTO {
        private Long idDetailDevis;
        private String description;
        private Integer quantite;
        private java.math.BigDecimal prixUnitaire;
        private java.math.BigDecimal montant;

        public DetailDevisDTO(DetailDevis detail) {
            this.idDetailDevis = detail.getIdDetailDevis();
            this.description = detail.getDescription();
            this.quantite = detail.getQuantite();
            this.prixUnitaire = detail.getPrixUnitaire();
            this.montant = detail.getMontant();
        }
        public Long getIdDetailDevis() { return idDetailDevis; }
        public String getDescription() { return description; }
        public Integer getQuantite() { return quantite; }
        public java.math.BigDecimal getPrixUnitaire() { return prixUnitaire; }
        public java.math.BigDecimal getMontant() { return montant; }
    }
}
