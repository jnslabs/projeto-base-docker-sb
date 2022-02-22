FROM postgres:13.1-alpine
LABEL maintainer "Jairo Nascimento <jaironsousa@gmail.com>"
ENV POSTGRES_PASSWORD=baseapi
ENV POSTGRES_DB=sbbaseapi-db
EXPOSE 5432