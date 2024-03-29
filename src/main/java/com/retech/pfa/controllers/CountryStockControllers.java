package com.retech.pfa.controllers;

import com.retech.pfa.helper.CountryStockExcelHelper;
import com.retech.pfa.models.CountryStock;
import com.retech.pfa.payLoad.responses.ResponseMessage;
import com.retech.pfa.services.CountryStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/stock/country")
public class CountryStockControllers {
    @Autowired
    CountryStockService countryStockService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CountryStockExcelHelper.hasExcelFormat(file)) {
            try {
                countryStockService.save(file);

                message = "Uploaded the file successfully): " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                Throwable cause = e.getCause();
                System.out.println(cause);
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    // get all data from countryStock table
    @GetMapping("/")
    public  ResponseEntity<List<CountryStock>> getCountryStocks(){
        List<CountryStock> listCountryStock = this.countryStockService.getCountryStocks();
        return new ResponseEntity<>(listCountryStock , HttpStatus.OK);
    }
    // search with material code
    @GetMapping("/materialCode/{materialCode}")
    public ResponseEntity<List<CountryStock>> searchMaterialCode(@PathVariable(value = "materialCode") String materialCode){
        List<CountryStock> countryStockList = this.countryStockService.searchMaterialCode(materialCode);
        return new ResponseEntity<>(countryStockList, HttpStatus.OK);
    }
    // search with material name
    @GetMapping("/materialName/{materialName}")
    public ResponseEntity<List<CountryStock>> searchMaterialName(@PathVariable(value = "materialName") String materialName){
        List<CountryStock> countryStockList = this.countryStockService.searchMaterialName(materialName);
        return new ResponseEntity<>(countryStockList, HttpStatus.OK);
    }
}
