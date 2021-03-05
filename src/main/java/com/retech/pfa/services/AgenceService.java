package com.retech.pfa.services;

import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.Agence;
import com.retech.pfa.repositories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceService {
    @Autowired
    AgenceRepository agenceRepository;

    //ajout agence service
    public String addAgence(Agence agence){
        this.agenceRepository.save(agence);
        return "Agence ajouté avec succés";
    }

    // récupération des agences service
    public List<Agence> getAgences(){

        return this.agenceRepository.findAll();
    }

    //recherche agence par nom
    public List<Agence> getAgenceByNom(String nom){

        return this.agenceRepository.findByNom(nom);
    }

    //modification d'un agence service
    public String modifAgence(Long id, Agence agence) throws ResourceNotFoundException {
       Optional<Agence>  agenceData = this.agenceRepository.findById(id);
       if (agenceData.isPresent()){
           Agence agencefound = agenceData.orElseThrow(()-> new ResourceNotFoundException("Agence not found"));
           agencefound.setNom(agence.getNom());
           agencefound.setAdresse(agence.getAdresse());
           agencefound.setTelephone(agence.getTelephone());


           this.agenceRepository.save(agencefound);
           return " agence modifié";
       }else{
           throw new ResourceNotFoundException("Agence not found");
       }

    }

    //Suppression d'un agence service
    public List<Agence> supprimerAgence(Long id) throws ResourceNotFoundException {
        Optional<Agence> agenceData = this.agenceRepository.findById(id);
        if(agenceData.isPresent()){
            this.agenceRepository.deleteById(id);
            return this.agenceRepository.findAll();
        }
        else{
            throw  new ResourceNotFoundException("Agence not found");
        }


    }


}
