CREATE DATABASE IF NOT EXISTS loja;

USE loja;

CREATE TABLE
    users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
        senha VARCHAR(255) NOT NULL,
        cpf VARCHAR(11) NOT NULL UNIQUE,
        senha_criptografada BOOLEAN NOT NULL
    );

CREATE TABLE
    products (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        preco DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE
    cart_items (
        id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT NOT NULL,
        product_id INT NOT NULL,
        quantidade INT NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users (id),
        FOREIGN KEY (product_id) REFERENCES products (id)
    );

INSERT INTO
    products (nome, preco)
VALUES
    ('Produto A', 10.00),
    ('Produto B', 20.00),
    ('Produto C', 30.00);