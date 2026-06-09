package com.example.forage.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.entity.StatusDemande;
import com.example.forage.repository.StatusDemandeRepository;

@Service
@Transactional
public class StatusDemandeService {

    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    @Autowired
    private CouleurService couleurService;

    public double calculerNbJoursEntreDates(LocalDateTime dateDebut, LocalDateTime dateFin) {

        if (dateDebut == null || dateFin == null || !dateDebut.isBefore(dateFin)) {
            return 0;
        }

        LocalTime heureDebutTravail = LocalTime.of(8, 0);
        LocalTime heureFinTravail = LocalTime.of(16, 0);

        long totalMinutes = 0;

        LocalDate jourCourant = dateDebut.toLocalDate();
        LocalDate dernierJour = dateFin.toLocalDate();

        while (!jourCourant.isAfter(dernierJour)) {

            DayOfWeek dayOfWeek = jourCourant.getDayOfWeek();

           
            if (dayOfWeek != DayOfWeek.SATURDAY
                    && dayOfWeek != DayOfWeek.SUNDAY) {

                LocalDateTime debutJour = LocalDateTime.of(
                        jourCourant,
                        heureDebutTravail);

                LocalDateTime finJour = LocalDateTime.of(
                        jourCourant,
                        heureFinTravail);

                if (jourCourant.equals(dateDebut.toLocalDate())) {
                    debutJour = dateDebut.isAfter(debutJour)
                            ? dateDebut
                            : debutJour;
                }

                if (jourCourant.equals(dateFin.toLocalDate())) {
                    finJour = dateFin.isBefore(finJour)
                            ? dateFin
                            : finJour;
                }

                if (debutJour.isBefore(finJour)) {
                    totalMinutes += Duration
                            .between(debutJour, finJour)
                            .toMinutes();
                }
            }

            jourCourant = jourCourant.plusDays(1);
        }

        return totalMinutes;
    }

    public StatusDemande ajouterStatusAuDemande(StatusDemande statusDemande) {
        return statusDemandeRepository.save(statusDemande);
    }

    public void getStatusDemandesLast(StatusDemande statusDemande) {

        StatusDemande sd = statusDemandeRepository.findLatestByDemande(
                statusDemande.getDemande().getIdDemande(),
                statusDemande.getStatus().getIdStatus());

        if (sd == null
                || sd.getDateStatus() == null
                || statusDemande.getDateStatus() == null) {
            return;
        }

        double nbJours = calculerNbJoursEntreDates(
                sd.getDateStatus(),
                statusDemande.getDateStatus());

        statusDemande.setNbJours(nbJours);
        statusDemande.setCouleur(
                couleurService.getCouleurByIntervale(nbJours));
    }
}