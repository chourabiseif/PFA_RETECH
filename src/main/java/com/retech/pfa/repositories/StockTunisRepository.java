package com.retech.pfa.repositories;

import com.retech.pfa.models.StockTunis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTunisRepository extends JpaRepository<StockTunis, Long> {
}
