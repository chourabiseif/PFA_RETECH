package com.retech.pfa.security.jwt;

import com.retech.pfa.models.Role;
import com.retech.pfa.models.User;
import com.retech.pfa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return authorities;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username = " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getMotDePasse(), getGrantedAuthorities(user));
    }
}
