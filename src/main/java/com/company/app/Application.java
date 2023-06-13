package com.company.app;

import static org.keycloak.OAuth2Constants.PASSWORD;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(title = "Backend Project Template API", version = "1.0"))
public class Application {

	final static String serverUrl = "http://localhost:8180";
    final static String realm = "master";
    final static String clientId = "backend-project-template";
    final static String clientSecret = "NcJpz8pLbsXJWcZ3PH2cQP8t4pWtyIdC";

	@Bean
	public Keycloak keycloak() {
		return KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
					.clientId(clientId)
					.grantType(PASSWORD)
					.username("admin")
					.password("admin")
					.clientSecret(clientSecret)
                    .resteasyClient(ResteasyClientBuilder.newBuilder().build())
                    .build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
