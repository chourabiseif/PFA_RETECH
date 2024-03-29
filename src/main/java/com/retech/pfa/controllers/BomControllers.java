package com.retech.pfa.controllers;

import com.retech.pfa.helper.BOMExcelHelper;
import com.retech.pfa.models.Bom;
import com.retech.pfa.payLoad.responses.ResponseList;
import com.retech.pfa.payLoad.responses.ResponseMessage;

import com.retech.pfa.services.BomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bom")
@CrossOrigin(origins ="*")
public class BomControllers {
    @Autowired
    BomService bomService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (BOMExcelHelper.hasExcelFormat(file)) {
            try {
                bomService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    // get all data from bom table
    @GetMapping("/")
    public  ResponseEntity<List<Bom>> getBoms(){
        List<Bom> listBom = this.bomService.getBoms();
        return new ResponseEntity<>(listBom , HttpStatus.OK);
    }
    // search with description
    @GetMapping("/description/{description}")
    public ResponseEntity<List<Bom>> searchBomDescription(@PathVariable(value = "description") String description){
        List<Bom> bomList = this.bomService.searchBomDescription(description);
        return new ResponseEntity<>(bomList, HttpStatus.OK);
    }
    // search with description
    @GetMapping("/model/{model}")
    public ResponseEntity<List<Bom>> searchBomModel(@PathVariable(value = "model") String model){
        List<Bom> bomList = this.bomService.searchBomModel(model);
        return new ResponseEntity<>(bomList, HttpStatus.OK);
    }
    // search with description
    @GetMapping("/sapCode/{sapCode}")
    public ResponseEntity<List<Bom>> searchBomSapCode(@PathVariable(value = "sapCode") long sapCode){
        List<Bom> bomList = this.bomService.searchBomSapCode(sapCode);
        return new ResponseEntity<>(bomList, HttpStatus.OK);
    }

    // get models
    @GetMapping("/models/{sapCode}")
    public ResponseEntity<ResponseList> getModels(@PathVariable(value = "sapCode") Long sapCode){
        List<String> bomList = this.bomService.getModels(sapCode);
        return new ResponseEntity<>(new ResponseList(bomList), HttpStatus.OK);
    }
}
