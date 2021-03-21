package com.retech.pfa.controllers;

import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.Role;
import com.retech.pfa.payLoad.responses.ResponseMessage;
import com.retech.pfa.services.RoleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleControllers {
    @Autowired
    RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> saveNewRole(@RequestBody Role role)
    {
        Role savedRole =  this.roleService.saveNewRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        List<Role> listRoles = this.roleService.getAllRoles();
        return new ResponseEntity<>(listRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Role role = this.roleService.findRoleByID(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateUserByID(@PathVariable("id") Long id, @RequestBody Role role) throws ResourceNotFoundException {
        String message = this.roleService.updateRoleByID(id, role);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        String message = this.roleService.deleteRoleById(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }
}
