package project.music.spring.service;

import project.music.spring.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User add(String name, String email, String password, String birthdate);

    void deleteById(Long id);

    User updateById(User user, Long id);

}
