# 📝 TechTask API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

O **TechTask** é uma API RESTful de gerenciamento de tarefas projetada com arquitetura moderna e padrões de nível corporativo. Este projeto foi desenvolvido para demonstrar o domínio na criação de sistemas back-end seguros, escaláveis e bem documentados utilizando o ecossistema Spring.

## 🚀 O Projeto e a Arquitetura

O TechTask é um projeto prático de estudos desenvolvido para consolidar conhecimentos avançados no ecossistema Spring. O objetivo principal desta API não é apenas executar um CRUD básico, mas sim desenhar e implementar uma arquitetura de software robusta que atenda aos padrões exigidos pelo mercado corporativo. 

Durante o desenvolvimento, o foco foi resolver problemas reais de segurança e regras de negócio, como:

- **Isolamento de Dados (Multi-tenancy):** Garantir que usuários acessem e modifiquem estritamente os seus próprios dados.
- **Segurança Stateless:** Implementação de autenticação robusta via Tokens JWT e criação de filtros de segurança customizados para interceptar requisições.
- **Proteção de Dados Sensíveis:** Utilização do padrão DTO (Data Transfer Object) para evitar o vazamento de credenciais e informações internas na resposta das requisições.

## ⚙️ Funcionalidades

- **Autenticação e Autorização:** Login de usuários com geração de Token JWT (expiração controlada).
- **Criptografia de Senhas:** Senhas salvas no banco de dados utilizando hash BCrypt.
- **Gestão de Usuários:** Cadastro seguro de novos usuários na plataforma.
- **Gestão de Tarefas (CRUD):** Criação, listagem, atualização e exclusão de tarefas.
- **Vínculo de Entidades:** Relacionamento `ManyToOne` (Muitas tarefas pertencem a um único usuário).
- **Documentação Viva:** Interface gráfica interativa gerada automaticamente pelo Swagger/OpenAPI.

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3** (Web, Data JPA, Validation)
- **Spring Security** (Filtros de interceptação, UserDetails, Stateless Session)
- **Auth0 java-jwt** (Geração e validação de tokens JWT)
- **SpringDoc OpenAPI / Swagger UI** (Documentação da API)
- **MySQL** (Banco de dados relacional)
- **Hibernate / JPA** (Mapeamento Objeto-Relacional)
- **Maven** (Gerenciamento de dependências)

## 📖 Documentação da API (Swagger)

A API possui uma documentação interativa. Para acessá-la em ambiente de desenvolvimento (Local):

1. Clone o repositório e configure o banco de dados MySQL.
2. Inicie a aplicação via sua IDE ou Maven.
3. Acesse no seu navegador: 👉 `http://localhost:8080/swagger-ui/index.html`
   
## 🔌 Endpoints Principais

### Autenticação & Usuários
- `POST /usuarios` - Cadastra um novo usuário no sistema.
- `POST /auth` - Autentica o usuário e devolve o Token JWT.

### Tarefas (Requerem Token JWT)
- `POST /tarefas` - Cria uma nova tarefa vinculada ao usuário logado.
- `GET /tarefas` - Lista as tarefas exclusivas do usuário logado.
- `PUT /tarefas/{id}` - Atualiza uma tarefa (valida a posse da tarefa).
- `DELETE /tarefas/{id}` - Deleta uma tarefa (valida a posse da tarefa).

## 👨‍💻 Autor

**Danilo Dev** Desenvolvedor Back-end Java  
[Meu GitHub](https://github.com/DaniloDev0)
