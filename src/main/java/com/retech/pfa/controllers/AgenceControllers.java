package com.retech.pfa.controllers;

import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.Agence;
import com.retech.pfa.payLoad.responses.ResponseMessage;
import com.retech.pfa.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
public class AgenceControllers {

    @Autowired
    AgenceService agenceService;

    //ajout agence controller
    @RequestMapping(value = "/agences/", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage>addAgence(@RequestBody Agence agence)
    {
         String message = this.agenceService.addAgence(agence) ;
         return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    //affichage de tous les agences controller
    @RequestMapping(value = "/agences/", method = RequestMethod.GET)
    public  ResponseEntity<List<Agence>> getAgences()
    {
        List<Agence> agenceList = this.agenceService.getAgences() ;
        return new ResponseEntity<>(agenceList , HttpStatus.OK);
    }

    //recherche agence par nom


    @RequestMapping(value="/agences/{nom}", method =  RequestMethod.GET)
    public ResponseEntity<List<Agence>> getAgenceByNom(@PathVariable(value="nom") String nom){
        List<Agence> agenceList = this.agenceService.getAgenceByNom(nom);
        return new ResponseEntity<>(agenceList , HttpStatus.OK);
    }

    //Modification d'un seul agence controller
    @RequestMapping(value="/agences/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<ResponseMessage>  modifAgence(@PathVariable(value="id") Long id, @RequestBody Agence agence) throws ResourceNotFoundException {
        String message = this.agenceService.modifAgence(id, agence ) ;
        return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    //Suppression d'un seul agence controller
    @RequestMapping(value="/agences/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<List<Agence>>supprimerAgence(@PathVariable(value="id") Long id) throws ResourceNotFoundException {

        List<Agence> agenceList = this.agenceService.supprimerAgence(id) ;
        return new ResponseEntity<>(agenceList, HttpStatus.OK);
     }

}
