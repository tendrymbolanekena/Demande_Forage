package com.example.forage.repository;

import com.example.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.forage.entity.Devis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    @Query("SELECT d FROM Devis d LEFT JOIN FETCH d.detailDevis WHERE d.demande.reference = :reference")
    Optional<Devis> findByDemandeReference(@Param("reference") String reference);
}