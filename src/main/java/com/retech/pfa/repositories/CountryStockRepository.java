package com.retech.pfa.repositories;

import com.retech.pfa.models.CountryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryStockRepository extends JpaRepository<CountryStock , Long> {
}
