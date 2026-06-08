package com.example.forage.repository;

import com.example.forage.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    
    List<Parametre> findByCouleur(String couleur);
    
    List<Parametre> findByStatus1IdStatusOrStatus2IdStatus(Long idStatus1, Long idStatus2);
}
