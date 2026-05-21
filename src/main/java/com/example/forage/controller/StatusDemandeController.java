package com.example.forage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.forage.entity.*;
import com.example.forage.service.*;
import com.example.forage.repository.*;


import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/statusdemandes")
public class StatusDemandeController {
    
    @Autowired
    private DemandeRepository demandeService;
    
   @Autowired
   private StatusRepository statusService;

   @Autowired
    private StatusDemandeRepository statusDemandeRepository;
    
    // @GetMapping
    // public String listerStatusDemandes(Model model) {
    //     List<Demande> demandes = demandeService.getAll();
    //     model.addAttribute("demandes", demandes);
    //     return "statusdemandes/list";
    // }

    @GetMapping("/new")
    public String creerStatusDemande(Model model) {
        model.addAttribute("statusDemande", new StatusDemande());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "StatusDemande/form";
    }

    @GetMapping("/{idDemande}/{idStatus}")
    public StatusDemande voirStatusDemande(@PathVariable Long idDemande, @PathVariable Long idStatus, Model model) {
        return statusDemandeRepository.findByIdDemandeAndIdStatus(idDemande, idStatus).get(0);
    }
}
