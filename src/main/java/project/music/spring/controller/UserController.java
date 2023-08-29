package project.music.spring.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.music.spring.mapper.UserMapper;
import project.music.spring.model.dto.UserDTO;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        try{
            User newUser = userService.add(userDTO.name(), userDTO.email(), userDTO.password(), userDTO.birthdate());
            UserDTO newUserDTO = UserMapper.INSTANCE.entityToDto(newUser);
            return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
        public ResponseEntity<List<UserDTO>> getUser(){
        try{
            List<User> users = userService.getUsers();
            List<UserDTO> userDTOs = users.stream()
                    .map(UserMapper.INSTANCE::entityToDto).toList();
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        try{
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        try{
            User userToUpdate = UserMapper.INSTANCE.dtoToEntity(userDTO);
            User updatedUser = userService.updateById(userToUpdate, id);
            UserDTO updatedUserDTO = UserMapper.INSTANCE.entityToDto(updatedUser);
            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
