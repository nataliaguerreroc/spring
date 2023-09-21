package project.music.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.music.spring.model.entity.User;
import project.music.spring.repository.UserRepository;
import project.music.spring.utils.Encrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("Andrea", "andrea@gmail.com", "password", "febrero 2"));
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getUsers();
        assertEquals(1, result.size());
        User user = result.get(0);
        assertEquals("Andrea", user.getName());
        assertEquals("andrea@gmail.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("febrero 2", user.getBirthdate());
    }

    @Test
    public void addUser(){
        User newUser = new User("Andrea", "andrea@gmail.com", "password", "febrero 2");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.add("Andrea", "andrea@gmail.com", "password", "febrero 2");
        assertEquals("Andrea", result.getName());
        assertEquals("andrea@gmail.com", result.getEmail());
        assertEquals("password", result.getPassword());
        assertEquals("febrero 2", result.getBirthdate());
    }

    @Test
    public void deleteUser(){
        UUID userID = UUID.randomUUID();
        userService.deleteById(userID);

        verify(userRepository, times(1)).deleteById(userID);
    }

    @Test
    public void updateUser(){
        UUID userID = UUID.randomUUID();
        String newName = "Lina";
        String newEmail = "lina@gmail.com";
        String newPassword = "newpassword";
        String newBirthdate = "abril 4";

        User existingUser = new User("Andrea", "andrea@gmail.com", "password", "febrero 2");
        when(userRepository.findById(userID)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User result = userService.updateById(newName, newEmail, newPassword, newBirthdate, userID);
        assertEquals(newName, result.getName());
        assertEquals(newEmail, result.getEmail());
        assertEquals(newBirthdate, result.getBirthdate());

        String encryptedPassword = Encrypt.encryptSHA256(newPassword);
        assertEquals(encryptedPassword, result.getPassword());
    }
}
