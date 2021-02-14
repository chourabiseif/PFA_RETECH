package com.retech.pfa.services;

import com.retech.pfa.models.User;
import com.retech.pfa.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //ajouter utlisateur
    public  String addUser(User user){
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
    public String modifUser(Long id , User user){
        User userfound = this.userRepository.findById(id).get();
        userfound.setMotDePasse(user.getMotDePasse());
        userfound.setNom(user.getNom());
        userfound.setPrenom(user.getPrenom());
        userfound.setUsername(user.getUsername());

        this.userRepository.save(userfound);
        return "Utilisateur modifié";
    }

    //Supprimer Utilisateur
    public List<User> deleteUser(Long id){
        this.userRepository.deleteById(id);
        return this.userRepository.findAll();
    }

}
