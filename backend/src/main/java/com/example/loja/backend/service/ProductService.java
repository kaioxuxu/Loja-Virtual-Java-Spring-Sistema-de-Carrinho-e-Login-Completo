package com.example.loja.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loja.backend.model.Product;
import com.example.loja.backend.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> listAll() {
        return productRepo.findAll();
    }
}
