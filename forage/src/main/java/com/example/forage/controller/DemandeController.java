package com.example.forage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.forage.entity.Demande;
import com.example.forage.entity.Users;
import com.example.forage.entity.Commune;
import com.example.forage.service.DemandeService;
import com.example.forage.service.UsersService;
import com.example.forage.repository.UsersRepository;
import com.example.forage.repository.CommuneRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/demandes")
public class DemandeController {
    
    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CommuneRepository communeRepository;
    
    @GetMapping
    public String listerDemandes(Model model) {
        List<Demande> demandes = demandeService.getAll();
        model.addAttribute("demandes", demandes);
        return "demandes/list";
    }

    @GetMapping("/new")
    public String Affichage(Model model){
        model.addAttribute("demande", new Demande());
        model.addAttribute("users", usersService.getAllUsers());
        return "demandes/form";
    }

    @PostMapping("/creer")
    public String creer(@ModelAttribute Demande demande,
                        @RequestParam(value = "idUser", required = false) Long idUser,
                        @RequestParam(value = "idCommune", required = false) Long idCommune) {
        
        if (idUser != null) {
            Users user = usersRepository.findById(idUser).orElse(null);
            demande.setUser(user);
        }

        if (idCommune != null) {
            Commune commune = communeRepository.findById(idCommune).orElse(null);
            demande.setCommune(commune);
        }

        demandeService.creerDemande(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Demande demande = demandeService.getById(id);
        model.addAttribute("demande", demande);
        model.addAttribute("users", usersService.getAllUsers());
        return "demandes/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        demandeService.supprimerDemande(id);
        return "redirect:/demandes";
    }
}