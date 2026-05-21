package com.example.forage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.forage.entity.StatusDemande;
import java.util.List;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande, Long> {

  
    @Query(value = "SELECT * FROM Status_Demande WHERE idDemande = :demande AND idStatus = :status", nativeQuery = true)
    List<StatusDemande> findByIdDemandeAndIdStatus(@Param("demande") Long demande, @Param("status") Long status);
}