package com.example.loja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.loja.backend.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId); 
    CartItem findByUserIdAndProductId(Long userId, Long productId);

}
