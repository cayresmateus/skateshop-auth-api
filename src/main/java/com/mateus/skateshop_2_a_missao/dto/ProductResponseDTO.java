package com.mateus.skateshop_2_a_missao.dto;

import com.mateus.skateshop_2_a_missao.domain.product.Brand;
import com.mateus.skateshop_2_a_missao.domain.product.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(String name,
                                 BigDecimal price,
                                 String description,
                                 Brand brand,
                                 String imageUrl,
                                 Long quantity
                                 ) {
    public ProductResponseDTO(Product p){
        this( p.getName(), p.getPrice(), p.getDescription(), p.getBrand(), p.getImageUrl(), p.getQuantity());

    }
}
