# 🏥 VidaPlus API

API RESTful desenvolvida com **Spring Boot**, voltada para o gerenciamento de uma rede de saúde. A aplicação oferece controle completo sobre usuários, pacientes, profissionais de saúde, unidades, agendamento médico e videochamadas.

---

## 📚 Descrição

**VidaPlus** é uma plataforma pensada para hospitais, clínicas, laboratórios e serviços de home care. Seu objetivo é centralizar o gerenciamento de atendimentos médicos, proporcionando integração entre setores, segurança dos dados e agilidade no acesso às informações.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **Swagger (SpringDoc OpenAPI)**
- **Banco de Dados**: PostgreSQL *(ou outro — altere conforme seu projeto)*

---

## 🔐 Autenticação

Autenticação baseada em JWT.

### Endpoints de autenticação:
- `POST /login` – Login do usuário  
- `POST /refresh-token` – Renovação do token JWT

---

## 🗂️ Endpoints Principais

### 📅 Agenda Médica
- `GET /vidaPlus/agenda/{id}` – Buscar agenda por ID  
- `GET /vidaPlus/agenda` – Listar todas as agendas  
- `POST /vidaPlus/agenda` – Criar nova agenda  
- `PUT /vidaPlus/agenda/{id}` – Atualizar agenda  
- `DELETE /vidaPlus/agenda/{id}` – Deletar agenda  

### 👤 Usuário
- `POST /vidaPlus/user/register` – Registrar novo usuário  
- `GET /vidaPlus/user/{id}` – Buscar usuário por ID  
- `DELETE /vidaPlus/user/{id}` – Deletar usuário  

### 🏥 Unidade de Atendimento
- `GET /vidaPlus/unidade` – Listar unidades  
- `POST /vidaPlus/unidade` – Criar unidade  
- `GET /vidaPlus/unidade/{id}` – Buscar unidade por ID  

### 🧍 Paciente
- `GET /vidaPlus/paciente` – Listar pacientes  
- `POST /vidaPlus/paciente` – Cadastrar paciente  
- `GET /vidaPlus/paciente/{id}` – Buscar paciente por ID  
- `PUT /vidaPlus/paciente/{id}` – Atualizar dados  
- `DELETE /vidaPlus/paciente/{id}` – Deletar paciente  

### 🧑‍⚕️ Profissional de Saúde
- `GET /vidaPlus/profissional-saude` – Listar profissionais  
- `POST /vidaPlus/profissional-saude` – Cadastrar profissional  
- `GET /vidaPlus/profissional-saude/{id}` – Buscar por ID  
- `PUT /vidaPlus/profissional-saude/{id}` – Atualizar  
- `DELETE /vidaPlus/profissional-saude/{id}` – Remover  

### 📹 Videochamadas
- `GET /vidaPlus/videochamada` – Listar videochamadas  
- `GET /vidaPlus/videochamada/{id}` – Buscar por ID  
- `PUT /vidaPlus/videochamada/{id}` – Atualizar observações  


Através dela é possível testar os endpoints e visualizar os contratos da API de forma intuitiva.

---

## 🧰 Como Rodar o Projeto Localmente

### Pré-requisitos

- Java 17+  
- Maven  
- PostgreSQL (ou o banco de sua escolha)  

### Passos

```bash
# Clone o repositório
git clone https://github.com/SeuUsuario/VidaPlus.git

# Acesse o diretório
cd VidaPlus

# Configure o application.properties ou application.yml com suas credenciais de banco

# Compile o projeto
./mvnw clean install

# Execute a aplicação
./mvnw spring-boot:run
