package com.retech.pfa.controllers;


import com.retech.pfa.payLoad.responses.CountryStockResponse;
import com.retech.pfa.payLoad.responses.StockSfaxResponse;
import com.retech.pfa.payLoad.responses.StockSousseResponse;
import com.retech.pfa.payLoad.responses.StockTunisResponse;
import com.retech.pfa.services.StockWithModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stockWithModels")
public class StockWithModelsControllers {

    @Autowired
    StockWithModelsService stockWithModelsService;

    // Get Country Stock with models
    @GetMapping("/countrystock/")
    public ResponseEntity<List<CountryStockResponse>> getCountryStock(){
        List<CountryStockResponse> list = this.stockWithModelsService.getCountryStockWithModels();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    // Get Tunis Stock with models
    @GetMapping("/stockTunis/")
    public ResponseEntity<List<StockTunisResponse>> getStockTunis(){
        List<StockTunisResponse> list = this.stockWithModelsService.getTunisStockWithModels();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    // Get Sousse Stock with models
    @GetMapping("/stockSousse/")
    public ResponseEntity<List<StockSousseResponse>> getStockSousse(){
        List<StockSousseResponse> list = this.stockWithModelsService.getSousseStockWithModels();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }
    // Get Sfax Stock with models
    @GetMapping("/stockSfax/")
    public ResponseEntity<List<StockSfaxResponse>> getStockSfax(){
        List<StockSfaxResponse> list = this.stockWithModelsService.getSfaxStockWithModels();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }
}
