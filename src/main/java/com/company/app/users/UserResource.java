package com.company.app.users;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_= {@Autowired})
public class UserResource {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        var user = service.create(userRequest);
        return new ResponseEntity<UserResponse>(UserResponse.from(user), CREATED);
    }
    
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable String id) {
        var user = service.findById(id);
        return UserResponse.from(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        var user = service.update(id, userRequest);
        return UserResponse.from(user);
    }

}
