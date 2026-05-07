package com.example.forage.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.forage.entity.Commune;


@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
    
    @Query("SELECT c FROM Commune c WHERE c.nom like :nom%")
    List<Commune> findByNomContaining(@Param("nom") String nom);

    List<Commune> findByDistrictIdDistrict(Long idDistrict);
}