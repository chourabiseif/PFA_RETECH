package com.retech.pfa.repositories;

import com.retech.pfa.models.StockSfax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSfaxRepository extends JpaRepository<StockSfax , Long> {
}
