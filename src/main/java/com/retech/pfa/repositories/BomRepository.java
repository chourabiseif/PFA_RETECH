package com.retech.pfa.repositories;

import com.retech.pfa.models.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository  extends JpaRepository<Bom, Long> {
}
