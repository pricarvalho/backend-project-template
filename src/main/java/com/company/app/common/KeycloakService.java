package com.company.app.common;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.users.UserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class KeycloakService {

    private final Keycloak keycloak;

    public void create(UserRequest request) {

        final CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());

        final UserRepresentation user = new UserRepresentation();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        try {
            final Response response = keycloak.realm("my-app-name").users().create(user);

            System.out.println(response);
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
}