package com.example.forage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.entity.StatusDemande;
import com.example.forage.repository.StatusDemandeRepository;
// import java.util.List;
// import java.util.Optional;

@Service
@Transactional
public class StatusDemandeService {
    
    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    public StatusDemande ajouterStatusAuDemande(StatusDemande statusDemande) {
        return statusDemandeRepository.save(statusDemande);
        
    }


}
