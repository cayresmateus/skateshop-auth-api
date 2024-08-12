package com.mateus.skateshop_2_a_missao.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @Enumerated(EnumType.STRING) @NotBlank
    private Brand brand;

    @NotBlank @Enumerated(EnumType.STRING)
    private TypeProduct typeProduct;

    @Setter
    private long quantity;

    @Column(name="image_url")
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
