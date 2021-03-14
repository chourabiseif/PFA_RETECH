package com.retech.pfa.repositories;

import com.retech.pfa.models.StockSfax;
import com.retech.pfa.models.StockTunis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockSfaxRepository extends JpaRepository<StockSfax , Long> {
    //search materialCode
    @Query(value="SELECT * from stock_sfax where material_code like %?1% " , nativeQuery = true)
    List<StockSfax> searchMaterialCode(String materialCode);
    //search Materialdesc
    @Query(value="SELECT * from stock_sfax where material_desc like %?1% " , nativeQuery = true)
    List<StockSfax> searchMaterialDesc(String materialDesc);
}
