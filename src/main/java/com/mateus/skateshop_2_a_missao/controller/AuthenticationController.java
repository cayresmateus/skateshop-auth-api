package com.mateus.skateshop_2_a_missao.controller;

import com.mateus.skateshop_2_a_missao.dto.AuthDTO;
import com.mateus.skateshop_2_a_missao.dto.LoginResponseDTO;
import com.mateus.skateshop_2_a_missao.dto.RegisterDTO;
import com.mateus.skateshop_2_a_missao.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO dto){
        String token = authorizationService.loginUser(dto);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO dto){
        this.authorizationService.registerUser(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/adm")
    public ResponseEntity<String> registerAdm(@RequestBody @Valid RegisterDTO dto){
        this.authorizationService.registerAdm(dto);
        return ResponseEntity.ok().build();
    }
}
