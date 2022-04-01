package com.project.staybooking.service;

import com.project.staybooking.exception.UserNotExistException;
import com.project.staybooking.model.Token;
import com.project.staybooking.model.User;
import com.project.staybooking.model.UserRole;
import com.project.staybooking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;


    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    public Token authenticate(User user, UserRole role) throws UserNotExistException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        } catch (AuthenticationException ex) {
            throw new UserNotExistException("User doesn't exist");

        }

        if (authentication == null || !authentication.isAuthenticated() ||
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
            throw new UserNotExistException("User authentication fail");
        }

        return new Token(jwtUtil.generateToken(user.getUsername()));
    }

}
