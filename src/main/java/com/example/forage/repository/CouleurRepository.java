package com.example.forage.repository;

import com.example.forage.entity.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Long> {

    Optional<Couleur> findByNom(String nom);
    
 
    boolean existsByNom(String nom);
}
