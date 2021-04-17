package com.retech.pfa.controllers;

import com.retech.pfa.models.CountryStock;
import com.retech.pfa.models.StockSfax;
import com.retech.pfa.payLoad.responses.CountryStockResponse;
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

    @GetMapping("/")
    public ResponseEntity<List<CountryStockResponse>> get(){
        List<CountryStockResponse> list = this.stockWithModelsService.getCountryStockWithModels();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }
}
