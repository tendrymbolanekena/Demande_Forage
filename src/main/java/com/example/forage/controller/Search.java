package com.example.forage.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.forage.entity.Commune;
import com.example.forage.service.CommuneService;



@RestController
@RequestMapping("/search")
public class Search {
    
    @Autowired
    CommuneService communeService;

    @GetMapping("/communes")
    public List<Commune> searchCommunes(@RequestParam("nom") String nom) {
        return communeService.searchCommunesByName(nom);
    }
}