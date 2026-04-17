# Spring Boot CRUD Tarefas

API RESTful desenvolvida com **Spring Boot** para gerenciamento de tarefas (Tasks), com autenticação de usuários e validação de propriedade (ownership).

## Sobre o Projeto

Esta é uma aplicação backend completa de CRUD que permite:
- Cadastro, login e gerenciamento de usuários
- Criação e gerenciamento de **Tipos de Tarefas** (TaskType)
- Criação, atualização, listagem e exclusão de **Tarefas** (Task)
- Validação de propriedade: um usuário só pode visualizar/alterar suas próprias tarefas e tipos
- Paginação e boas práticas de API REST

### Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA** + Hibernate
- **Spring Security**
- **Bean Validation** (Jakarta Validation)
- **MapStruct** (mapeamento de DTOs)
- **Lombok**
- **H2** (Banco em memória)
- **Maven**

## Funcionalidades Principais

- Autenticação e extração do `userId` via `RequestAttribute`
- Validador centralizado de propriedade (`OwnershipValidator`)
- Tratamento global de exceções (`GlobalExceptionHandler`)
- Paginação com `Pageable`

## Estrutura do Projeto
```bash
src/main/java/com/gabriel_sousa/crud_tarefas/
├── controller/          ← Controllers REST
├── dto/                 ← DTOs de request e response
├── entity/              ← Entidades JPA (UserEntity, TaskEntity, TaskTypeEntity)
├── exception/           ← Exceções customizadas
├── mapper/              ← Mappers MapStruct
├── repository/          ← Repositórios Spring Data JPA
├── security/            ← Configuração de segurança e RequestAttribute
├── service/             ← Camada de serviços
├── validation/          ← Validadores de negócio (OwnershipValidator)
└── CrudTarefasApplication.java
```

## Endpoints da API

A API segue o padrão REST e utiliza autenticação via Spring Security.  
Todos os endpoints (exceto login/cadastro) exigem autenticação e recebem o `userId` automaticamente via `@RequestAttribute`.

Base URL: `http://localhost:8080`

### TaskType (Tipos de Tarefa)

| Método     | Endpoint                                             | Descrição                                      | 
|------------|------------------------------------------------------|------------------------------------------------|
| `POST`     | `/taskTypes`                                         | Criar um novo tipo de tarefa                   |
| `GET`      | `/taskTypes`                                         | Listar os tipos de tarefa do usuário(Paginados)|
| `PUT`      | `/taskTypes/{taskTypeId}`                            | Atualizar um tipo de tarefa (completo)         | 
| `DELETE`   | `/taskTypes/{taskTypeId}`                            | Excluir um tipo de tarefa                      | 

### Task (Tarefa)

| Método     | Endpoint                                             | Descrição                                      | 
|------------|------------------------------------------------------|------------------------------------------------|
| `POST`     | `/tasks`                                             | Criar uma nova        tarefa                   |
| `GET`      | `/tasks`                                             | Listar as tarefa do usuário(Paginadas)         |
| `PUT`      | `/tasks/{taskTypeId}`                                | Atualizar a tarefa (completo)                  | 
| `PATCH`    | `/conclude-task/{taskId}`                            | Concluir tarefa                                | 
| `DELETE`   | `/tasks/{taskTypeId}`                                | Excluir a tarefa                               | 

### Observações importantes

- **Validação de Propriedade**: Todos os endpoints de alteração e exclusão verificam se o usuário autenticado é o dono do recurso através do `OwnershipValidator`.
- **Paginação**: O endpoint `GET /taskTypes/` e `GET /tasks`  suportam paginação com os parâmetros padrão do Spring (`?page=0&size=10&sort=name,asc`).
- **Tratamento de Erros**: Erros de validação retornam status **400 Bad Request** e erros de permissão retornam **403 Forbidden**.

