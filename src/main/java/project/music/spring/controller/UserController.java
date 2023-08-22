package project.music.spring.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.music.spring.model.entity.User;
import project.music.spring.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody Map<String, String> json){
        return new ResponseEntity<>(userService.add(json.get("name"), json.get("email"), json.get("password"), json.get("birthdate")), HttpStatus.CREATED);
    }

    /*
    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@Valid @RequestBody Map<String, String> json){
        return this.userService.add(json.get("name"), json.get("email"), json.get("password"), json.get("birthdate"));

    }
    */

    @GetMapping(value = {""})
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateById(user, id), HttpStatus.OK);
    }


}
