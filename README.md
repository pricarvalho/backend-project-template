# backend-project-template

This repository intend to help developers to startup their application faster.

## Software Architecture

- [Java-20](https://docs.oracle.com/en/java/javase/20/)
- [JOOQ](https://www.jooq.org/doc/3.18/manual/code-generation/codegen-advanced/codegen-config-database/codegen-database-catalog-and-schema-mapping/)
- [SpringBoot](https://spring.io/projects/spring-boot)
- [Swagger3](https://swagger.io/docs/specification/about/) com [SpringDocs2](https://springdoc.org/v2/)
- [Maven](https://maven.apache.org/guides/)
  - [JOOQ](https://www.jooq.org/doc/latest/manual/code-generation/codegen-maven/) - *record entities code generator plugin*
  - [Flyway](https://flywaydb.org/documentation/concepts/migrations.html) - *database script migration plugin* 
- [Docker](https://www.docker.com/)
  - [PosgreSQL-15.3](https://www.postgresql.org/docs/current/index.html)
  - [PgAdmin-4](https://www.pgadmin.org/docs/pgadmin4/latest/index.html)

## Usage

1. Clone the repo

2. Setup the network and the application properties
    - Execute the `make setup` command
  
3. Start the dependencies with docker compose
    - Execute the `make start` command  

4. Now, we are going to compile the project to migrate the database scripts and generate the record entities
    - Execute the `make compile` command
    - Check the logs. The application should have started succesfully
    - Check the `target/generated-sources` folder and if the database record entities were generated

5. Run the Application:
    - Execute the `make run` command 

## Application

  - **Host**: http://localhost:8080
  
  - **Swagger API**: http://localhost:8080/swagger-ui

  - **PgAdmin**: http://localhost:5050/login
      - **use**r: admin@admin.com
      - **password**: admin *(the same to connect to the database)*
