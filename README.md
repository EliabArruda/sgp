# Documentação da API SGP (Sistema de Gestão de Protocolos)

## Visão Geral
A API SGP (Sistema de Gestão de Protocolos) é um sistema que permite a gestão de protocolos com diferentes estados. Este documento fornece uma visão geral de todas as partes significativas do código-fonte do projeto, incluindo controladores, serviços, modelos e configurações.

## Sumário
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelos](#modelos)
- [Controladores](#controladores)
- [Serviços](#serviços)
- [Tratamento de Erros](#tratamento-de-erros)
- [Configuração do Swagger](#configuração-do-swagger)

## Estrutura do Projeto
O projeto está organizado de acordo com as melhores práticas do Spring Boot, com foco na modularidade e clareza do código. Abaixo está a estrutura do projeto:
```css
src
├── main
│   ├── java
│   │   └── com
│   │       └── eliab
│   │           └── sistemas
│   │               └── sgp
│   │                   ├── controller
│   │                   ├── exception
│   │                   ├── handle
│   │                   ├── model
│   │                   ├── repository
│   │                   └── service
│   ├── resources
│   └── ...
```
Aqui está uma breve descrição de cada pacote:

- `controller`: Contém os controladores que definem os endpoints da API.
- `exception`: Contém exceções personalizadas usadas para tratamento de erros.
- `handle`: Contém classes relacionadas ao tratamento de erros.
- `model`: Define os modelos de dados usados no aplicativo.
- `repository`: Contém interfaces de repositório para interagir com o banco de dados.
- `service`: Contém interfaces e implementações de serviços.

## Modelos
### Protocolo
A classe Protocolo representa um protocolo no sistema. Ela possui os seguintes atributos:
- `id`: Um identificador exclusivo do protocolo.
- `protocolo`: O número de protocolo gerado automaticamente.
- `requerente`: O requerente associado ao protocolo.
- `status`: O status atual do protocolo (PENDENTE, DEFERIDO, INDEFERIDO).
- `descricao`: Uma descrição do protocolo.
- `data`: A data de criação do protocolo.

Esta classe é usada para representar as informações principais de um protocolo e é persistida no banco de dados.

### Requerente
A classe Requerente representa um requerente no sistema. Ela possui os seguintes atributos:
- `id`: Um identificador exclusivo do requerente.
- `nome`: O nome do requerente.
- `endereco`: O endereço do requerente.
- `email`: O endereço de e-mail do requerente.
- `telefone`: O número de telefone do requerente.

Esta classe é usada para representar as informações de um requerente e é persistida no banco de dados.

### EnumStatus
A enumeração EnumStatus define os possíveis estados de status que um protocolo pode ter. Ela possui três valores de enumeração: PENDENTE, DEFERIDO e INDEFERIDO.

## Controladores
### ProtocoloController
A classe ProtocoloController define os endpoints da API relacionados a protocolos. Alguns dos endpoints incluem:
- `GET /protocolo/busca-todos`: Retorna todos os protocolos cadastrados.
- `GET /protocolo/{id}`: Retorna um protocolo específico com base em seu ID.
- `POST /protocolo/salvar`: Salva um novo protocolo.
- `PUT /protocolo/{id}/mudar-status`: Altera o status de um protocolo.

Esta classe também lida com validações de entrada, tratamento de erros e interações com os serviços relacionados a protocolos.

### RequerenteController
A classe RequerenteController define os endpoints da API relacionados a requerentes. Alguns dos endpoints incluem:
- `GET /requerente/busca-todos`: Retorna todos os requerentes cadastrados.
- `GET /requerente/{id}`: Retorna um requerente específico com base em seu ID.
- `POST /requerente/salvar`: Salva um novo requerente.

Esta classe também lida com validações de entrada, tratamento de erros e interações com os serviços relacionados a requerentes.

## Serviços
### ProtocoloService
A interface ProtocoloService define os serviços relacionados a protocolos. Alguns dos métodos incluem:
- `buscarTodos()`: Retorna todos os protocolos cadastrados.
- `buscarPorId(Long id)`: Retorna um protocolo específico com base em seu ID.
- `salvar(Protocolo protocolo)`: Salva um novo protocolo.
- `mudarStatus(Long id, EnumStatus status)`: Altera o status de um protocolo.

Esta interface define operações que podem ser realizadas em protocolos e é implementada pela classe `ProtocoloServiceImplementacao`.

### RequerenteService
A interface RequerenteService define os serviços relacionados a requerentes. Alguns dos métodos incluem:
- `buscarTodos()`: Retorna todos os requerentes cadastrados.
- `buscarPorId(Long id)`: Retorna um requerente específico com base em seu ID.
- `salvar(Requerente requerente)`: Salva um novo requerente.

Esta interface define operações que podem ser realizadas em requerentes e é implementada pela classe `RequerenteServiceImplementacao`.

## Tratamento de Erros
### ProtocoloNotFoundException
A classe `ProtocoloNotFoundException` é uma exceção personalizada que é lançada quando um protocolo não é encontrado na API. Ela é usada para lidar com cenários em que um protocolo específico não existe.

### ProtocoloControllerAdvice
A classe `ProtocoloControllerAdvice` é um controlador de aconselhamento que lida com o tratamento de exceções relacionadas a protocolos. Ela fornece respostas de erro formatadas adequadamente quando ocorrem exceções, como `ProtocoloNotFoundException` e validações de entrada falhadas.

## Configuração do Swagger
### SwaggerConfig
A classe `SwaggerConfig` é responsável por configurar o Swagger, uma ferramenta de documentação interativa para a API. Ela define informações sobre a API, como título, descrição, versão e detalhes de contato. O Swagger permite que os desenvolvedores visualizem e testem a API por meio de uma interface da web amigável.

Esta documentação fornece uma visão geral completa da estrutura e funcionamento da API SGP.
