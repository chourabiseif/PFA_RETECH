package com.retech.pfa.repositories;

import com.retech.pfa.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
    
    List<Agence> findByNom(String nom);

}
