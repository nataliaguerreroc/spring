package project.music.spring.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.music.spring.exceptions.UserNotRegistered;
import project.music.spring.model.entity.User;
import project.music.spring.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static project.music.spring.utils.Encrypt.encryptSHA256;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    public User add(String name, String email, String password, String birthdate){
        User users = new User(name, email, encryptSHA256(password), birthdate);
        users = this.userRepository.save(users);
        return users;
    }

    public void deleteById(UUID id){
        this.userRepository.deleteById(id);
    }


    public User updateById(String newName, String newEmail, String newPassword, String newBirthdate, UUID id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotRegistered("No user found with the given ID: " + id);
        }
        log.info("Updatting user with name {}", newName);
        User oldUser = optionalUser.get();
        oldUser.setName(newName);
        oldUser.setEmail(newEmail);
        oldUser.setPassword(encryptSHA256(newPassword));
        oldUser.setBirthdate(newBirthdate);
        oldUser = this.userRepository.save(oldUser);
        return oldUser;

    }





}
