package com.retech.pfa.controllers;


import com.retech.pfa.models.Agence;
import com.retech.pfa.models.User;
import com.retech.pfa.playLoad.responses.ResponseMessage;
import com.retech.pfa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {
    @Autowired
    private UserService userService;

    //ajouter utilisateur
    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> addUser(@RequestBody User user)
    {
        String message = this.userService.addUser(user) ;
        return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    // recuperer tous les utilisateurs
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public  ResponseEntity<List<User>> getUsers()
    {
        List<User> userList = this.userService.getUsers() ;
        return new ResponseEntity<>(userList , HttpStatus.OK);
    }

    // récupérer user by id
    @RequestMapping(value="/users/{id}", method =  RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(value="id") Long id){
       User user = this.userService.getUserByID(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
    //Modification utilisateur
    @RequestMapping(value="/users/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<ResponseMessage>  modifUser(@PathVariable(value="id") Long id, @RequestBody User user){
        String message = this.userService.modifUser(id,user);
        return new ResponseEntity<>(new ResponseMessage(message) , HttpStatus.OK);
    }

    // Suppression utilisateur
    @RequestMapping(value="/users/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<List<User>>deleteUser(@PathVariable(value="id") Long id){

        List<User> userList = this.userService.deleteUser(id) ;
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
