# Documentação Completa do SGP (Sistema de Gerenciamento de Protocolos)

## Visão Geral
O SGP (Sistema de Gerenciamento de Protocolos) é uma aplicação que permite o gerenciamento de protocolos com diferentes estados. Esta documentação fornece uma visão geral completa de todas as partes significativas do projeto, incluindo frontend e backend, destacando estrutura, modelos, controladores, serviços, configurações e arquivos relevantes.

## Sumário
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelos](#modelos)
- [Controladores](#controladores)
- [Serviços](#serviços)
- [Tratamento de Erros](#tratamento-de-erros)
- [Configuração do Swagger](#configuração-do-swagger)
- [Frontend](#frontend)
    - [Estrutura do Frontend](#estrutura-do-frontend)
    - [Arquivos CSS](#arquivos-css)
    - [Arquivos HTML](#arquivos-html)
    - [Arquivos JavaScript](#arquivos-javascript)
    - [Configuração](#configuração-frontend)
    - [Arquivo Favicon](#arquivo-favicon)

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

- `backend`: Contém o código-fonte do backend da aplicação, incluindo controladores, modelos, serviços e configurações.
- `frontend`: Contém o código-fonte do frontend da aplicação, incluindo arquivos CSS, HTML, JavaScript e configurações.

## Modelos
### Protocolo
A classe Protocolo representa um protocolo no sistema. Ela possui os seguintes atributos:
- `id`: Um identificador exclusivo do protocolo.
- `protocolo`: O número de protocolo gerado automaticamente.
- `requerente`: O requerente associado ao protocolo.
- `status`: O status atual do protocolo (PENDENTE, DEFERIDO, INDEFERIDO).
- `descricao`: Uma descrição do protocolo.
- `data`: A data de criação do protocolo.

### Requerente
A classe Requerente representa um requerente no sistema. Ela possui os seguintes atributos:
- `id`: Um identificador exclusivo do requerente.
- `nome`: O nome do requerente.
- `endereco`: O endereço do requerente.
- `email`: O endereço de e-mail do requerente.
- `telefone`: O número de telefone do requerente.

### EnumStatus
A enumeração EnumStatus define os possíveis estados de status que um protocolo pode ter. Ela possui três valores de enumeração: PENDENTE, DEFERIDO e INDEFERIDO.

## Controladores
### ProtocoloController
A classe ProtocoloController define os endpoints da API relacionados a protocolos. Alguns dos endpoints incluem:
- `GET /protocolo/busca-todos`: Retorna todos os protocolos cadastrados.
- `GET /protocolo/{id}`: Retorna um protocolo específico com base em seu ID.
- `POST /protocolo/salvar`: Salva um novo protocolo.
- `PUT /protocolo/{id}/mudar-status`: Altera o status de um protocolo.

### RequerenteController
A classe RequerenteController define os endpoints da API relacionados a requerentes. Alguns dos endpoints incluem:
- `GET /requerente/busca-todos`: Retorna todos os requerentes cadastrados.
- `GET /requerente/{id}`: Retorna um requerente específico com base em seu ID.
- `POST /requerente/salvar`: Salva um novo requerente.

## Serviços
### ProtocoloService
A interface ProtocoloService define os serviços relacionados a protocolos. Alguns dos métodos incluem:
- `buscarTodos()`: Retorna todos os protocolos cadastrados.
- `buscarPorId(Long id)`: Retorna um protocolo específico com base em seu ID.
- `salvar(Protocolo protocolo)`: Salva um novo protocolo.
- `mudarStatus(Long id, EnumStatus status)`: Altera o status de um protocolo.

### RequerenteService
A interface RequerenteService define os serviços relacionados a requerentes. Alguns dos métodos incluem:
- `buscarTodos()`: Retorna todos os requerentes cadastrados.
- `buscarPorId(Long id)`: Retorna um requerente específico com base em seu ID.
- `salvar(Requerente requerente)`: Salva um novo requerente.

## Tratamento de Erros
### ProtocoloNotFoundException
A classe `ProtocoloNotFoundException` é uma exceção personalizada que é lançada quando um protocolo não é encontrado na API. Ela é usada para lidar com cenários em que um protocolo específico não existe.

### ProtocoloControllerAdvice
A classe `ProtocoloControllerAdvice` é um controlador de aconselhamento que lida com o tratamento de exceções relacionadas a protocolos. Ela fornece respostas de erro formatadas adequadamente quando ocorrem exceções, como `ProtocoloNotFoundException` e validações de entrada falhadas.

## Configuração do Swagger
### SwaggerConfig
A classe `SwaggerConfig` é responsável por configurar o Swagger, uma ferramenta de documentação interativa para a API. Ela define informações sobre a API, como título, descrição, versão e detalhes de contato. O Swagger permite que os desenvolvedores visualizem e testem a API por meio de uma interface da web amigável.

## Frontend
### Estrutura do Frontend
O frontend do SGP está organizado da seguinte forma:

```
frontend
├── css
│ ├── adicionar_protocolo.css
│ ├── editar_protocolo.css
│ ├── fontawesome-free-5.3.1-web_all.min.css
│ └── style.css
├── html
│ ├── adicionar_protocolo.html
│ ├── editar_protocolo.html
│ └── visualizar_protocolo.html
├── js
│ ├── fancybox
│ │ └── (arquivos do plugin Fancybox)
│ ├── util
│ │ ├── adicionar_protocolo.js
│ │ ├── editar_protocolo.js
│ │ └── visualizar_protocolo.js
│ ├── Chart-2.9.4.js
│ ├── graficos.js
│ ├── html_element_creator.js
│ ├── jquery.mask.min.js
│ ├── jquery.min.js
│ ├── json_utils.js
│ ├── popup.js
│ ├── sgp.js
│ └── telefone_mask.js
├── webfonts
│ └── (arquivos de fonte utilizados na aplicação)
├── config.js
├── favicon.ico
├── index.html
└── README.md
```

### Arquivos CSS
- **adicionar_protocolo.css**: Estilos específicos para a página de adição de protocolo.
- **editar_protocolo.css**: Estilos específicos para a página de edição de protocolo.
- **fontawesome-free-5.3.1-web_all.min.css**: Arquivo CSS contendo ícones da biblioteca Font Awesome.
- **style.css**: Estilos gerais compartilhados entre as páginas da aplicação.

### Arquivos HTML
- **adicionar_protocolo.html**: Página para adição de novos protocolos.
- **editar_protocolo.html**: Página para edição de protocolos existentes.
- **visualizar_protocolo.html**: Página para visualização detalhada de um protocolo.

### Arquivos JavaScript
- **fancybox/**: Pasta contendo arquivos do plugin Fancybox para exibição de imagens em um lightbox.
- **util/**: Pasta contendo scripts JavaScript utilitários para as páginas específicas.
- **Chart-2.9.4.js**: Biblioteca Chart.js para criação de gráficos na aplicação.
- **graficos.js**: Script responsável pela criação e atualização dos gráficos na página.
- **html_element_creator.js**: Script para criação dinâmica de elementos HTML na página.
- **jquery.mask.min.js**: Plugin jQuery para máscaras de entrada em campos de formulário.
- **jquery.min.js**: Biblioteca jQuery para manipulação do DOM e interações JavaScript.
- **json_utils.js**: Funções utilitárias para manipulação de objetos JSON.
- **popup.js**: Script para exibição de pop-ups na aplicação.
- **sgp.js**: Script principal que contém a lógica de interação com o backend e atualização da interface do usuário.
- **telefone_mask.js**: Script para aplicação de máscara de telefone em campos de formulário.

### Configuração Frontend
- **config.js**: Arquivo de configuração da aplicação contendo variáveis globais e configurações específicas.

### Arquivo Favicon
- **favicon.ico**: Ícone da aplicação exibido na barra de navegação do navegador.

Essa documentação fornece uma visão geral completa da estrutura e funcionamento do Sistema de Gerenciamento de Protocolos, abrangendo tanto o backend quanto o frontend. Cada parte desempenha um papel importante na construção e operação do sistema, garantindo uma experiência de usuário eficiente e intuitiva.
