package com.example.loja.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.loja.backend.model.CartItem;
import com.example.loja.backend.model.Product;
import com.example.loja.backend.model.User;
import com.example.loja.backend.repository.CartItemRepository;
import com.example.loja.backend.repository.ProductRepository;
import com.example.loja.backend.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItem addItem(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem existingItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (existingItem != null) {
            existingItem.setQuantidade(existingItem.getQuantidade() + 1);
            return cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantidade(1);
            return cartItemRepository.save(newItem);
        }
    }

    public List<CartItem> getItemsByUser(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
}
