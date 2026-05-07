package com.example.forage.repository;

import com.example.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    Optional<Demande> findByReference(String reference);
    
 
    boolean existsByReference(String reference);
}
