package com.example.forage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.forage.entity.StatusDemande;
import java.util.List;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande, Long> {

    @Query(value = "SELECT idStatusDemande FROM Status_Demande WHERE idDemande = :demande AND idStatus = :status", nativeQuery = true)
    List<Long> findIdsByDemandeAndStatus(@Param("demande") Long demande, @Param("status") Long status);

    @Query(value = "SELECT * FROM Status_Demande WHERE idDemande = :demande AND idStatus != :status ORDER BY dateStatus DESC LIMIT 1", nativeQuery = true)
    StatusDemande findLatestByDemande(@Param("demande") Long demande , @Param("status") Long status);

    @Query(value = "SELECT idStatus FROM Status_Demande WHERE idDemande = :demande ", nativeQuery = true)
    List<Long> findIdsByDemande(@Param("demande") Long demande);

    List<StatusDemande> findByDemandeIdDemande(Long idDemande);

    @Query(value = "SELECT SUM(nbJours) total FROM Status_Demande WHERE idDemande = :demande AND idStatus >= :status1 AND idStatus <= :status2 AND dateStatus <= :date", nativeQuery = true)
    Double sumNbJours(@Param("demande") Long demande, @Param("status1") Long status1, @Param("status2") Long status2, @Param("date") String date);
}