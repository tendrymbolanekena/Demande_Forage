package com.example.forage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forage.entity.StatusDemande;
import java.util.List;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande, Long> {

    // Spring Data va inspecter ton entité et générer le bon SQL automatiquement
    List<StatusDemande> findByIdDemandeAndIdStatus(Long idDemande, Long idStatus);
}