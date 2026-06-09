package com.example.forage.controller.api;

import com.example.forage.dto.DemandeCompletDTO;
import com.example.forage.entity.Demande;
import com.example.forage.entity.StatusDemande;
import com.example.forage.entity.Devis;
import com.example.forage.entity.Alerte;
import com.example.forage.repository.DemandeRepository;
import com.example.forage.repository.StatusDemandeRepository;
import com.example.forage.repository.AlerteRepository;
import com.example.forage.repository.DevisRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consultation")
public class DemandeRechercheApiController {

    private final DemandeRepository demandeRepository;
    private final StatusDemandeRepository statusDemandeRepository;
    private final AlerteRepository alerteRepository;
    private final DevisRepository devisRepository;

    public DemandeRechercheApiController(DemandeRepository demandeRepository,
                                         StatusDemandeRepository statusDemandeRepository,
                                         AlerteRepository alerteRepository,
                                         DevisRepository devisRepository) {
        this.demandeRepository = demandeRepository;
        this.statusDemandeRepository = statusDemandeRepository;
        this.alerteRepository = alerteRepository;
        this.devisRepository = devisRepository;
    }

    // URL d'appel : GET http://localhost:8080/forage/api/consultation?reference=REF-XYZ
    @GetMapping
    public ResponseEntity<DemandeCompletDTO> getFullDetailsByReference(@RequestParam("reference") String reference) {
        
        return demandeRepository.findByReference(reference)
                .map(demande -> {
                    Long idDemande = demande.getIdDemande();

                    // 1. Récupérer l'historique des statuts
                    List<StatusDemande> statuts = statusDemandeRepository.findByDemandeIdDemande(idDemande);

                    // 2. Récupérer les alertes de temps
                    List<Alerte> alertes = alerteRepository.findByDemandeIdDemande(idDemande);

                    // 3. Récupérer le devis et mapper ses détails en DTO pour casser la boucle infinie
DemandeCompletDTO.DevisInfoDTO devisDTO = devisRepository.findByDemandeReference(reference).map(devis -> {
                                List<DemandeCompletDTO.DetailDevisDTO> detailsDTO = devis.getDetailDevis().stream()
                                        .map(DemandeCompletDTO.DetailDevisDTO::new)
                                        .collect(Collectors.toList());
                                return new DemandeCompletDTO.DevisInfoDTO(devis, detailsDTO);
                            }).orElse(null); // Retourne null si aucun devis n'est encore associé

                    // 4. Assembler et renvoyer la réponse propre
                    return ResponseEntity.ok(new DemandeCompletDTO(demande, statuts, alertes, devisDTO));
                })
                .orElse(ResponseEntity.notFound().build()); // 404 si la référence est introuvable
    }
}
