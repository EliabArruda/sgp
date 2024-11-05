
# Documentação Completa da API do SGP (Sistema de Gerenciamento de Protocolos)

## Visão Geral
O SGP (Sistema de Gerenciamento de Protocolos) é uma aplicação que permite o gerenciamento de protocolos com diferentes estados. Esta documentação fornece uma visão geral completa do backend, destacando a estrutura, os modelos, os controladores, os serviços e o tratamento de erros.

## Sumário
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelos](#modelos)
- [Controladores](#controladores)
- [Serviços](#serviços)
- [Tratamento de Erros](#tratamento-de-erros)
- [Configuração do Swagger](#configuração-do-swagger)

## Estrutura do Projeto
O projeto está organizado da seguinte forma:

```
backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── eliab
│   │   │           └── sistemas
│   │   │               └── sgp
│   │   │                   ├── controller
│   │   │                   ├── exception
│   │   │                   ├── handle
│   │   │                   ├── model
│   │   │                   ├── repository
│   │   │                   └── service
│   │   └── resources
│   └── ...
```

Aqui está uma breve descrição de cada diretório:
- `controller`: Contém os controladores da aplicação, responsáveis por definir os endpoints.
- `exception`: Define exceções personalizadas para tratamento de erros.
- `handle`: Lida com o tratamento centralizado de exceções.
- `model`: Contém as classes que representam as entidades do sistema.
- `repository`: Define as interfaces de repositório para interação com o banco de dados.
- `service`: Contém as interfaces e implementações de serviços que encapsulam a lógica de negócios.

## Modelos
### Protocolo
A classe `Protocolo` representa um protocolo no sistema. Atributos principais:
- `id`: Identificador exclusivo do protocolo.
- `protocolo`: Número do protocolo gerado automaticamente.
- `requerente`: Requerente associado ao protocolo.
- `status`: Status do protocolo (PENDENTE, DEFERIDO, INDEFERIDO).
- `descricao`: Descrição do protocolo.
- `data`: Data de criação.

### Requerente
A classe `Requerente` representa um requerente no sistema. Atributos principais:
- `id`: Identificador exclusivo do requerente.
- `nome`: Nome do requerente.
- `endereco`: Endereço do requerente.
- `email`: Endereço de e-mail.
- `telefone`: Número de telefone.

### Endereco
A classe `Endereco` representa o endereço de um requerente. Atributos principais:
- `id`: Identificador exclusivo do endereço.
- `cep`: Código postal.
- `logradouro`: Rua ou avenida.
- `uf`: Unidade federativa (estado).
- `localidade`: Cidade.
- `bairro`: Bairro.
- `numeroCasa`: Número da residência.

### EnumStatus
A enumeração `EnumStatus` define os estados possíveis de um protocolo: PENDENTE, DEFERIDO, INDEFERIDO.

## Controladores
### ProtocoloController
Define os endpoints da API relacionados a protocolos:
- `GET /protocolo/busca-todos`: Retorna todos os protocolos.
- `GET /protocolo/{id}`: Retorna um protocolo específico pelo ID.
- `POST /protocolo/salvar`: Salva um novo protocolo.
- `PUT /protocolo/{id}/mudar-status`: Altera o status de um protocolo.

### RequerenteController
Define os endpoints da API para gerenciamento de requerentes:
- `GET /requerente/busca-todos`: Retorna todos os requerentes.
- `GET /requerente/{id}`: Retorna um requerente específico pelo ID.
- `POST /requerente/salvar`: Salva um novo requerente.

## Serviços
### ProtocoloService
Define serviços relacionados a protocolos:
- `buscarTodos()`: Retorna todos os protocolos.
- `buscarPorId(Long id)`: Retorna um protocolo específico pelo ID.
- `salvar(Protocolo protocolo)`: Salva um novo protocolo.
- `mudarStatus(Long id, EnumStatus status)`: Altera o status de um protocolo.

### RequerenteService
Define serviços para gerenciamento de requerentes:
- `buscarTodos()`: Retorna todos os requerentes.
- `buscarPorId(Long id)`: Retorna um requerente específico pelo ID.
- `salvar(Requerente requerente)`: Salva um novo requerente.

### EnderecoService
Define os serviços para o gerenciamento de endereços:
- `buscarTodos()`: Retorna todos os endereços cadastrados.
- `buscarPorId(Long id)`: Retorna um endereço específico pelo ID.
- `salvar(Endereco endereco)`: Salva um novo endereço.
- `deletarTodos()`: Exclui todos os endereços.

### Implementação dos Serviços
A implementação dos serviços para cada entidade lida com as operações descritas, encapsulando a lógica de interação com o banco de dados e lidando com exceções, como o `EnderecoNotFoundException` para casos em que o endereço não é encontrado.

## Tratamento de Erros
### ProtocoloNotFoundException e EnderecoNotFoundException
Exceções personalizadas para protocolos e endereços, lançadas quando uma entidade específica não é encontrada.

### ProtocoloControllerAdvice
Controlador de aconselhamento para tratamento de exceções, fornecendo respostas de erro formatadas quando ocorrem falhas de validação ou a entidade não é encontrada.

## Configuração do Swagger
### SwaggerConfig
Configura o Swagger para documentação interativa da API, com informações como título, descrição, versão e contato. O Swagger oferece uma interface web para visualização e teste da API.
