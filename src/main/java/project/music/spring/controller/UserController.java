package project.music.spring.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.music.spring.mapper.UserMapper;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.User;
import project.music.spring.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static project.music.spring.constant.Constants.USERS;

@RestController
@RequestMapping(value = USERS)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUser(){
        try{
            List<User> users = userService.getUsers();
            List<UserDTO> userDTOs = users.stream()
                    .map(userMapper::entityToDto).toList();
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        try{
            User newUser = userService.add(userDTO.name(), userDTO.email(), userDTO.password(), userDTO.birthdate());
            UserDTO newUserDTO = userMapper.entityToDto(newUser);
            return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
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

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        try{
            //User userToUpdate = userMapper.dtoToEntity(userDTO);
            User userToUpdate = userService.updateById(userDTO.name(), userDTO.password(), id);
            UserDTO updatedUserDTO = userMapper.entityToDto(userToUpdate);
            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}