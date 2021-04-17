package com.retech.pfa.services;

import com.retech.pfa.models.CountryStock;
import com.retech.pfa.models.StockSfax;
import com.retech.pfa.models.StockSousse;
import com.retech.pfa.models.StockTunis;
import com.retech.pfa.payLoad.responses.CountryStockResponse;
import com.retech.pfa.payLoad.responses.StockSfaxResponse;
import com.retech.pfa.payLoad.responses.StockSousseResponse;
import com.retech.pfa.payLoad.responses.StockTunisResponse;
import com.retech.pfa.repositories.*;
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
    @Autowired
    StockTunisRepository stockTunisRepository;
    @Autowired
    StockSousseRepository stockSousseRepository;
    @Autowired
    StockSfaxRepository stockSfaxRepository;

    // Get country stock with models
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
    // Get Tunis stock report with models
    public List<StockTunisResponse> getTunisStockWithModels(){
        List<StockTunis> listTunis =  this.stockTunisRepository.findAll();
        List<StockTunisResponse> listTunisStockWithModel = new ArrayList<StockTunisResponse>();
        // loop Tunis stock list to create a new list with models
        listTunis.forEach(x ->{
                    // get models for specific material code
                    List<String> listModelsWithDuplicates = this.bomRepository.getModel(Long.parseLong(x.getMaterialCode().trim()));
                    // eliminates duplication
                    List<String> listModelsWithoutDuplicates = new ArrayList<>(
                            new HashSet<>(listModelsWithDuplicates));
                    // create a new object Stock tunis with models (models is a list of string)
                    StockTunisResponse stockTunisResponse = new StockTunisResponse(x.getCustomerCode().trim(),x.getSerialNo1().trim(),x.getSerialNo2().trim(),x.getMaterialCode().trim(),x.getMaterialDesc().trim(),x.getQuantity(),x.getEmployeeName().trim(),x.getPartnerName().trim(),x.getFirstName().trim(),x.getStoreLocation().trim(),x.getBinCode().trim(),x.getPrice(),x.getGRNDate(),x.getWarrantyStatus().trim(),x.getClassification().trim(),listModelsWithoutDuplicates);
                    // add the object to the list  lisCountryStockWithModel
                    listTunisStockWithModel.add(stockTunisResponse);
                }
        );
        return listTunisStockWithModel;
    }
    // Get Sousse stock report with models
    public List<StockSousseResponse> getSousseStockWithModels(){
        List<StockSousse> listSousse =  this.stockSousseRepository.findAll();
        List<StockSousseResponse> listSousseStockWithModel = new ArrayList<StockSousseResponse>();
        // loop Sousse stock list to create a new list with models
        listSousse.forEach(x ->{
                    // get models for specific material code
                    List<String> listModelsWithDuplicates = this.bomRepository.getModel(Long.parseLong(x.getMaterialCode().trim()));
                    // eliminates duplication
                    List<String> listModelsWithoutDuplicates = new ArrayList<>(
                            new HashSet<>(listModelsWithDuplicates));
                    // create a new object Stock Sousse with models (models is a list of string)
                    StockSousseResponse stockSousseResponse = new StockSousseResponse(x.getCustomerCode().trim(),x.getSerialNo1().trim(),x.getSerialNo2().trim(),x.getMaterialCode().trim(),x.getMaterialDesc().trim(),x.getQuantity(),x.getEmployeeName().trim(),x.getPartnerName().trim(),x.getFirstName().trim(),x.getStoreLocation().trim(),x.getBinCode().trim(),x.getPrice(),x.getGRNDate(),x.getWarrantyStatus().trim(),x.getClassification().trim(),listModelsWithoutDuplicates);
                    // add the object to the list  lisCountryStockWithModel
                    listSousseStockWithModel.add(stockSousseResponse);
                }
        );
        return listSousseStockWithModel;
    }
    // Get Sfax stock report with models
    public List<StockSfaxResponse> getSfaxStockWithModels(){
        List<StockSfax> listSfax =  this.stockSfaxRepository.findAll();
        List<StockSfaxResponse> listSfaxStockWithModel = new ArrayList<StockSfaxResponse>();
        // loop Sfax stock list to create a new list with models
        listSfax.forEach(x ->{
                    // get models for specific material code
                    List<String> listModelsWithDuplicates = this.bomRepository.getModel(Long.parseLong(x.getMaterialCode().trim()));
                    // eliminates duplication
                    List<String> listModelsWithoutDuplicates = new ArrayList<>(
                            new HashSet<>(listModelsWithDuplicates));
                    // create a new object Stock Sfax with models (models is a list of string)
                    StockSfaxResponse stockSfaxResponse = new StockSfaxResponse(x.getCustomerCode().trim(),x.getSerialNo1().trim(),x.getSerialNo2().trim(),x.getMaterialCode().trim(),x.getMaterialDesc().trim(),x.getQuantity(),x.getEmployeeName().trim(),x.getPartnerName().trim(),x.getFirstName().trim(),x.getStoreLocation().trim(),x.getBinCode().trim(),x.getPrice(),x.getGRNDate(),x.getWarrantyStatus().trim(),x.getClassification().trim(),listModelsWithoutDuplicates);
                    // add the object to the list  lisCountryStockWithModel
                    listSfaxStockWithModel.add(stockSfaxResponse);
                }
        );
        return listSfaxStockWithModel;
    }
}
