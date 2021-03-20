package com.retech.pfa.controllers;

import com.retech.pfa.helper.StockSfaxExcelHelper;

import com.retech.pfa.models.StockSfax;
import com.retech.pfa.payLoad.responses.ResponseMessage;
import com.retech.pfa.services.StockSfaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock/sfax")
public class StockSfaxControllers {
    @Autowired
    StockSfaxService stockSfaxService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (StockSfaxExcelHelper.hasExcelFormat(file)) {
            try {
                stockSfaxService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
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
    // get all data from StockTunis table
    @GetMapping("/")
    public  ResponseEntity<List<StockSfax>> getStockSfax(){
        List<StockSfax> stockSfaxList = this.stockSfaxService.getStockSfax();
        return new ResponseEntity<>(stockSfaxList , HttpStatus.OK);
    }
    // search with materialcode
    @GetMapping("/materialCode/{materialCode}")
    public ResponseEntity<List<StockSfax>> searchMaterialCode(@PathVariable(value = "materialCode") String materialCode){
        List<StockSfax> stockSfaxList = this.stockSfaxService.searchMaterialCode(materialCode);
        return new ResponseEntity<>(stockSfaxList, HttpStatus.OK);
    }
    // search with description
    @GetMapping("/materialDesc/{materialDesc}")
    public ResponseEntity<List<StockSfax>> searchMaterialDesc(@PathVariable(value = "materialDesc") String materialDesc){
        List<StockSfax> stockSfaxList = this.stockSfaxService.searchMaterialDesc(materialDesc);
        return new ResponseEntity<>(stockSfaxList, HttpStatus.OK);
    }
}
