package com.retech.pfa.repositories;

import com.retech.pfa.models.Bom;
import com.retech.pfa.models.CountryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryStockRepository extends JpaRepository<CountryStock , Long> {
    //search Material code
    @Query(value="SELECT * from country_stock where material_code like %?1% " , nativeQuery = true)
    List<CountryStock> searchMaterialCode(String materialCode);
    //search Material name
    @Query(value="SELECT * from country_stock where material_name like %?1% " , nativeQuery = true)
    List<CountryStock> searchMaterialName(String materialName);
}
