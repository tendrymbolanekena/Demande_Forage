package com.example.forage.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Status_Demande")
public class StatusDemande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatusDemande;

    @ManyToOne
    @JoinColumn(name = "idDemande")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;

    private LocalDateTime dateStatus;

    @Column(name = "observations", columnDefinition = "TEXT DEFAULT 'MANAONA TSY MISY OBSERVATION'")
    private String observations;

    public StatusDemande() {}

    public StatusDemande(Demande demande, Status status, LocalDateTime dateStatus) {
        this.demande = demande;
        this.status = status;
        this.dateStatus = dateStatus;
    }

    public void setIdStatusDemande(Long idStatusDemande) {
        this.idStatusDemande = idStatusDemande;
    }

    public Long getIdStatusDemande() {
        return idStatusDemande;
    }
    
    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Demande getDemande() {
        return demande;
    } 

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setDateStatus(LocalDateTime dateStatus) {
        this.dateStatus = dateStatus;
    }

    public LocalDateTime getDateStatus() {
        return dateStatus;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }


}