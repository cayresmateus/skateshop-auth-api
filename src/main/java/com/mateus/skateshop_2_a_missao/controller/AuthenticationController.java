package com.mateus.skateshop_2_a_missao.controller;

import com.mateus.skateshop_2_a_missao.domain.user.User;
import com.mateus.skateshop_2_a_missao.dto.AuthDTO;
import com.mateus.skateshop_2_a_missao.dto.LoginResponseDTO;
import com.mateus.skateshop_2_a_missao.dto.RegisterDTO;
import com.mateus.skateshop_2_a_missao.infra.security.TokenService;
import com.mateus.skateshop_2_a_missao.repositories.UserRepository;
import com.mateus.skateshop_2_a_missao.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        this.authorizationService.registerUser(dto);
        return ResponseEntity.ok().build();
    }

}
