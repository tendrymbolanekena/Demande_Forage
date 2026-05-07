package com.example.forage.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forage.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByNomContaining(String nom);
}
