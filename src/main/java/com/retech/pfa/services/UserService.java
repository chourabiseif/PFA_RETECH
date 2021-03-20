package com.retech.pfa.services;

import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.Agence;
import com.retech.pfa.models.Role;
import com.retech.pfa.models.User;
import com.retech.pfa.repositories.AgenceRepository;
import com.retech.pfa.repositories.RoleRepository;
import com.retech.pfa.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    //ajouter utlisateur
    public  String addUser(User user , Long agenceId) throws ResourceNotFoundException {

        Optional<Agence> agencedata = this.agenceRepository.findById(agenceId);
        if(agencedata.isPresent()){
            Agence agence = agencedata.orElseThrow(()-> new ResourceNotFoundException("Agence not found"));
            user.setAgence(agence);
            user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
            this.userRepository.save(user);
            return "Utilisateur ajouté avec succés";
        }
        else {
            throw new  ResourceNotFoundException("Agence not found");
        }


    }

    // récupérer les utilisateurs
    public List<User> getUsers(){

        return  this.userRepository.findAll();
    }

    // récupérer user by id
    public User getUserByID(Long id) throws ResourceNotFoundException {

        Optional<User> user = this.userRepository.findById(id);
        // Return statement if user exist or throw exception
        return user.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    //Modifier utilisateur
    public String modifUser(Long id , User user , Long agenceId) throws ResourceNotFoundException {
        Optional<User> userData = this.userRepository.findById(id);
        Optional<Agence> agenceData = this.agenceRepository.findById(agenceId);
        if (userData.isPresent()) {
            Agence agence = agenceData.orElseThrow(()-> new ResourceNotFoundException("Agence not found"));
            User userfound = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            userfound.setMotDePasse(user.getMotDePasse());
            userfound.setNom(user.getNom());
            userfound.setPrenom(user.getPrenom());
            userfound.setUsername(user.getUsername());
            userfound.setAgence(agence);
            this.userRepository.save(userfound);
            return "Utilisateur modifié";
        }else{
            throw new ResourceNotFoundException("User not found");
        }


    }

    //Supprimer Utilisateur
    public List<User> deleteUser(Long id) throws ResourceNotFoundException {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            this.userRepository.deleteById(id);
            return this.userRepository.findAll();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // find user by user name
    @Transactional(readOnly = true)
    public User findByUserName(String username) {
        User user = null;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw e;
        }
        return user;
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

    // Affecter Role to user
    public String affectUserToRole(Long idUser, Long idRole) throws ResourceNotFoundException {
        Optional<User> userData = this.userRepository.findById(idUser);
        if (userData.isPresent()) {
            User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Optional<Role> roleData = this.roleRepository.findById(idRole);
            if (roleData.isPresent()) {
                Role existingRole = roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
                Set<Role> roles = existingUser.getRoles();
                roles.add(existingRole);
                existingUser.setRoles(roles);
                this.userRepository.save(existingUser);

            }
        }
        return "User affected to role successfully!";
    }


}
