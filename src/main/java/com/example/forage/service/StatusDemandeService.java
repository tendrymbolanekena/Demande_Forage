package com.example.forage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.entity.StatusDemande;
import com.example.forage.repository.StatusDemandeRepository;
import com.example.forage.service.CouleurService;
// import java.util.List;
// import java.util.Optional;
import java.time.*;

@Service
@Transactional
public class StatusDemandeService {
    
    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    @Autowired
    private CouleurService couleurService;


    public double calculerNbJoursEntreDates(LocalDate dateDebut, LocalDate dateFin) {
        double jours = 0;

        while (!dateDebut.isAfter(dateFin)) {
            
            if(dateDebut.getDayOfWeek().getValue() >= 1 && dateDebut.getDayOfWeek().getValue() <= 5) {
                jours++;
            }
            dateDebut = dateDebut.plusDays(1);
        }
        return jours-2 >= 0 ? jours-2 : 0;
    }

    public StatusDemande ajouterStatusAuDemande(StatusDemande statusDemande) {
        return statusDemandeRepository.save(statusDemande);
        
    }

    public void getStatusDemandesLast(StatusDemande statusDemande) {
        StatusDemande sd = statusDemandeRepository.findLatestByDemande(statusDemande.getDemande().getIdDemande() , statusDemande.getStatus().getIdStatus());
        if (sd == null || sd.getDateStatus() == null || statusDemande.getDateStatus() == null) {
            return;
        }

        double nbJours = calculerNbJoursEntreDates(sd.getDateStatus().toLocalDate(),
                                                    statusDemande.getDateStatus().toLocalDate());

        LocalTime lc = LocalTime.of(8,0);

        double heure1 = Duration.between(lc, sd.getDateStatus().toLocalTime()).toHours();
        double heure2 = Duration.between(lc, statusDemande.getDateStatus().toLocalTime()).toHours();
        heure1 = heure1 < 0 ? 0 :(heure1 > 8 ? 8 : heure1);
        heure2 = heure2 < 0 ? 0 : (heure2 > 8 ? 8 : heure2);
        nbJours +=(heure1 + heure2)/8;
        statusDemande.setNbJours(nbJours);
        statusDemande.setCouleur(couleurService.getCouleurByIntervale(nbJours));
        
    }


}
