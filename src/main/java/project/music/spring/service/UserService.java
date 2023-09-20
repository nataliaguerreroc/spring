package project.music.spring.service;

import project.music.spring.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getUsers();

    User add(String name, String email, String password, String birthdate);

    void deleteById(UUID id);

    User updateById(String newName, String newEmail, String newPassword, String newBirthdate, UUID id);
}
