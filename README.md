# Projeto CRUD - Clientes

Este repositório contém uma aplicação Spring Boot contemplando toda a parte do backend do que seria uma aplicação CRUD para clientes. Utiliza de serviços de geolocalização de IP e clima para recuperar informações e associar aos clientes cadastrados. 

# **O projeto**

O projeto foi desenvolvido no IntelliJ IDEA. Esta IDE tem uma ótima integração com o Spring e Maven, assim como inteligência de código referência. Para a geração do projeto, foi utilizada a ferramenta Spring Initializr (integrada com a IDE).

## Spring Boot

Para a aplicação em si. Roda o servidor de forma que outras configurações de servidores externas não sejam necessárias, assim temos uma aplicação que rodaria em qualquer máquina.

## H2 database - Banco de dados

Banco de dados "in-memory". Configuração rápida, confiável e suave integração com o Spring Data (JPA).

## Maven

Gerenciamento de dependências e build.

## Swagger

Documentação de toda parte REST da aplicação. Além de documentar, é possível testar os controladores (métodos) de toda a aplicação através de uma interface amigável no contexto: /swagger-ui.html.

## IntelliJ IDEA

IDE utilizada para desenvolvido. Versão 2017.2. Ótima integração com Spring e Maven, assim como velocidade e inteligência de código.

# Iniciando

Necessário: Maven.

Para executar a aplicação há três maneiras:

## 1. IDE
Para iniciar direto na IDE basta baixar o repositório em sua máquina e importar em seu ambiente. Executar a aplicação como uma aplicação normal Java pela classe principal.

## 2. Maven
Com o repositório baixado, executar o comando:
`mvn spring-boot:run`

## 3. Deploy do WAR
Se preferir rodar em um servidor (para fins de Produção por exemplo), o Maven gera o .war do projeto. Para isso, executar o seguinte comando:
`mvn clean install`
O .war da aplicação estará disponível em /target (raiz do projeto).

# REST

Nesta aplicação, foi implementado o framework Swagger para documentação e testes de serviços REST. Utiliza-se de uma interface gráfica "swagger-ui".

![swagger](https://raw.githubusercontent.com/aleixofp/projeto-cliente/master/src/main/resources/static/imagem-swagger.png)

# Documentação

Toda a documentação se encontra na pasta [docs](https://github.com/aleixofp/projeto-cliente/tree/master/docs) do projeto.