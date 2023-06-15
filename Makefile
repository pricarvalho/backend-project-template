APP_VERSION?="$(shell ./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)"

DOCKER_COMPOSE_FILE_PATH?="docker/docker-compose.yaml"

restart: stop start

run:
	@./mvnw spring-boot:run

compile:
	@./mvnw clean package -DskipTests

setup: 
	-$(MAKE) create-network
	$(MAKE) start
	$(MAKE) install
	$(MAKE) compile

install:
	@echo "Generating 'application.properties'"
	@cp -n src/main/resources/application.properties.sample src/main/resources/application.properties; true

#TESTING
test:
	@./mvnw clean test

integration-test:
	@./mvnw clean verify

#DOCKER
start:
	@echo "Starting containers..."
	@echo "App version: $(APP_VERSION)"
	@APP_VERSION=$(APP_VERSION) docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} up -d

stop:
	@echo "Stopping containers..."
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} stop

kill: stop 
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} rm -f -v

clean: kill
	@echo "Deleting named volumes..."
	@docker volume rm docker_my-app-postgres-data
	@docker volume rm docker_my-app-pgadmin4-data 

create-network:
	@echo "Creating network 'development'..."
	-@docker network create --gateway 172.28.0.1 --subnet 172.28.0.0/16 development 2>/dev/null; true

status:
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} ps	