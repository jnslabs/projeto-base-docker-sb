FROM maven:3.8.4-jdk-11 as build
LABEL maintainer "Jairo Nascimento <jaironsousa@gmail.com>"
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
LABEL maintainer "Jairo Nascimento <jaironsousa@gmail.com>"
WORKDIR /app
COPY --from=build ./build/target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar