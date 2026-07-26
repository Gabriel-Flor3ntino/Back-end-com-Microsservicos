# Loja - Sistema de E-commerce

Um sistema de e-commerce modular construído com Spring Boot, composto por múltiplos microsserviços.

## 📦 Estrutura do Projeto

```
loja/
├── user-api/          # Serviço de gerenciamento de usuários
├── product-api/       # Serviço de gerenciamento de produtos
├── shopping-api/      # Serviço de carrinho de compras
├── shopping-client/   # Cliente para integração com o serviço de compras
├── postgres-configuration/  # Configurações do banco de dados
└── docker-compose.yaml      # Orquestração dos serviços com Docker
```

## 🛠️ Tecnologias

- **Java** com **Spring Boot 4.1.0**
- **Maven** para gerenciamento de dependências
- **PostgreSQL** como banco de dados
- **Docker** e **Docker Compose** para containerização
- **APIs RESTful** para comunicação entre serviços

## 🚀 Serviços

| Serviço | Porta | Descrição |
|---------|-------|-----------|
| `user-api` | 8080 | Gerenciamento de usuários |
| `product-api` | 8081 | Gerenciamento de produtos |
| `shopping-api` | 8082 | Carrinho de compras |

## ⚙️ Pré-requisitos

- Docker e Docker Compose instalados
- Java 17+ (para desenvolvimento local)
- Maven 3.6+

## 🏃‍♂️ Como Rodar

### Usando Docker Compose (Recomendado)

```bash
# Iniciar todos os serviços
docker-compose up -d

# Verificar status dos containers
docker-compose ps

# Parar todos os serviços
docker-compose down
```

### Desenvolvimento Local

Cada módulo é um projeto Maven independente. Para rodar localmente:

```bash
# User API
cd user-api
./mvnw spring-boot:run

# Product API
cd product-api
./mvnw spring-boot:run

# Shopping API
cd shopping-api
./mvnw spring-boot:run
```

## 🔧 Variáveis de Ambiente

O `docker-compose.yaml` configura as seguintes variáveis:

- `POSTGRES_URL`: URL de conexão com o PostgreSQL
- `POSTGRES_USER`: Usuário do banco de dados (padrão: `postgres`)
- `POSTGRES_PASSWORD`: Senha do banco de dados (padrão: `postgres`)
- `PRODUCT_API_URL`: URL da API de produtos (para shopping-api)
- `USER_API_URL`: URL da API de usuários (para shopping-api)

## 🗄️ Banco de Dados

- **Host**: localhost
- **Porta**: 5432
- **Database**: dev
- **Usuário**: postgres
- **Senha**: postgres

## 🤝 Comunicação entre Serviços

Os serviços se comunicam via HTTP REST:

- `shopping-api` consome `user-api` e `product-api`
- Cada serviço possui seu próprio contexto delimitado

## 📝 Licença

Este projeto é de uso interno/demonstrativo.

## 👨‍💻 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

**Desenvolvido com ☕ Java e Spring Boot**
