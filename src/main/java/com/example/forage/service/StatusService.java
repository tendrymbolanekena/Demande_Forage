package com.example.forage.service;

import com.example.forage.entity.Status;
import com.example.forage.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository;

    public Status creerStatus(Status status) {
        return statusRepository.save(status);
    }
    

    public List<Status> obtenirTousLesStatuts() {
        return statusRepository.findAll();
    }

    public Optional<Status> obtenirStatusParId(Long id) {
        return statusRepository.findById(id);
    }

    public Optional<Status> obtenirStatusParLibelle(String libelle) {
        return statusRepository.findByLibelle(libelle);
    }
    

    public Status mettreAJourStatus(Long id, Status statusUpdated) {
        return statusRepository.findById(id).map(status -> {
            status.setLibelle(statusUpdated.getLibelle());
            return statusRepository.save(status);
        }).orElseThrow(() -> new IllegalArgumentException("Statut non trouvé avec l'ID: " + id));
    }
    

    public void supprimerStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
