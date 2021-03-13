package com.retech.pfa.controllers;

import com.retech.pfa.helper.BOMExcelHelper;
import com.retech.pfa.models.Bom;
import com.retech.pfa.playLoad.responses.ResponseMessage;

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

                message = "Uploaded the file successfully): " + file.getOriginalFilename();
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

}
