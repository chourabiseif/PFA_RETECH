package com.retech.pfa.services;

import com.retech.pfa.models.CountryStock;
import com.retech.pfa.payLoad.responses.CountryStockResponse;
import com.retech.pfa.repositories.BomRepository;
import com.retech.pfa.repositories.CountryStockRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StockWithModelsService {
    @Autowired
    BomRepository bomRepository;
    @Autowired
    CountryStockRepository countryStockRepository;

    public List<CountryStockResponse> getCountryStockWithModels(){
      List<CountryStock> listCountry =  this.countryStockRepository.findAll();
      List<CountryStockResponse> listCountryStockWithModel = new ArrayList<CountryStockResponse>();
      // loop country stock list to create a new list with models
      listCountry.forEach(x ->{
          // get models for specific material code
          List<String> listModelsWithDuplicates = this.bomRepository.getModel(Long.parseLong(x.getMaterialCode().trim()));
          // eliminates duplication
          List<String> listModelsWithoutDuplicates = new ArrayList<>(
                  new HashSet<>(listModelsWithDuplicates));
         // create a new object country stock with models (models is a list of string)
         CountryStockResponse c = new CountryStockResponse(x.getCountryCode().trim(),x.getCustomerName().trim(),x.getPlantCode().trim(),x.getPlantName().trim(),x.getCustomerCode().trim(),x.getCustomerName().trim(),x.getDivisionCode().trim(),x.getMaterialCode().trim(),x.getMaterialName().trim(),x.getStoreCode().trim(),x.getStoreName().trim(),x.getTransitQty(),x.getActualSQty(),listModelsWithoutDuplicates);
         // add the object to the list  lisCountryStockWithModel
          listCountryStockWithModel.add(c);
      }
    );
        return listCountryStockWithModel;
    }

}
