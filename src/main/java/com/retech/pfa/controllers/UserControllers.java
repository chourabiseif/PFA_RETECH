package com.retech.pfa.controllers;


import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.User;
import com.retech.pfa.payLoad.responses.ResponseMessage;
import com.retech.pfa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
public class UserControllers {
    @Autowired
    private UserService userService;

    //ajouter utilisateur
    @RequestMapping(value = "/users/{agenceId}/{role}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> addUser(@RequestBody User user, @PathVariable(value = "agenceId")Long agenceId, @PathVariable(value = "role") String role) throws ResourceNotFoundException {
        String message = this.userService.addUser(user,agenceId,role) ;
        return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    // recuperer tous les utilisateurs
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public  ResponseEntity<List<User>> getUsers()
    {
        List<User> userList = this.userService.getUsers() ;
        return new ResponseEntity<>(userList , HttpStatus.OK);
    }

    // Récupérer un utilisateur par son id
    @GetMapping("/users/username/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable("username") String username)
    {
        User user = this.userService.findByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // récupérer user by id
    @RequestMapping(value="/users/{id}", method =  RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
       User user = this.userService.getUserByID(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
    //Modification utilisateur
    @RequestMapping(value="/users/{id}/{agenceId}", method =  RequestMethod.PUT)
    public ResponseEntity<ResponseMessage>  modifUser(@PathVariable(value="id") Long id,@PathVariable(value="agenceId") Long agenceId, @RequestBody User user) throws ResourceNotFoundException {
        String message = this.userService.modifUser(id,user,agenceId);
        return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    // Suppression utilisateur
    @RequestMapping(value="/users/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<List<User>>deleteUser(@PathVariable(value="id") Long id) throws ResourceNotFoundException {

        List<User> userList = this.userService.deleteUser(id) ;
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    // affect agence to user not used

    @RequestMapping(value="/users/{userId}/agence/{agenceId}" , method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> affectAgenceToUser(@PathVariable(value ="userId" ) Long userId,@PathVariable(value="agenceId") Long agenceId){
        String message = this.userService.affectAgenceToUser(userId,agenceId);
        return new  ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }
    // Affecter Role to user
    @PutMapping("/affect-role/{idUser}/{idRole}")
    public ResponseEntity<ResponseMessage> affectUserToRole(@PathVariable(value ="idUser" )Long idUser,@PathVariable(value ="idRole" ) Long idRole) throws ResourceNotFoundException {
        String message = this.userService.affectUserToRole(idUser, idRole);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

}
