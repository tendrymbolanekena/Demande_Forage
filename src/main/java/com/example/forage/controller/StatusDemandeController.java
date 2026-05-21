package com.example.forage.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.forage.entity.*;
import com.example.forage.repository.*;
import com.example.forage.service.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/statusdemandes")
public class StatusDemandeController {
    
    @Autowired
    private DemandeRepository demandeService;
    
    @Autowired
    private StatusRepository statusService;

    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    @GetMapping("/new")
    public String creerStatusDemande(Model model) {
        model.addAttribute("statusDemande", new StatusDemande());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "StatusDemande/form";
    }

    @GetMapping("/{idDemande}/{idStatus}")
    @ResponseBody 
    public org.springframework.http.ResponseEntity<java.util.Map<String, Object>> voirStatusDemande(@PathVariable("idDemande") Long idDemande, @PathVariable("idStatus") Long idStatus) {
        List<Long> idsExistants = statusDemandeRepository.findIdsByDemandeAndStatus(idDemande, idStatus);
        
        if(idsExistants.isEmpty()) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }
        StatusDemande sd = statusDemandeRepository.findById(idsExistants.get(0)).orElse(null);
        if (sd == null) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }

        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("idStatusDemande", sd.getIdStatusDemande());
        response.put("idDemande", sd.getDemande() != null ? sd.getDemande().getIdDemande() : null);
        response.put("idStatus", sd.getStatus() != null ? sd.getStatus().getIdStatus() : null);
        response.put("statusLibelle", sd.getStatus() != null ? sd.getStatus().getLibelle() : null);
        response.put("dateStatus", sd.getDateStatus() != null ? sd.getDateStatus().toString() : null);
        response.put("observations", sd.getObservations());
        return org.springframework.http.ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public String saveStatusDemande(@RequestParam("idStatusDemande") Long idStatusDemande,
                                    @RequestParam("idDemande") Long idDemande,
                                    @RequestParam("idStatus") Long idStatus,
                                    @RequestParam("dateStatus") String dateStatus,
                                    @RequestParam("observations") String observations) {
        StatusDemande statusDemande = new StatusDemande();
        statusDemande.setIdStatusDemande(idStatusDemande);
        statusDemande.setDemande(demandeService.findById(idDemande).orElse(null));
        statusDemande.setStatus(statusService.findById(idStatus).orElse(null));
        statusDemande.setDateStatus(LocalDateTime.parse(dateStatus));
        statusDemande.setObservations(observations);
        statusDemandeRepository.save(statusDemande);
        return "redirect:/statusdemandes/new";
    }
}