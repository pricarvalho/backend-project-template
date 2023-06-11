package com.company.app.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {

    private final UserService service;

    // curl -X GET http://localhost:8080/users/{id}
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable String id) {
        var user = service.findById(id);
        return UserResponse.from(user);
    }

    // curl -X DELETE http://localhost:8080/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    // curl -X POST -H 'Content-Type: application/json' -d '{"firstName": "John", "lastName": "Smith", "email": "john.smith@gmail.com"}' http://localhost:8080/users
    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest) {
        var user = service.save(userRequest);
        return UserResponse.from(user);
    }

    // curl -X PUT -H 'Content-Type: application/json' -d '{"firstName": "John", "lastName": "Smith", "email": "john.smith@hotmail.com"}' http://localhost:8080/users/{id}
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        var user = service.update(id, userRequest);
        return UserResponse.from(user);
    }

}
