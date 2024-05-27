# FoodDeliveryAPI

# Sobre o projeto

API REST de uma aplicação para o serviço de entregas de restaurantes.<br>
<br>
A motivação desse projeto veio a partir do interesse em entender como funcionam esses grandes serviços já estabelecidos no mercado, trazendo como maior objeto de interesse o aprendizado em como se aplica um processo de busca por restaurantes a partir da localização de um usuário assim como a execução prática de testes unitários e de integração.

# Funcionalidades

#Usuarios
- Cadastro de conta Usuário
- Atualização dos dados de um Usuário
- Deleção de Conta
  
#Lojas
- Cadastro de conta Loja
- Busca por dados de uma Loja
- Busca por nome de Lojas
- Busca por Lojas que entregam em determinada localidade
- Atualização dos dados de uma Loja
- Deleção de Conta

#Produtos
- Cadastro de Produtos
- Atualização dos dados de um Produto
- Deleção de um Produto

# Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.5
- Spring Secutiry / JWT
- JPA / Hibernate
- Maven
- PostgreSQL
- JUnit / Mockito
- OpenAPI (Swagger)

A API poderá ser acessada em http://localhost:8080

# Como executar o projeto

```bash
# clonar repositório
git clone https://github.com/lucascamposdev/FoodDeliveryAPI.git

# executar o projeto (Docker)
docker compose up --build

# executar os testes
mvn test
```

## API Endpoints

A maior parte das funcionalidades exige que o usuário esteja autenticado utilizando token JWT.

O Swagger contendo todos os endpoints e como utiliza-los poderá ser acessado em: http://localhost:8080/swagger-ui.html

# Autor

Lucas Campos

https://www.linkedin.com/in/lucascamposdev/


