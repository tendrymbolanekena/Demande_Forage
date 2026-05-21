package com.example.forage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forage.entity.Demande;
import com.example.forage.entity.StatusDemande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande, Long> {

    List<StatusDemande> findByDemande(Demande demande);

    @Query("SELECT s FROM StatusDemande s WHERE s.idDemande = :demande AND s.idStatus = :status LIMIT 1")
    StatusDemande findStatusesByDemandeAndStatus(@Param("demande") Long demande, @Param("status") Long status);

}
