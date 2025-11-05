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

    /**
     * Adiciona um item ao carrinho do usuário.
     * * @param body Um mapa contendo o ID do usuário ("userId") e o ID do produto
     * ("productId").
     * Exemplo de corpo da requisição:
     * {@code {"userId": 1, "productId": 42}}
     * 
     * @return O objeto {@code CartItem} que foi adicionado ao carrinho, incluindo
     *         seus detalhes.
     * @throws NumberFormatException     Se os valores de "userId" ou "productId"
     *                                   não puderem ser convertidos para Long.
     * @throws ResourceNotFoundException Se o usuário ou o produto não for
     *                                   encontrado (dependendo da sua implementação
     *                                   de serviço).
     */
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
