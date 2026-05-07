package com.example.forage.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemande;

    @Column(unique = true, nullable = false)
    private String reference;

    private String lieu;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = true)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "idCommune", nullable = true)
    private Commune commune;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private List<StatusDemande> statusDemandes;

    public Demande() {
    }

    public Demande(String reference, String lieu, LocalDateTime date , Users user, Commune commune) {
        this.reference = reference;
        this.lieu = lieu;
        this.date = date;
        this.user = user;
        this.commune = commune;
        // this.nom = nom;
    }

    // public void setNom(String nom) {
    //     this.nom = nom;
    // }

  
    public String getNom() {
        return this.user != null ? this.user.getUsername() : null;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public List<StatusDemande> getStatusDemandes() {
        return statusDemandes;
    }

    public void setStatusDemandes(List<StatusDemande> statusDemandes) {
        this.statusDemandes = statusDemandes;
    }

    public Date getDateAsDate() {
        if (this.date == null)
            return null;
        return Date.from(this.date.atZone(ZoneId.systemDefault()).toInstant());
    }
}