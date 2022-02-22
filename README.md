# Projeto base docker com Spring Boot
<small>Projeto base como criar imagem docker de uma aplicação Spring Boot</small>

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
