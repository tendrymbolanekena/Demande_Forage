package com.example.forage.repository;

import com.example.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DetailDevisRepository extends JpaRepository<DetailDevis, Long> {

    @Query("SELECT d FROM DetailDevis d WHERE d.devis.idDevis = :id")
    List<DetailDevis> findAllByIdDevis(@Param("id") Long id);
}   
