package com.retech.pfa.services;

import com.retech.pfa.exceptions.ResourceNotFoundException;
import com.retech.pfa.models.Role;
import com.retech.pfa.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role saveNewRole(Role role)
    {
        return this.roleRepository.save(role);
    }

    public List<Role> getAllRoles()
    {
        return this.roleRepository.findAll();
    }

    public Role findRoleByID(Long id) throws ResourceNotFoundException {
        Optional<Role> roleData = this.roleRepository.findById(id);
        // Return statement if user exist or null
        return roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

    }

    public String updateRoleByID(Long id, Role role) throws ResourceNotFoundException {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            Role existingRole = roleData.orElse(null);
            existingRole.setName(role.getName());
            // save existing User in the database
            this.roleRepository.save(existingRole);
            // return statement
            return "Role updated successfully!";
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }

    public String deleteRoleById(Long id) throws ResourceNotFoundException {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            this.roleRepository.deleteById(id);
            return "Role deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }
}
