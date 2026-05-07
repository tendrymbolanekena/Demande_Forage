package com.example.forage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.forage.entity.Commune;
import com.example.forage.repository.CommuneRepository;

@Service
@Transactional
public class CommuneService {

    @Autowired
    private CommuneRepository communeRepository;

    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }

    public List<Commune> searchCommunesByName(String nom) {
        return communeRepository.findByNomContaining(nom);
    }

    public List<Commune> getCommunesByDistrict(Long idDistrict) {
        return communeRepository.findByDistrictIdDistrict(idDistrict);
    }
}