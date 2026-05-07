package com.example.forage.repository;

import com.example.forage.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    

    Optional<Status> findByLibelle(String libelle);
}
