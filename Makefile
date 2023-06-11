APP_VERSION?="$(shell ./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)"

DOCKER_COMPOSE_FILE_PATH?="docker/docker-compose.yaml"



restart: stop start

# Add after configuring Docker plugin on Maven

# rebuild: clean docker start

# docker:
# 	@./mvnw clean package -DskipTests

compile:
	@./mvnw clean package -DskipTests

run:
	@./mvnw spring-boot:run

start:
	@echo "Starting containers..."
	@echo "App version: $(APP_VERSION)"
	@APP_VERSION=$(APP_VERSION) docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} up -d

stop:
	@echo "Stopping containers..."
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} stop

clean: soft-clean
	@echo "Deleting named volumes..."
	@docker volume rm docker_postgres-data
	@docker volume rm docker_pgadmin4-data 

soft-clean: stop 
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} rm -f -v

status:
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} ps

test:
	@./mvnw clean test

integration-test:
	@./mvnw clean verify

setup:
	@echo "Creating network 'development'..."
	@docker network create --gateway 172.28.0.1 --subnet 172.28.0.0/16 development 2>/dev/null; true
	@echo "Generating 'application.properties'"
	@cp -n src/main/resources/application.properties.sample src/main/resources/application.properties; true