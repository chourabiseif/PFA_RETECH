package com.retech.pfa.services;

import com.retech.pfa.helper.CountryStockExcelHelper;
import com.retech.pfa.helper.StockTunisExcelHelper;
import com.retech.pfa.models.CountryStock;
import com.retech.pfa.models.StockTunis;
import com.retech.pfa.repositories.StockTunisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StockTunisService {
    @Autowired
    StockTunisRepository stockTunisRepository;

    // Saving data from excel file

    public void save(MultipartFile file) {
        try {
            List<StockTunis> stockTunisData = StockTunisExcelHelper.exceltoStockTunis(file.getInputStream());
            //delete the old data
            stockTunisRepository.deleteAll();
            //save the new data from Stocktunis
            stockTunisRepository.saveAll(stockTunisData);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    // get all data from StockTunis table
    public List<StockTunis> getStockTunis() {

        return this.stockTunisRepository.findAll( );
    }
}
