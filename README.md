# Projeto base docker com Spring Boot ![GitHub repo size](https://img.shields.io/github/repo-size/jnslabs/projeto-base-docker-sb) ![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/jnslabs/projeto-base-docker-sb/continuos-integrations.yml?logo=github) [![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/sbbaseapi.svg)](https://hub.docker.com/repository/docker/jnsousa/sbbaseapi) [![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/pgsbbaseapi.svg)](https://hub.docker.com/repository/docker/jnsousa/pgsbbaseapi)
<small>Projeto base como criar imagem docker de uma aplicação Spring Boot</small>

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Gerar a primeira image docker

1. Primeiro tem gerar a buid da imagem
```shell
.\mvnw clean package
```
Testar o jar
```shell
java -jar .\target\springdocker-0.0.1-SNAPSHOT.jar 
```

2. Criar arquivo `Dockerfile`
```dockerfile
FROM openjdk:11
WORKDIR /app
COPY ./target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar
```

3. Build
```shell
docker build -t jnsousa/springdocker .
```
Verificar se image foi criada
```shell
docker images
REPOSITORY                   TAG         IMAGE ID       CREATED         SIZE
jnsousa/springdocker         latest      25569890447b   2 minutes ago   680MB
```

4. Agora pode criar containers com essa imagem
```shell
docker run --name springdockercontainer -p 8081:8080 jnsousa/springdocker
```

# Criar imagem Banco Dados

## Banco de Dados
```
docker build -f pgsbbaseapi.dockerfile -t jnsousa/pgsbbaseapi:1.0.0 .
```

## Criar container banco de dados
```
docker run --name "pgsbbaseapi" -p 5432:5432 -d jnsousa/pgsbbaseapi:1.0.0
```

https://github.com/mesuk/SpringReadyApp

https://blog.devgenius.io/securing-spring-boot-rest-api-with-spring-security-jwt-and-jpa-64ec45fb25e0

