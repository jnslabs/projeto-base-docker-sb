# Projeto base docker com Spring Boot ![GitHub repo size](https://img.shields.io/github/repo-size/jnslabs/projeto-base-docker-sb) ![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/jnslabs/projeto-base-docker-sb/continuos-integrations.yml?logo=github) [![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/sbbaseapi.svg)](https://hub.docker.com/repository/docker/jnsousa/sbbaseapi) [![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/pgsbbaseapi.svg)](https://hub.docker.com/repository/docker/jnsousa/pgsbbaseapi)
<small>Projeto base como criar imagem docker de uma aplicação Spring Boot</small>

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Gerar a primeira image docker

Os arquivos dokerfile se encontram na pasta docker.

### 1. Primeiro tem gerar a buid da imagem
```shell
.\mvnw clean package
```

### Testar o jar
```shell
java -jar .\target\sbbaseapi-1.0.0-SNAPSHOT.jar 
```

### 2. Criar arquivo `Dockerfile`

```dockerfile
FROM openjdk:11
WORKDIR /app
COPY ./target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar
```

### 3. Build
```shell
docker build -f sbbaseapi.dockerfile -t jnsousa/sbbaseapi .
```
### Verificar se image foi criada

```shell
docker images
REPOSITORY                   TAG         IMAGE ID       CREATED         SIZE
jnsousa/springdocker         latest      25569890447b   2 minutes ago   680MB
```

### 4. Agora pode criar containers com essa imagem

```shell
docker run --name springdockercontainer -p 8080:8080 jnsousa/sbbaseapi
```

# Criar imagem Banco Dados

## Banco de Dados
```
docker build -f pgsbbaseapi.dockerfile -t jnsousa/pgsbbaseapi .
```

## Criar container banco de dados
```
docker run --name "pgsbbaseapi" -p 5432:5432 -d jnsousa/pgsbbaseapi:1.0.0
```

https://github.com/mesuk/SpringReadyApp

https://blog.devgenius.io/securing-spring-boot-rest-api-with-spring-security-jwt-and-jpa-64ec45fb25e0


## Docker compose
podemos gerar a build projeto com o docker composer:

```yaml
version: "3.8"

services:
  pgbaseapi:
    build:
      dockerfile: ./docker/pgsbbaseapi.dockerfile
      context: .
    image: jnsousa/pgsbbaseapi
    container_name: pgsbbaseapi
    ports:
      - "5432"
    networks:
      - sbbase
    volumes:
      - pgdata:/var/lib/postgresql/data
  api:
    build:
      dockerfile: ./docker/sbbaseapi.dockerfile
      context: .
    image: jnsousa/sbbaseapi
    container_name: sbbaseapi
    environment:
      DB_HOST: pgbaseapi
    ports:
      - "8080:8080"
    networks:
      - sbbase
    depends_on:
      - pgbaseapi

networks:
  sbbase:
    driver: bridge

volumes:
  pgdata:
```

* O dockerfile criar as imagens para api e o banco de dados Postgres, 
repare o comando build ele primeiro buida o mavem para gerar o artifactory 
da api, chama o arquivo dockerfile *pgsbbaseapi.dockerfile* que executa o 
maven antes de gerar a imagem da api. 

* O comando **build** só é executado se não houver a imagens nos repositorios Local oiu remoto (Dockerhub).
Nesse caso pode até comentar esse trecho quando for rodar.

## Github Action

No diretorio `.github/workflow` estão os arquivos de configuração da pipeline no Github Action
`continuos-integrations.yml` realiza o build na branche develop e o `continuos-integrations-main.yml` realiza quando estiver pull request para brnach main,
e tambem atualiza a imagem da api e BD no Dockerhub.
