package com.mateus.skateshop_2_a_missao.service;

import com.mateus.skateshop_2_a_missao.domain.product.Product;
import com.mateus.skateshop_2_a_missao.dto.CreateProductDTO;
import com.mateus.skateshop_2_a_missao.dto.ProductResponseDTO;
import com.mateus.skateshop_2_a_missao.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> findAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public ProductResponseDTO findByName(String name){
        var product = productRepository.findByName(name).orElseThrow();
        return new ProductResponseDTO(product);
    }

    public void restock(Long id, Long quantity) {
        var product = productRepository.findById(id).orElseThrow();
        var oldQuantity = product.getQuantity();
        product.setQuantity(oldQuantity+quantity);
    }

    public void create(CreateProductDTO dto) {
        Product product = new Product(dto);
        productRepository.save(product);
    }
}
