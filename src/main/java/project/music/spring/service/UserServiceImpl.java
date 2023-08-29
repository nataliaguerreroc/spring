package project.music.spring.service;


import org.springframework.stereotype.Service;
import project.music.spring.model.entity.User;
import project.music.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
        User users = new User(name, email, password, birthdate);
        users = this.userRepository.save(users);
        return users;
    }

    public void deleteById(Long id){
        this.userRepository.deleteById(id);
    }


    public User updateById(User user, Long id){
        Optional<User> optionalUser = this.userRepository.findById(id);
        User oldUser = null;
        if(optionalUser.isPresent()){
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setBirthdate(user.getBirthdate());

            oldUser = this.userRepository.save(oldUser);
        }
        return oldUser;
    }





}
