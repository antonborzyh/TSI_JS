package com.tsieducation.controllers.api;

import com.tsieducation.entities.UsersEntity;
import com.tsieducation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserApiController {

    private UserService userService;

    // Build CREATE UsersEntity REST API
    @PostMapping
    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity usersEntity) {
        UsersEntity savedUsersEntity = userService.createUser(usersEntity);
        return new ResponseEntity<>(savedUsersEntity, HttpStatus.CREATED);
    }

    // Build GET UsersEntity by id REST API
    // http://localhost:8079/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable("id") Integer userId) {
        UsersEntity usersEntity = userService.getUserById(userId);
        return new ResponseEntity<>(usersEntity, HttpStatus.OK);
    }

    // Build GET All UsersEntity REST API
    // http://localhost:8079/api/users
    @GetMapping
    public ResponseEntity<Map<String, List<UsersEntity>>> getAllUsers() {
        List<UsersEntity> usersEntities = userService.getAllUsers();
        return new ResponseEntity<>(Map.of("users", usersEntities), HttpStatus.OK);
    }

    // Build UPDATE UsersEntity REST API
    // http://localhost:8079/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable("id") Integer userId,
                                                   @RequestBody UsersEntity usersEntity) {
        usersEntity.setUserId(userId);
        UsersEntity updatedUsersEntity = userService.updateUser(usersEntity);
        return new ResponseEntity<>(updatedUsersEntity, HttpStatus.OK);
    }

    // Build DELETE UsersEntity REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("UsersEntity deleted", HttpStatus.OK);
    }

}
