package com.retech.pfa.services;

import com.retech.pfa.models.Agence;
import com.retech.pfa.repositories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String modifAgence(Long id, Agence agence){
        Agence agencefound = this.agenceRepository.findById(id).get();
        agencefound.setNom(agence.getNom());
        agencefound.setAdresse(agence.getAdresse());
        agencefound.setTelephone(agence.getTelephone());


        this.agenceRepository.save(agencefound);
        return " agence modifié";
    }

    //Suppression d'un agence service
    public List<Agence> supprimerAgence(Long id){
        this.agenceRepository.deleteById(id);
        return this.agenceRepository.findAll();

    }


}
