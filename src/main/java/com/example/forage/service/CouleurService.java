package com.example.forage.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.forage.repository.CouleurRepository;
import com.example.forage.entity.Couleur;
import java.util.List;

@Service
@Transactional
public class CouleurService {

    @Autowired
    private CouleurRepository couleurRepository;

    public Couleur getCouleurByIntervale(double nbJours) {
        List<Couleur> couleurs = couleurRepository.findAll();
        for (Couleur couleur : couleurs) {
            if (nbJours >= couleur.getIntervalle1() && nbJours <= couleur.getIntervalle2()) {
                return couleur;
            }
        }
        return null;
    }
  
}