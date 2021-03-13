package com.retech.pfa.services;

import com.retech.pfa.helper.StockSousseExcelHelper;
import com.retech.pfa.helper.StockTunislExcelHelper;
import com.retech.pfa.models.StockSousse;
import com.retech.pfa.repositories.StockSousseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StockSousseService {
    @Autowired
    StockSousseRepository stockSousseRepository;

    // Saving data from excel file

    public void save(MultipartFile file) {
        try {
            List<StockSousse> stockSousseData = StockSousseExcelHelper.exceltoStockSousse(file.getInputStream());
            //delete the old data
            stockSousseRepository.deleteAll();
            //save the new data from StockSousse
            stockSousseRepository.saveAll(stockSousseData);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    // get all data from StockSousse table
    public List<StockSousse> getStockSousse() {

        return this.stockSousseRepository.findAll( );
    }
}
