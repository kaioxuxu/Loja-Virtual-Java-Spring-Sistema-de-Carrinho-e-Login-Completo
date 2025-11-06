# Loja App Java

## Autor: Kaio Alexsander da Silva de Sá

## Sobre o projeto
Este é um projeto simples de loja virtual desenvolvido com Java Spring Boot no backend e HTML + JavaScript no frontend.  
O sistema permite cadastrar e logar usuários, listar produtos, adicionar itens ao carrinho e visualizar o carrinho do usuário.

## Tecnologias utilizadas
- Java 21 
- Spring Boot  
- JPA / Hibernate  
- MySQL  
- HTML, CSS e JavaScript  
- XAMPP (para MySQL e Apache)

## Como executar o projeto

### 1. Banco de dados
- Abra o XAMPP e inicie o Apache e o MySQL.  
- Crie um banco de dados chamado `loja`.  
- Verifique o arquivo `application.properties` no backend e ajuste o usuário e senha do MySQL se necessário.

### 2. Backend
- Abra o projeto no VS Code ou IntelliJ.  
- Execute o arquivo principal `LojaApplication.java` 
- A API ficará disponível em `http://localhost:8080`.

### 3. Frontend
- Copie a pasta do frontend (que contém o arquivo `index.html`) para dentro da pasta: C:\xampp\htdocs\
- No navegador, acesse: http://localhost/frontend/register.html 


## Testando
- Faça o cadastro e login de um usuário.  
- Acesse a página da loja (`store.html`).  
- Adicione produtos ao carrinho e clique em “Carrinho” para visualizar os itens.

## Licença
Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.



