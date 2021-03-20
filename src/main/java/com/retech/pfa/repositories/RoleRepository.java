package com.retech.pfa.repositories;

import com.retech.pfa.models.ERole;
import com.retech.pfa.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // find Role by name
    Optional<Role> findByName(ERole name);
}

