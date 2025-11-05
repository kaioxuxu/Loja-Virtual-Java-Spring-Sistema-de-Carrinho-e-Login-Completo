package com.example.loja.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.loja.backend.model.User;
import com.example.loja.backend.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder; // bean do BCrypt

    public User register(User user, boolean criptografar) {
        if (userRepo.findByCpf(user.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (criptografar) {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            user.setSenhaCriptografada(true);
        } else {
            user.setSenhaCriptografada(false);
        }
        return userRepo.save(user);
    }

    public User login(String cpf, String senha) {
        User user = userRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (user.isSenhaCriptografada()) {
            if (!passwordEncoder.matches(senha, user.getSenha())) {
                throw new RuntimeException("Senha incorreta");
            }
        } else {
            if (!senha.equals(user.getSenha())) {
                throw new RuntimeException("Senha incorreta");
            }
        }
        return user;
    }
}