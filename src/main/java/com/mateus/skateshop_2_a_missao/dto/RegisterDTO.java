package com.mateus.skateshop_2_a_missao.dto;


import com.mateus.skateshop_2_a_missao.domain.user.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
