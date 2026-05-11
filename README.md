# 🍽️ Reservation API

Sistema de reservas de mesas para restaurante desenvolvido com Java 21 e Spring Boot.

## 📋 Sobre o Projeto

API RESTful para gerenciamento de reservas de mesas em um restaurante. O sistema permite que usuários autenticados realizem reservas, com controle de disponibilidade por horário, validação de capacidade e cancelamento de reservas.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3**
- **Spring Security** com autenticação JWT
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL**
- **Flyway** para migrations
- **MapStruct** para mapeamento de objetos
- **Docker** + **Docker Compose**
- **Springdoc OpenAPI** (Swagger)

## ⚙️ Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Docker](https://www.docker.com/)

## 🐳 Rodando com Docker

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/reservation-api.git
cd reservation-api
```

2. Crie o arquivo `.env` na raiz do projeto com base no `.env.example`:
```bash
cp .env.example .env
```

3. Preencha as variáveis no `.env`:
```env
POSTGRES_DB=reservationapi
DB_USER=postgres
DB_PASSWORD=postgres
JWT_SECRET=sua_chave_secreta
DB_URL=jdbc:postgresql://postgres:5432/reservationapi
```

4. Suba os containers:
```bash
docker-compose up --build
```

A API estará disponível em `http://localhost:8080/reservation/v1`

## 📖 Documentação

Com a aplicação rodando, acesse o Swagger em:

```
http://localhost:8080/reservation/v1/swagger-ui/index.html
```

## 🔐 Autenticação

A API utiliza JWT. Para acessar os endpoints protegidos:

1. Registre um usuário em `POST /auth/register`
2. Faça login em `POST /auth/login` e copie o token retornado
3. Nas requisições protegidas, envie o header:
```
Authorization: Bearer {token}
```

## 📌 Endpoints

### Autenticação
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/auth/register` | Registrar novo usuário | ❌ |
| POST | `/auth/login` | Login e geração de token | ❌ |

### Mesas
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/tables` | Listar todas as mesas | ✅ |
| POST | `/tables` | Criar nova mesa | ✅ Admin |
| PATCH | `/tables/:id/status` | Atualizar status da mesa | ✅ Admin |

### Reservas
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/reservations` | Criar reserva | ✅ |
| GET | `/reservations` | Listar reservas | ✅ |
| PATCH | `/reservations/:id/cancel` | Cancelar reserva | ✅ |

## 🏗️ Arquitetura

O projeto segue os princípios da **Arquitetura Hexagonal (Ports & Adapters)**:

```
src/
├── adapters/
│   ├── in/
│   │   ├── web/          # Controllers (REST)
│   │   └── security/     # Filtros e contexto de segurança
│   └── out/
│       └── persistence/  # Implementações dos repositórios
├── application/
│   ├── dto/              # Request e Response objects
│   ├── mapper/           # Mapeadores MapStruct
│   └── usecase/          # Casos de uso (regras de negócio)
├── config/               # Configurações (Security, Business, Swagger)
└── domain/
    ├── enums/            # Enumerações de domínio
    ├── exception/        # Exceções de domínio
    ├── model/            # Entidades de domínio
    └── port/
        └── out/          # Interfaces dos repositórios
```

## 📏 Regras de Negócio

- Reservas só podem ser feitas dentro do horário de funcionamento do restaurante
- A duração padrão de uma reserva é de **3 horas**
- O sistema valida conflito de horário antes de confirmar uma reserva
- A mesa é bloqueada automaticamente ao ser reservada
- Ao cancelar uma reserva, a mesa volta a ficar disponível
- Apenas administradores podem criar e gerenciar mesas

## 🧪 Testes

Para rodar os testes:

```bash
./mvnw test
```

Para gerar o relatório de cobertura:

```bash
./mvnw test jacoco:report
```

O relatório estará em `target/site/jacoco/index.html`

## 📄 Licença

Este projeto foi desenvolvido como desafio prático de programação.