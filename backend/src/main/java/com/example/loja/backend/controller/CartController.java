package com.example.loja.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.example.loja.backend.model.CartItem;
import com.example.loja.backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public CartItem addToCart(@RequestBody Map<String, Object> body) {
        Long userId = Long.parseLong(body.get("userId").toString());
        Long productId = Long.parseLong(body.get("productId").toString());
        return cartService.addItem(userId, productId);
    }

    
    @GetMapping("/items")
    public List<CartItem> getCartItems(@RequestParam Long userId) {
        return cartService.getItemsByUser(userId);
    }
}
