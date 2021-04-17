package com.retech.pfa.repositories;

import com.retech.pfa.models.Bom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BomRepository  extends JpaRepository<Bom, Long> {

    // get model for sap code
    @Query(value = "SELECT brand,model from bom where sap_code = ?1", nativeQuery = true)
    List<String> getModel(Long sapCode);



    //search description
    @Query(value="SELECT * from bom where componentdescription_english like %?1% " , nativeQuery = true)
    List<Bom> searchBomDescription(String description);
    //search model
    @Query(value="SELECT * from bom where  model like %?1% " , nativeQuery = true)
    List<Bom> searchBomModel(String model);
    //search
    @Query(value="SELECT * from bom where  sap_code like %?1% " , nativeQuery = true)
    List<Bom> searchBomSapCode(long sapCode);


}

