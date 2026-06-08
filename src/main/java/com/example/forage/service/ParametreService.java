package com.example.forage.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.forage.repository.*;
import com.example.forage.entity.*;
import java.util.List;
import java.math.BigDecimal;

@Service
@Transactional
public class ParametreService {

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private AlerteRepository alerteRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    public void verifierEtCreerAlertes(StatusDemande statusDemande) {
        if (statusDemande == null || statusDemande.getStatus() == null) {
            return;
        }

        Long idStatusDemande = statusDemande.getStatus().getIdStatus();
        List<Long> statusIds = statusDemandeRepository.findIdsByDemande(statusDemande.getDemande().getIdDemande());
        
     

        for (Long statusId : statusIds) {
            
            double nbJours = statusDemandeRepository.sumNbJours(
            statusDemande.getDemande().getIdDemande(), 
            statusId, 
            idStatusDemande, 
            statusDemande.getDateStatus().toLocalDate().toString()
            );

            if (statusId < idStatusDemande) {
                List<Parametre> parametres = parametreRepository.findByStatus1IdStatusOrStatus2IdStatus(
                    statusId, 
                    idStatusDemande
                );
                Alerte alerte = new Alerte();
                for (Parametre parametre : parametres) {
                    if (nbJours >= parametre.getMax().doubleValue()) {
                        
                        alerte.setDemande(statusDemande.getDemande());
                        alerte.setStatusDemande(statusDemande);
                        alerte.setDateAlerte(statusDemande.getDateStatus());
                        alerte.setParametre(parametre);
                    }
                }

                alerteRepository.save(alerte);
            }
        }
    }

    public Parametre getParametreByIntervale(double nbJours) {
        Parametre selected = null;

        for (Parametre parametre : parametreRepository.findAll()) {
            if (parametre.getMax() != null && nbJours <= parametre.getMax().doubleValue()) {
                if (selected == null || parametre.getMax().doubleValue() < selected.getMax().doubleValue()) {
                    selected = parametre;
                }
            }
        }

        return selected;
    }

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }

    public Parametre getParametreById(Long id) {
        return parametreRepository.findById(id).orElse(null);
    }

    public Parametre saveParametre(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    public void deleteParametre(Long id) {
        parametreRepository.deleteById(id);
    }
}