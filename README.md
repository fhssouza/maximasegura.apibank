
# API Bank - Teste para Desenvolvedor Back-End Java Jr


## Objetivo

Este repositório contém a implementação de uma API em Java para gerenciar usuários e transações financeiras, como parte de um processo seletivo para desenvolvedor Back-End Java Jr.

## Requisitos Técnicos

- **Persistência**: O padrão DAO utilizando SQL nativo para persistir os dados no banco de dados H2 em memória.
- **Framework**: Spring Boot com suporte a APIs RESTful.
- **Validações**: Utilização de classes de validação para garantir a integridade dos dados, conforme os requisitos de negócios.

## Estrutura do Projeto

O projeto foi estruturado com base nas boas práticas de desenvolvimento, utilizando princípios como SOLID, DRY e Clean Code.

### Estrutura de Pacotes

- `com.maximaseguranca.apibank.controller`: Contém os controladores REST da API, responsáveis por expor os endpoints.
- `com.maximaseguranca.apibank.service`: Contém a lógica de negócio da aplicação, como cadastro de usuários e transações financeiras.
- `com.maximaseguranca.apibank.model`: Contém os modelos (entidades) da aplicação, como `Usuario` e `Transacao`.
- `com.maximaseguranca.apibank.repository`: Contém as interfaces de persistência, seguindo o padrão DAO.
- `com.maximaseguranca.apibank.exception`: Contém as exceções personalizadas.
- `com.maximaseguranca.apibank.dto`: Contém os DTOs (Data Transfer Objects) utilizados nas trocas de dados entre camadas.

## Endpoints

### 1. Criar Usuário
**Método**: `POST /api/users`

**Corpo da requisição**:
```json
{
  "nome": "João Silva",
  "idade": 25,
  "cpf": "123.456.789-10"
}
```

**Resposta de Sucesso**:
```json
{
  "id": 1,
  "nome": "João Silva",
  "idade": 25,
  "cpf": "123.456.789-10",
  "numeroConta": "000123",
  "saldo": 0.00
}
```

**Resposta de Erro**:
```json
{
  "error": "CPF já cadastrado."
}
```

### 2. Consultar Usuário por ID
**Método**: `GET /api/users/{id}`

**Resposta de Sucesso**:
```json
{
  "id": 1,
  "nome": "João Silva",
  "idade": 25,
  "cpf": "123.456.789-10",
  "numeroConta": "000123",
  "saldo": 150.00
}
```

**Resposta de Erro**:
```json
{
  "error": "Usuário não encontrado."
}
```

### 3. Listar Todos os Usuários
**Método**: `GET /api/users`

**Resposta de Sucesso**:
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10",
    "numeroConta": "000123",
    "saldo": 150.00
  },
  {
    "id": 2,
    "nome": "Fabio Souza",
    "idade": 35,
    "cpf": "674.456.789-21",
    "numeroConta": "000456",
    "saldo": 800.00
  }
]
```

### 4. Realizar Transferência
**Método**: `POST /api/transactions`

**Corpo da requisição**:
```json
{
  "contaOrigem": "000123",
  "contaDestino": "000456",
  "valor": 100.00
}
```

**Resposta de Sucesso**:
```json
{
  "mensagem": "Transferência realizada com sucesso."
}
```

**Resposta de Erro** (Saldo Insuficiente):
```json
{
  "error": "Saldo insuficiente para a transação."
}
```

### 5. Realizar Depósito
**Método**: `POST /api/transactions/deposits`

**Corpo da requisição**:
```json
{
  "numeroConta": "000123",
  "valor": 100.00
}
```

**Resposta de Sucesso**:
```json
{
  "mensagem": "Depósito realizado com sucesso."
}
```

**Resposta de Erro** (Valor Inválido ou Conta Não Encontrada):
```json
{
  "error": "O valor do depósito deve ser maior que 0.00."
}
```

### 6. Realizar Saque
**Método**: `POST /api/transactions/withdraw`

**Corpo da requisição**:
```json
{
  "numeroConta": "000123",
  "valor": 50.00
}
```

**Resposta de Sucesso**:
```json
{
  "mensagem": "Saque realizado com sucesso."
}
```

**Resposta de Erro** (Saldo Insuficiente ou Valor Inválido):
```json
{
  "error": "Saldo insuficiente para o saque."
}
```

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção da API RESTful.
- **H2 Database**: Banco de dados em memória para persistência.
- **Swagger**: Para documentação automática da API.
- **JUnit**: Para testes unitários.

## Como Executar o Projeto

### Pré-requisitos

- Java 17.
- Maven.

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/fhssouza/maximasegura.apibank
cd seu_repositorio
```

2. Compile e execute o projeto:

- Usando Maven:

```bash
mvn clean install
mvn spring-boot:run
```


3. Acesse a API em `http://localhost:8080`.

4. Utilize o Swagger para testar os endpoints em `http://localhost:8080/swagger-ui.html`.

## Decisões Técnicas

- **DAO com SQL Nativo**: A persistência é feita utilizando o padrão DAO com SQL nativo para garantir a simplicidade e facilitar a integração com bancos de dados em produção.
- **Validação de Dados**: A validação de dados foi centralizada em classes de serviço e validadores, aplicando boas práticas de SOLID, como a separação de responsabilidades.
- **Swagger**: O Swagger foi utilizado para documentar automaticamente a API, tornando mais fácil para os desenvolvedores entenderem como interagir com os endpoints.

## Conclusão

Este projeto tem como objetivo demonstrar a capacidade de desenvolver uma API RESTful robusta, com boas práticas de código e tratamento adequado de erros. A implementação segue as melhores práticas de arquitetura de software e busca garantir um código limpo e fácil de manter.
