package com.mateus.skateshop_2_a_missao.service;

import com.mateus.skateshop_2_a_missao.domain.user.User;
import com.mateus.skateshop_2_a_missao.domain.user.exceptions.UserAlreadyExistsException;
import com.mateus.skateshop_2_a_missao.dto.RegisterDTO;
import com.mateus.skateshop_2_a_missao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public void registerUser(RegisterDTO dto){
        if(repository.findByUsername(dto.username()) != null){
            throw new UserAlreadyExistsException("This username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), encryptedPassword, dto.role());
        repository.save(newUser);
    }
}
