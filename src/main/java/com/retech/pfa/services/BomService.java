package com.retech.pfa.services;

import com.retech.pfa.helper.BOMExcelHelper;
import com.retech.pfa.models.Bom;
import com.retech.pfa.repositories.BomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BomService {
    @Autowired
    BomRepository bomRepository;

    // Saving data from excel file

    public void save(MultipartFile file) {
        try {
            List<Bom> boms = BOMExcelHelper.excelToBom(file.getInputStream());
            //delete the old data
            bomRepository.deleteAll();
            //save the new data from bom
            bomRepository.saveAll(boms);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    // get all data from bom table
    public List<Bom> getBoms() {

        return this.bomRepository.findAll( );
    }

    // search with description
    public List<Bom> searchBomDescription(String description){
      return   this.bomRepository.searchBomDescription(description);
    }
    // search with description
    public List<Bom> searchBomModel(String model){
        return   this.bomRepository.searchBomModel(model);
    }
    // search with description
    public List<Bom> searchBomSapCode(long sapCode){
        return   this.bomRepository.searchBomSapCode(sapCode);
    }
}
