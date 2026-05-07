package com.example.forage.entity;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Table(name = "Status")
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(unique = true, nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<StatusDemande> statusDemandes;

    public Status() {}



    public Status(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
