# Mobiauto - Desafio Backend
Este projeto é parte de um desafio para desenvolvedores backend, focado na criação de um sistema de gestão para revendas de veículos.

## Tecnologias Utilizadas
Java 21\
Spring Boot 3.3.1\
Spring Security\
Spring Data JPA\
Hibernate ORM\
PostgreSQL\
Lombok 1.18.32\
MapStruct 1.5.5.Final\
Auth0 Java JWT 4.4.0

Pré-requisitos:
JDK 21 ou superior\
Maven 3.8.x\
PostgreSQL 16.x\
IDE compatível com Java (Eclipse, IntelliJ IDEA, etc.)\
Configuração do Ambiente\
Certifique-se de configurar o banco de dados PostgreSQL e ajustar as configurações de conexão no arquivo application.properties do Spring Boot.

## Configurando ambiente:

Clone o repositório do GitHub:
```bash
git clone https://github.com/CaBezOf/mobiauto-backend-interview.git
```

Build do projeto no Docker:
```bash
docker-compose up --build
```

Link imagem Docker Hub:
```link
https://hub.docker.com/r/kaiko94/desafiomobiauto
```


## Exemplos de Payloads
Aqui estão exemplos de payloads para operações principais da API:

### Registrar um novo usuário
Endpoint: POST /auth/registrar

```json
{
    "login": "admin",
    "senha": "12345",
    "tipoUsuario": "ADMIN"
}
```

### Login
Endpoint: POST /auth/login

```json
{
    "login": "admin",
    "senha": "12345"
}
```

### Criar uma nova oportunidade
Endpoint: POST /oportunidades

```json
{
    "id": 1,
    "statusOportunidade": "NOVO",
    "motivoConclusao": null,
    "atribuida": false,
    "dataAtribuicao": null,
    "dataConclusao": null,
    "responsavel": null,
    "revenda": {
        "id": 1,
        "cnpj": "12345678000195",
        "nomeSocial": "Revenda Carros Top",
        "usuarios": null,
        "oportunidades": null
    },
    "cliente": {
        "id": 1,
        "clienteNome": "João da Silva",
        "clienteEmail": "joao.silva@example.com",
        "clienteTelefone": "(11) 98765-4321"
    },
    "veiculo": {
        "id": 1,
        "marcaVeiculo": "Toyota",
        "modeloVeiculo": "Corolla",
        "anoVeiculo": 2023,
        "versaoVeiculo": "XEi"
    }
}
```

### Atribuir uma oportunidade
Endpoint: POST /oportunidades/atribuir

```json
{
  "id": 1,  
  "responsavel": {
    "id": 1
  }
}
```

### Editar uma oportunidade
Endpoint: PUT /oportunidades/editar

```json
{
  "id": 1,  
  "responsavel": {
    "id": 1
  }
}
```

### Registrar um novo usuário assistente
Endpoint: POST /usuarios

```json
{
  "login": "usuario_assistente",
  "nome": "Fulano de Tal",
  "email": "fulano@example.com",
  "senha": "senha123",
  "tipoUsuario": "ASSISTENTE",
  "tipoUsuarioAtivo": "ADMIN",
  "revenda": {
    "cnpj": "12345678000195",
    "nomeSocial": "Revenda Carros Top"
  }
}
```

## Melhorias sugeridas

### Registrar uma nova revenda
Endpoint: POST /revendas/registrar

```json
{
  "cnpj": "12345678000195",
  "nomeSocial": "Revenda Carros Top"
}
```

## Testes Unitários

1. Teste de Registro de Revenda

  * Verifica se é possível registrar uma nova revenda corretamente.
    
2.Teste de Autenticação

  * Testa se o sistema autentica corretamente um usuário com as credenciais válidas.
    
3.Teste de Atribuição de Oportunidade

  * Valida se a atribuição de uma oportunidade a um assistente ocorre conforme esperado.
    
4. Teste de Edição de Oportunidade

  * Garante que a edição de uma oportunidade seja feita corretamente, atualizando o responsável.
    
5. Teste de Listagem de Usuários

  * Verifica se a listagem de usuários, especialmente assistentes, retorna corretamente os dados esperados.


