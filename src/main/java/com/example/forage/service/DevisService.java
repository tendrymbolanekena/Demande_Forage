package com.example.forage.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.forage.repository.DevisRepository;
import com.example.forage.entity.Devis;

@Service
@Transactional
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    public Devis saveDevis(Devis devis) {
        return devisRepository.save(devis);
    }
}