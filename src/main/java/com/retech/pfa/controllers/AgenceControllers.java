package com.retech.pfa.controllers;

import com.retech.pfa.models.Agence;
import com.retech.pfa.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AgenceControllers {

    @Autowired
    AgenceService agenceService;

    //ajout agence controller
    @RequestMapping(value = "/agences/", method = RequestMethod.POST)
    public  String addAgence(@RequestBody Agence agence)
    {

        return agenceService.addAgence(agence) ;
    }

    //affichage de tous les agences controller
    @RequestMapping(value = "/agences/", method = RequestMethod.GET)
    public  List<Agence> getAgences()
    {
        return this.agenceService.getAgences() ;
    }

    //recherche agence par nom
    @RequestMapping(value="/agences/{nom}", method =  RequestMethod.GET)
    public List<Agence> getAgenceByNom(@PathVariable(value="nom") String nom){
        return this.agenceService.getAgenceByNom(nom) ;
    }



    //Modification d'un seul agence controller
    @RequestMapping(value="/agences/{id}", method =  RequestMethod.PUT)
    public String  modifAgence(@PathVariable(value="id") Long id, @RequestBody Agence agence){
        return this.agenceService.modifAgence(id, agence ) ;
    }

    //Suppression d'un seul agence controller
    @RequestMapping(value="/agences/{id}", method =  RequestMethod.DELETE)
    public List<Agence>supprimerfAgence(@PathVariable(value="id") Long id){
        return this.agenceService.supprimerAgence(id) ;
    }

}
