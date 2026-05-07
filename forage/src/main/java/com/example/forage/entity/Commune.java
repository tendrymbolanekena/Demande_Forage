package com.example.forage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "Commune")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommune;
    
    private String nom;

    @ManyToOne
    @JoinColumn(name = "idDistrict", nullable = false)
    private District district;

    public Commune() {
    }

    public Commune(String nom) {
        this.nom = nom;
    }

    public Commune(String nom, District district) {
        this.nom = nom;
        this.district = district;
    }

    public Long getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(Long idCommune) {
        this.idCommune = idCommune;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}