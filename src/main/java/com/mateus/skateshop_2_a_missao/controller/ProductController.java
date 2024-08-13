package com.mateus.skateshop_2_a_missao.controller;

import com.mateus.skateshop_2_a_missao.dto.CreateProductDTO;
import com.mateus.skateshop_2_a_missao.dto.ProductResponseDTO;
import com.mateus.skateshop_2_a_missao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> list(){
        var products = service.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponseDTO> findByName(@PathVariable String name){
        var product = service.findByName(name);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductDTO dto){
        service.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restock/{id}")
    public void restock(@PathVariable Long id, @RequestBody Long quantity){
        service.restock(id, quantity);
    }


}
