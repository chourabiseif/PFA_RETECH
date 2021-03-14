package com.retech.pfa.services;

import com.retech.pfa.helper.StockSfaxExcelHelper;
import com.retech.pfa.models.StockSfax;
import com.retech.pfa.models.StockTunis;
import com.retech.pfa.repositories.StockSfaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StockSfaxService {
    @Autowired
    StockSfaxRepository stockSfaxRepository;

    // Saving data from excel file

    public void save(MultipartFile file) {
        try {
            List<StockSfax> stockSfaxData = StockSfaxExcelHelper.exceltoStockSfax(file.getInputStream());
            //delete the old data
            stockSfaxRepository.deleteAll();
            //save the new data from StockSfax
            stockSfaxRepository.saveAll(stockSfaxData);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    // get all data from StockTunis table
    public List<StockSfax> getStockSfax() {

        return this.stockSfaxRepository.findAll( );
    }
    // search with Material Code
    public List<StockSfax> searchMaterialCode(String materialCode){
        return   this.stockSfaxRepository.searchMaterialCode(materialCode);
    }
    // search with Material desc
    public List<StockSfax> searchMaterialDesc(String materialDesc){
        return   this.stockSfaxRepository.searchMaterialDesc(materialDesc);
    }
}
