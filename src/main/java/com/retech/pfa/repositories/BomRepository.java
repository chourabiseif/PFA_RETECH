package com.retech.pfa.repositories;

import com.retech.pfa.models.Bom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BomRepository  extends JpaRepository<Bom, Long> {
    //search bom
    @Query(value="SELECT * from bom where componentdescription_english like %?1% or model like %?2% or sap_code like %?3%" , nativeQuery = true)
    List<Bom> searchBom(String description, String model,String sap_code);

}

