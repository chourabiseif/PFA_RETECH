package com.retech.pfa.services;

import com.retech.pfa.models.Agence;
import com.retech.pfa.models.User;
import com.retech.pfa.repositories.AgenceRepository;
import com.retech.pfa.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgenceRepository agenceRepository;

    //ajouter utlisateur
    public  String addUser(User user , Long agenceId){

        Agence agence = this.agenceRepository.findById(agenceId).get();

        user.setAgence(agence);
        this.userRepository.save(user);
        return "Utilisateur ajouté avec succés";
    }

    // récupérer les utilisateurs
    public List<User> getUsers(){
        return  this.userRepository.findAll();
    }

    // récupérer user by id
    public User getUserByID(Long id){
       return this.userRepository.findById(id).get();
    }

    //Modifier utilisateur
    public String modifUser(Long id , User user , Long agenceId){

        Agence agence = this.agenceRepository.findById(agenceId).get();
        User userfound = this.userRepository.findById(id).get();
        userfound.setMotDePasse(user.getMotDePasse());
        userfound.setNom(user.getNom());
        userfound.setPrenom(user.getPrenom());
        userfound.setUsername(user.getUsername());

        userfound.setAgence(agence);
        this.userRepository.save(userfound);
        return "Utilisateur modifié";
    }

    //Supprimer Utilisateur
    public List<User> deleteUser(Long id){
        this.userRepository.deleteById(id);
        return this.userRepository.findAll();
    }

    // affect agence to user not used until now
    public String affectAgenceToUser(Long userId,Long  agenceId){
        User user =  this.userRepository.findById(userId).get();
        Agence agence = this.agenceRepository.findById(agenceId).get();
    //add user to publication

        user.setAgence(agence);
        this.userRepository.save(user);

        return "agence affected to user";
    }

}
