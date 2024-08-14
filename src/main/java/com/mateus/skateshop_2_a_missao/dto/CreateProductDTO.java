package com.mateus.skateshop_2_a_missao.dto;

import com.mateus.skateshop_2_a_missao.domain.product.Brand;
import com.mateus.skateshop_2_a_missao.domain.product.TypeProduct;

import java.math.BigDecimal;
import java.math.BigInteger;

public record CreateProductDTO(String name,
                               BigDecimal price,
                               String description,
                               Brand brand,
                               TypeProduct typeProduct,
                               Long quantity,
                               String imageUrl) {
}
