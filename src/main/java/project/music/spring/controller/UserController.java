package project.music.spring.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.music.spring.mapper.UserMapper;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.User;
import project.music.spring.service.UserService;

import java.util.List;

import static project.music.spring.constant.Constants.USERS;

@Slf4j
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        log.info("Updatting users with name {}", userDTO.name());
        try{
            //User userToUpdate = userMapper.dtoToEntity(userDTO);
            User userToUpdate = userService.updateById(userDTO.name(), userDTO.password(), id);
            UserDTO updatedUserDTO = userMapper.entityToDto(userToUpdate);
            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}