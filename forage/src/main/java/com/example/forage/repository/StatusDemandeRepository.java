package com.example.forage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forage.entity.Demande;
import com.example.forage.entity.StatusDemande;

@Repository
public interface StatusDemandeRepository extends JpaRepository<StatusDemande, Long> {

    List<StatusDemande> findByDemande(Demande demande);

}
