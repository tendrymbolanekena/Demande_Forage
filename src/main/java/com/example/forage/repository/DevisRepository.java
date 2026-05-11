package com.example.forage.repository;

import com.example.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.forage.entity.Devis;


@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {

}