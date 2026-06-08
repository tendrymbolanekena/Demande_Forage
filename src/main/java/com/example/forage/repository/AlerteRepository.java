package com.example.forage.repository;

import com.example.forage.entity.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {
    
    List<Alerte> findByDemandeIdDemande(Long idDemande);
    
    List<Alerte> findByParametreIdParametre(Long idParametre);
}
