package com.example.forage.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name = "couleur")
public class Couleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCouleur;
    private String nom;
    private double intervalle1;
    private double intervalle2;

    @OneToMany(mappedBy = "couleur")
    private List<StatusDemande> statusDemandes;

    public Couleur() {}

    public Couleur(String nom, double intervalle1, double intervalle2) {
        this.nom = nom;
        this.intervalle1 = intervalle1;
        this.intervalle2 = intervalle2;
    }

    public Long getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(Long idCouleur) {
        this.idCouleur = idCouleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getIntervalle1() {
        return intervalle1;
    }

    public void setIntervalle1(double intervalle1) {
        this.intervalle1 = intervalle1;
    }

    public double getIntervalle2() {
        return intervalle2;
    }

    public void setIntervalle2(double intervalle2) {
        this.intervalle2 = intervalle2;
    }
}