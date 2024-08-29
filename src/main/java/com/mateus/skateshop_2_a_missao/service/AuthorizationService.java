package com.mateus.skateshop_2_a_missao.service;

import com.mateus.skateshop_2_a_missao.domain.user.User;
import com.mateus.skateshop_2_a_missao.domain.user.UserRole;
import com.mateus.skateshop_2_a_missao.domain.user.exceptions.UserAlreadyExistsException;
import com.mateus.skateshop_2_a_missao.dto.AuthDTO;
import com.mateus.skateshop_2_a_missao.dto.RegisterDTO;
import com.mateus.skateshop_2_a_missao.infra.security.TokenService;
import com.mateus.skateshop_2_a_missao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public String loginUser(AuthDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((User) auth.getPrincipal());
    }

    public void registerUser(RegisterDTO dto){
        if(repository.findByUsername(dto.username()) != null){
            throw new UserAlreadyExistsException("This username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), encryptedPassword);
        repository.save(newUser);
    }

    public void registerAdm(RegisterDTO dto){
        if(repository.findByUsername(dto.username()) != null){
            throw new UserAlreadyExistsException("This username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.username(), encryptedPassword, UserRole.ADMIN);
        repository.save(newUser);
    }
}
