package com.example.forage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.forage.service.DevisService;
import com.example.forage.service.DetailDevisService;
import com.example.forage.repository.*;
import com.example.forage.entity.*;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.TreeMap;
import java.math.BigDecimal;

@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DetailDevisService detailDevisService;

    @Autowired
    private StatusDemandeRepository statusDemandeRepository;

    // @GetMapping
    // public String index(Model model) {
    //     List<Demande> demandes = demandeRepository.findAll();
    //     model.addAttribute("demandes", demandes);
    //     return "devis/devis";
    // }

    @GetMapping("/{id}")
    public String home(@PathVariable("id") Long id, Model model) {
        model.addAttribute("demande", demandeRepository.findById(id).orElse(null));
        return "devis/devis";
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "reference") String reference,
                        @RequestParam(value = "dateDevis") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate dateDevis,
                        @RequestParam(value = "idDemande", required = false) Long idDemande,
                        HttpServletRequest request) {
        
        Map<String, Object> response = new java.util.HashMap<>();
        
        response.put("reference", reference);
        response.put("dateDevis", dateDevis.toString());
        response.put("idDemande", idDemande);
        

        Map<Integer, Map<String, String>> detailsMap = new TreeMap<>();
        Pattern pattern = Pattern.compile("detailDevisList\\[(\\d+)\\]\\.([a-zA-Z]+)");
        
        for (String paramName : request.getParameterMap().keySet()) {
            Matcher matcher = pattern.matcher(paramName);
            if (matcher.find()) {
                int index = Integer.parseInt(matcher.group(1));
                String field = matcher.group(2);
                String value = request.getParameter(paramName);
                
                detailsMap.computeIfAbsent(index, k -> new java.util.HashMap<>()).put(field, value);
            }
        }
        

        List<Map<String, Object>> details = new java.util.ArrayList<>();
        List<DetailDevis> detailsToSave = new java.util.ArrayList<>();
        BigDecimal totalMontant = BigDecimal.ZERO;
        
        for (Map.Entry<Integer, Map<String, String>> entry : detailsMap.entrySet()) {
            Map<String, String> detailData = entry.getValue();
            String description = detailData.get("description");
            
            if (description != null && !description.trim().isEmpty()) {
                int quantite = Integer.parseInt(detailData.get("quantite"));
                BigDecimal prixUnitaire = new BigDecimal(detailData.get("prixUnitaire"));
                BigDecimal montantDetail = prixUnitaire.multiply(new BigDecimal(quantite));
                
                Map<String, Object> detail = new java.util.HashMap<>();
                detail.put("index", entry.getKey());
                detail.put("description", description);
                detail.put("quantite", quantite);
                detail.put("prixUnitaire", prixUnitaire.toString());
                detail.put("montant", montantDetail.toString());
                details.add(detail);
                
                DetailDevis detailDevis = new DetailDevis();
                detailDevis.setDescription(description);
                detailDevis.setQuantite(quantite);
                detailDevis.setPrixUnitaire(prixUnitaire);
                detailDevis.setMontant(montantDetail);
                detailsToSave.add(detailDevis);
                
                totalMontant = totalMontant.add(montantDetail);
            }
        }
        

        try {
            Devis devis = new Devis();
            devis.setReference(reference);
            devis.setDateDevis(dateDevis.atStartOfDay());
            devis.setMontant(totalMontant);
            
            if (idDemande != null && idDemande > 0) {
                Demande demande = demandeRepository.findById(idDemande).orElse(null);
                devis.setDemande(demande);
                devis.setStatus(Devis.StatusDevis.EN_COURS);
                Status status = new Status();
                status.setIdStatus(4L);
                StatusDemande statusDemande = new StatusDemande(demande, status,dateDevis.atStartOfDay());

                statusDemandeRepository.save(statusDemande);

            }
            
            Devis savedDevis = devisService.saveDevis(devis);
            

            for (DetailDevis detail : detailsToSave) {
                detail.setDevis(savedDevis);
                detailDevisService.saveDetailDevis(detail);
            }
            

            
        } catch (Exception e) {
         return "redirect:/devis?error="+e.getMessage();
        }
        return "redirect:/demandes";
     
    }
}