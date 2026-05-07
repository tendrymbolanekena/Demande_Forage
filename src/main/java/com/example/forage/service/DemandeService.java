package com.example.forage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.entity.Demande;
import com.example.forage.entity.Status;
import com.example.forage.entity.StatusDemande;
import com.example.forage.repository.DemandeRepository;


@Service
@Transactional
public class DemandeService {
    
    @Autowired
    private DemandeRepository demandeRepository;
    
    @Autowired
    private StatusDemandeService statusDemandeService;

    
    public Demande creerDemande(Demande demande) {
        if (demandeRepository.existsByReference(demande.getReference())) {
            throw new IllegalArgumentException("Une demande avec cette référence existe déjà: " + demande.getReference());
        }

        Status st = new Status();
        st.setIdStatus(1L);
        StatusDemande statusInitial = new StatusDemande(demande, st, demande.getDate());
        demande.setStatusDemandes(List.of(statusInitial));
        
        demandeRepository.save(demande);
        statusDemandeService.ajouterStatusAuDemande(statusInitial);
        return null;
    }

    public List<Demande> getAll() {
        return demandeRepository.findAll();
    }

    public Demande getById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public void supprimerDemande(Long id) {
        demandeRepository.deleteById(id);
    }
       




}
