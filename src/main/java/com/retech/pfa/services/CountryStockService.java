package com.retech.pfa.services;

import com.retech.pfa.helper.CountryStockExcelHelper;
import com.retech.pfa.models.Bom;
import com.retech.pfa.models.CountryStock;
import com.retech.pfa.repositories.CountryStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CountryStockService {
    @Autowired
    CountryStockRepository countryStockRepository;

    // Saving data from excel file

    public void save(MultipartFile file) {
        try {
            List<CountryStock> countryStocks = CountryStockExcelHelper.excelToCountryStock(file.getInputStream());
            //delete the old data
            countryStockRepository.deleteAll();
            //save the new data from CountryStock
            countryStockRepository.saveAll(countryStocks);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    // get all data from CountryStock table
    public List<CountryStock> getCountryStocks() {

        return this.countryStockRepository.findAll( );
    }
}
