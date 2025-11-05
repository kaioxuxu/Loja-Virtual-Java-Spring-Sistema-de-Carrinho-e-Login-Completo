package com.example.loja.backend.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.loja.backend.model.User;
import com.example.loja.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*") // permite CORS para todas as origens (apenas p/dev)
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Registro de usuário
    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        User user = new User();
        user.setNome(body.get("nome"));
        user.setEmail(body.get("email"));
        user.setSenha(body.get("senha"));
        user.setCpf(body.get("cpf"));

        boolean criptografar = Boolean.parseBoolean(body.get("criptografar"));
        return userService.register(user, criptografar);
    }

    // ✅ Login do usuário
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        String cpf = body.get("cpf");
        String senha = body.get("senha");
        return userService.login(cpf, senha);
    }
}
