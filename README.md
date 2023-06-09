# backend-project-template

This repository intend to help developers to startup their application faster.

## Software Architecture

- [Java-20](https://docs.oracle.com/en/java/javase/20/)
- [JOOQ](https://www.jooq.org/doc/3.18/manual/code-generation/codegen-advanced/codegen-config-database/codegen-database-catalog-and-schema-mapping/)
- [SpringBoot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/)
  - [JOOQ](https://www.jooq.org/doc/latest/manual/code-generation/codegen-maven/) - *record entities code generator plugin*
  - [Flyway](https://flywaydb.org/documentation/concepts/migrations.html) - *database script migration plugin* 
- [Docker](https://www.docker.com/)
  - [PosgreSQL-15.3](https://www.postgresql.org/docs/current/index.html)
  - [PgAdmin-4](https://www.pgadmin.org/docs/pgadmin4/latest/index.html)
  - [Keycloak](https://www.keycloak.org) - *Open Source Identity and Access Management*

## Usage

1. Clone the repo

3. We need to start the docker containers to allow flyway and jooq work properly 
    - Navegate to the `cd docker/` folder
    - Run the `docker compose up` command
    - Be sure the docker successfully started
  
3. Get back to the project root folder `cd ../`

5. Now, we are going to compile the project to migrate the database scripts and generate the record entities
    - Execute the command `mvn clean package` or `mvn clean install`
    - Check the logs. It must compile with success
    - Check the `target/generated-sources` folder and if the database record entities were generated
  
5. Go to the browser to access the follow consoles:
    - **PgAdmin**: http://localhost:5050/login
      - **use**r: admin@admin.com
      - **password**: admin *(the same to connect to the database)*
      
    - **KeyCloak**: http://localhost:8180
      - **user:** admin
      - **password:** admin 

Enjoy!
=D
