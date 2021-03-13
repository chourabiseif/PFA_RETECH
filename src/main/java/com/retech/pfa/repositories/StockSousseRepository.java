package com.retech.pfa.repositories;

import com.retech.pfa.models.StockSousse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSousseRepository extends JpaRepository<StockSousse, Long> {
}
