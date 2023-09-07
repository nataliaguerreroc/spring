//package project.music.spring.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import project.music.spring.model.entity.User;
//import project.music.spring.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class UserServiceImplTest {
//    private UserServiceImpl userService;
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setUp(){
//        userRepository = mock(UserRepository.class);
//        userService = new UserServiceImpl(userRepository);
//    }
//
//    @Test
//    public void getUsers(){
//        List<User> userList = new ArrayList<>();
//        userList.add(new User("Andrea", "andrea@gmail.com", "password", "febrero 2"));
//        when(userRepository.findAll()).thenReturn(userList);
//
//        List<User> result = userService.getUsers();
//        assertEquals(1, result.size());
//        assertEquals("Andrea", result.get(0).getName());
//        assertEquals("andrea@gmail.com", result.get(0).getEmail());
//        assertEquals("password", result.get(0).getPassword());
//        assertEquals("febrero 2", result.get(0).getBirthdate());
//    }
//
//    @Test
//    public void addUser(){
//        User newUser = new User("Andrea", "andrea@gmail.com", "password", "febrero 2");
//        when(userRepository.save(any(User.class))).thenReturn(newUser);
//
//        User result = userService.add("Andrea", "andrea@gmail.com", "password", "febrero 2");
//        assertEquals("Andrea", result.getName());
//        assertEquals("andrea@gmail.com", result.getEmail());
//        assertEquals("password", result.getPassword());
//        assertEquals("febrero 2", result.getBirthdate());
//    }
//
//    @Test
//    public void deleteUser(){
//        userService.deleteById(1L);
//
//        verify(userRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void updateUser(){
//        User existingUser = new User("Andrea", "andrea@gmail.com", "password", "febrero 2");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(any(User.class))).thenReturn(existingUser);
//
//        User updatedUser = new User("Lina", "lina@gmail.com", "password", "abril 4");
//        User result = userService.updateById(updatedUser, 1L);
//        assertEquals("Lina", result.getName());
//        assertEquals("lina@gmail.com", result.getEmail());
//        assertEquals("password", result.getPassword());
//        assertEquals("abril 4", result.getBirthdate());
//    }
//}
