package com.example.forage.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parametres")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParametre")
    private Long idParametre;

    @ManyToOne
    @JoinColumn(name = "idStatus1", nullable = false)
    private Status status1;

    @ManyToOne
    @JoinColumn(name = "idStatus2", nullable = false)
    private Status status2;

    @Column(name = "max", nullable = false, precision = 10, scale = 2)
    private BigDecimal max;

    @Column(name = "couleur", nullable = false, length = 50)
    private String couleur;

    // Constructeurs
    public Parametre() {}

    public Parametre(Status status1, Status status2, BigDecimal max, String couleur) {
        this.status1 = status1;
        this.status2 = status2;
        this.max = max;
        this.couleur = couleur;
    }

    // Getters et Setters
    public Long getIdParametre() { return idParametre; }
    public void setIdParametre(Long idParametre) { this.idParametre = idParametre; }

    public Status getStatus1() { return status1; }
    public void setStatus1(Status status1) { this.status1 = status1; }

    public Status getStatus2() { return status2; }
    public void setStatus2(Status status2) { this.status2 = status2; }

    public BigDecimal getMax() { return max; }
    public void setMax(BigDecimal max) { this.max = max; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }
}
