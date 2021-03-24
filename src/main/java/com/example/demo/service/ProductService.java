package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Optional;

public interface ProductService {
    void save(Product product);

    Product findById(Long id);

    Product findByName(String name);
}
